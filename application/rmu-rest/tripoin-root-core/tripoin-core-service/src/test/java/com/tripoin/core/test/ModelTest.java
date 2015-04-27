package com.tripoin.core.test;

import java.util.Date;
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
import com.tripoin.core.pojo.OrderDetail;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.Seat;
import com.tripoin.core.pojo.Stan;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IStanGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:/META-INF/spring/datasource/dataSourceContext_mysql.xml",
		"classpath*:/META-INF/spring/applicationContext-jpa.xml"})
public class ModelTest implements ApplicationContextAware  {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ModelTest.class);
	
	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IStanGenerator stanManager;
	
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
		User user = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"password"}, new Object[]{"3"}, null, null).get(0);
		System.out.println(user);
//		List<User> datas = iGenericManagerJpa.loadObjects(User.class);
//		for(User data : datas){
//			LOGGER.debug("data :"+data.toString());
//		}
	}

}
