package com.tripoin.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SystemParameterMapper implements RowMapper<SystemParameter> {
	public SystemParameter mapRow(ResultSet rs, int rowNum) throws SQLException {
		SystemParameter systemParameter = new SystemParameter();
		systemParameter.setId(rs.getLong("system_parameter_id"));
		systemParameter.setName(rs.getString("system_parameter_name"));
		systemParameter.setValue(rs.getString("system_parameter_value"));
		systemParameter.setRemarks(rs.getString("system_parameter_remarks"));
		return systemParameter;
	}
 
}