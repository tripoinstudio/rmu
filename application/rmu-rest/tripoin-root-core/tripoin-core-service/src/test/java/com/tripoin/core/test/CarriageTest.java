package com.tripoin.core.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.service.IGenericManagerJpa;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:/META-INF/spring/datasource/dataSourceContext_mysql.xml",
		"classpath*:/META-INF/spring/applicationContext-jpa.xml"})
public class CarriageTest implements ApplicationContextAware  {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(CarriageTest.class);
	
	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Test
	public void runTest() throws Exception {
		List<Carriage> cariages = iGenericManagerJpa.loadObjects(Carriage.class);
		System.out.println("debug :"+cariages.toString());
		for(Carriage carriage : cariages){
			LOGGER.debug("data :"+carriage.toString());
		}
	}

}
