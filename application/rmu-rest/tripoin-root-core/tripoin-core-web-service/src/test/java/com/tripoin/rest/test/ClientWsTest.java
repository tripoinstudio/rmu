package com.tripoin.rest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import antlr.Version;

import com.tripoin.core.dto.GeneralConnectionDTO;
import com.tripoin.core.dto.Versions;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.ISynchronize;
import com.tripoin.core.rest.util.impl.Base64AuthorizationImpl;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath*:/META-INF/spring/integration/http-outbound-config.xml"})
public class ClientWsTest implements ApplicationContextAware {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ClientWsTest.class);
	
	private ApplicationContext applicationContext;
	
	@Autowired
	private IRestClient restClientVersion;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ISynchronize synchronizeTrain;

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
		/*Versions versions = restClientVersion.execute(Versions.class);
		LOGGER.debug("Version : ".concat(versions.toString()));*/
		
//		LOGGER.debug("Version : ".concat(synchronizeTrain.getVersion().toString()));
		
		/*LOGGER.debug("Version : ".concat(new Base64AuthorizationImpl().encodeUserCredentials("train01", "train01").toString()));*/		
	}
	
}
