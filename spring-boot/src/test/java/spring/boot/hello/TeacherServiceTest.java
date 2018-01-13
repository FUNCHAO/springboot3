package spring.boot.hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fc.SpringBoot.MyApplication;
import com.fc.SpringBoot.entity.Teacher;
import com.fc.SpringBoot.service.ITeacherService;


//表示在测试环境中跑
@RunWith(SpringRunner.class)
//将启动整个springboot工程，并且自己创建的启动类，一定要在这里指明classes，否则报错：找不到什么context.xml
@SpringBootTest(classes=MyApplication.class)
public class TeacherServiceTest {
	@Autowired
	private ITeacherService teacherService;
	@Test
	public void getTeaTest(){
		Teacher tea = teacherService.getTea(14);
		System.out.println(tea.getAge());
		//主要导包import org.junit.Assert;
		//Assert.assertEquals(new Integer(11), tea.getAge());
	}
}
