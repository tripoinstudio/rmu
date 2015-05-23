package com.tripoin.rest.test;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.salt.ZeroSaltGenerator;
import org.jasypt.spring.security3.PasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"**/web-application-config*"})
public class GenerateUserTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(GenerateUserTest.class);
	
	private StandardStringDigester jasyptStringDigester;
	
	private ApplicationContext applicationContext;
	
	public <T> T getBean(Class<T> beanType) {
		return applicationContext.getBean(beanType);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Test
	public void runTest() throws Exception {
		/*Role role = iGenericManagerJpa.getObjectsUsingParameter(Role.class, new String[]{"code"}, new Object[]{"ROLE_REST_HTTP_USER"}, null, null).get(0);
		User user = new User();
		user.setUsername("ridla");
		user.setPassword(encodePassword("ridla"));
		user.setEnabled(1);
		user.setStatus(0);
		user.setRemarks("Available");
		user.setRole(role);
		iGenericManagerJpa.saveObject(user);*/
		encodePassword("agung");
	}
	
	private String encodePassword(String plainText){		
		jasyptStringDigester = new StandardStringDigester();
		jasyptStringDigester.setAlgorithm("SHA-1");
		jasyptStringDigester.setIterations(100000);
		jasyptStringDigester.setSaltGenerator(new ZeroSaltGenerator());
		jasyptStringDigester.setSaltSizeBytes(10);
		String chipperText = jasyptStringDigester.digest(plainText);
		LOGGER.debug(chipperText);		
		return chipperText;
	}

}
