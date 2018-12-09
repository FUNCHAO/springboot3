package com.fc.SpringBoot.springbatch.process;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.fc.SpringBoot.springbatch.entity.Person;

public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
	@Override
	public Person process(Person item) throws ValidationException {
		//需要执行super.process才会调用自定义校验器
		super.process(item);
		if("汉族".equals(item.getNation()))
		{
			item.setNation("01");
		}else
		{
			item.setNation("02");
		}
		return item;
	}
}
