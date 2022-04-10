package com.candidate.dannychung.progchallenge.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.candidate.dannychung.progchallenge.dao.StockAndOptionDetailsDAO;
import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
@Transactional
public class StockAndOptionDetailsDAOImpl implements StockAndOptionDetailsDAO {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<PortfolioItemConstantVO> selectConstantsForSecurities() {
		return jdbcTemplate.query("select * from stock_and_option_details;", BeanPropertyRowMapper.newInstance(PortfolioItemConstantVO.class));
	}
	
	@Override
	  public int updateCommonStockPrice(PortfolioItemConstantVO portfolioItemConstantVO) {
	    return jdbcTemplate.update("UPDATE stock_and_option_details SET current_price=? WHERE ticker=? or related_Common_Stock_Ticker=?",
	        new Object[] { portfolioItemConstantVO.getCurrentPrice(), portfolioItemConstantVO.getTicker(), portfolioItemConstantVO.getTicker() });
	  }
	
	@Override
	  public int updateCallOptionPrice(PortfolioItemConstantVO portfolioItemConstantVO) {
	    return jdbcTemplate.update("UPDATE stock_and_option_details SET call_option_price=? WHERE ticker=?",
	        new Object[] { portfolioItemConstantVO.getCallOptionPrice(), portfolioItemConstantVO.getTicker() });
	  }

	@Override
	public int updatePutOptionPrice(PortfolioItemConstantVO portfolioItemConstantVO) {
		  return jdbcTemplate.update("UPDATE stock_and_option_details SET put_option_price=? WHERE ticker=?",
			        new Object[] { portfolioItemConstantVO.getPutOptionPrice(), portfolioItemConstantVO.getTicker() });
			  
	}
	
	

}
	