package com.tripoin.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dao.util.ISystemParameterDao;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.service.util.ISystemParameterService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("systemParameterService")
public class SystemParameterServiceImpl implements ISystemParameterService {
	
	@Autowired
	private ISystemParameterDao iSystemParameterDao;

	@Override
	public SystemParameter getParameter(String name) {
		return iSystemParameterDao.loadValue(name);
	}

}
