package com.fc.SpringBoot.springbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.fc.SpringBoot.springbatch.Listener.CsvJobListener;
import com.fc.SpringBoot.springbatch.entity.Person;
import com.fc.SpringBoot.springbatch.process.CsvItemProcessor;
import com.fc.SpringBoot.springbatch.validator.CsvBeanValidator;
/**
 * 此配置类手动触发批处理任务
 * @author Administrator
 *
 */
@Configuration
@EnableBatchProcessing
class TriggerBatchConfig  {
	@Bean
	@StepScope
	public FlatFileItemReader<Person>
		reader(@Value("#{jobParameters['input.file.name']}")String pathToFile) 
				throws Exception{
		FlatFileItemReader<Person> reader=new FlatFileItemReader<Person>();
		reader.setResource(new ClassPathResource(pathToFile));
		reader.setLineMapper(
				new DefaultLineMapper<Person>()
				{{
						setLineTokenizer(
							new DelimitedLineTokenizer()
							{{
								setNames(new String []{"name","age","nation","address"});
							}}
						);
						setFieldSetMapper(
							new BeanWrapperFieldSetMapper<Person>()
							{{
								setTargetType(Person.class);
							}}	
						);
				}}
				);
		return reader;
	}
	/**
	 * 使用自定义的ItemProcessor的实现CsvItemProcessor
	 * 为processor指定校验器为CsvBeanValidator;
	 */
	@Bean
	public ItemProcessor<Person,Person>processor(){
		CsvItemProcessor csvItemProcessor = new CsvItemProcessor();
		csvItemProcessor.setValidator(csvBeanValidator());
		return csvItemProcessor;
	}
	/**
	 * Spring 能让容器中已有的Bean以参数的形式注入，Spring Boot已为我们定义了dataSource
	 * 我们使用了JDBC批处理的JdbcBatchItemWriter来写数据库
	 * 在此设置要执行的批处理的SQL语句
	 */
	@Bean
	public ItemWriter<Person>writer(DataSource dataSource){
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		String sql="insert into person "+"(name,age,nation,address)"+
					"values(:name,:age,:nation,:address)";
		writer.setSql(sql);
		writer.setDataSource(dataSource);
		return writer;
	}
	/**
	 * jobRepository的定义需要dataSource和transactionManager，
	 * Springboot已为我们自动配置了这两个类，Spring可通过这两个方法注入已有的bean
	 */
	@Bean
	public JobRepository jobRepository(DataSource dataSource,
						PlatformTransactionManager transactionManager)throws Exception{
		
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
		return jobRepositoryFactoryBean.getObject();
	}
	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource,
			PlatformTransactionManager transactionManager)throws Exception{
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
		return simpleJobLauncher;
	}
	/**
	 * 为job绑定step
	 * 绑定监听器csvJobListener
	 */
	@Bean
	public Job importJob(JobBuilderFactory jobs,Step s1){
		return jobs.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(s1)
				.end()
				.listener(csvJobListener())
				.build();
	}
	/**
	 * 每次提交(chunk中设置)条数据
	 * 给step绑定：reader,processor,writer
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,ItemReader<Person> reader,
			ItemWriter<Person> writer,ItemProcessor<Person,Person> processor){
		return stepBuilderFactory
				.get("step1")
				//chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)2条后写入(writer)一次。
				.<Person,Person>chunk(2)
				//捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
				.faultTolerant().retryLimit(2).retry(Exception.class).skipLimit(100).skip(Exception.class)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	@Bean
	public CsvJobListener csvJobListener(){
		
		return new CsvJobListener();
	}
	@Bean
	public Validator<Person> csvBeanValidator(){
		
		return new CsvBeanValidator<Person>();
	}
}

