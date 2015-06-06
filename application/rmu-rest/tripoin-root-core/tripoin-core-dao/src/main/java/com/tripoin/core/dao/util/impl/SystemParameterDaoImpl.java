package com.tripoin.core.dao.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tripoin.core.dao.util.ISystemParameterDao;
import com.tripoin.core.mapper.SystemParameterMapper;
import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class SystemParameterDaoImpl implements ISystemParameterDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<SystemParameter> systemParameterMapper = new SystemParameterMapper();
	
	@Override
	public SystemParameter loadValue(String name) {
		SystemParameter systemParameter = (SystemParameter)jdbcTemplate.queryForObject("SELECT * FROM master_system_parameter WHERE system_parameter_name = ?", new Object[]{name}, systemParameterMapper);
		return systemParameter;
	}

}
