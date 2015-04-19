package com.tripoin.core.dao.util.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tripoin.core.dao.util.IStanDao;
import com.tripoin.core.mapper.StanMapper;
import com.tripoin.core.pojo.Stan;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class StanDaoImpl implements InitializingBean, IStanDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Stan> stanMapper = new StanMapper();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}	
	
	public void updateStan(Long stanCounter, Long stanId){
		jdbcTemplate.update("UPDATE trx_stan SET stan_counter = ? WHERE stan_id = ?", new Object[]{stanCounter, stanId});
	}
	
	public Stan loadStan(Long userId){
		Stan stan = (Stan)jdbcTemplate.queryForObject("SELECT * FROM trx_stan WHERE user_id = ?", new Object[]{userId}, stanMapper);
		return stan;
	}	

}
