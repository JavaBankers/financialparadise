package com.sda.spring;

import com.sda.spring.database.dbtables.TableManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinparadiseApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Test
	public void testCreateTables() {
		TableManager.dropAllTables();
		TableManager.createAllTables();
	}

}
