package com.tripoin.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tripoin.core.pojo.Stan;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class StanMapper implements RowMapper<Stan> {
	public Stan mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stan stan = new Stan();
		stan.setId(rs.getLong("stan_id"));
		stan.setStanCounter(rs.getLong("stan_counter"));
		stan.setStanMax(rs.getLong("stan_max"));
		stan.setUserId(rs.getLong("user_id"));
		return stan;
	}
 
}