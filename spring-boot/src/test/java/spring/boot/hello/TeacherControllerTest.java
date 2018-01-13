package spring.boot.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fc.SpringBoot.MyApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)
@AutoConfigureMockMvc//controller测试
public class TeacherControllerTest {
	@Autowired
	private MockMvc mvc;
	@Test
	public void getAllTeasTest() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/teacher/getAllTeas"))
		//期望状态正常
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}	
