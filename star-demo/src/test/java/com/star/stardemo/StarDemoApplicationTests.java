package com.star.stardemo;

import com.star.stardemo.controller.CarController;
import com.star.stardemo.controller.startTest;
import com.star.stardemo.dataobject.StarUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarDemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(StarDemoApplicationTests.class);

	@Autowired
	private CarController carController;

	@Autowired
	private startTest startTest;


	@Test
	public void contextLoads() {

//		StarUser s = StarUser.newBuilder().id(1).userAge(22).userName(null).build();

//		carController.e();
//
//		System.out.println(1);

		startTest.test();

		log.info("========================over=========");


	}





}
