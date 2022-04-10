package com.candidate.dannychung.progchallenge.dao;

import java.util.List;

import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;


public interface StockAndOptionDetailsDAO {

	List<PortfolioItemConstantVO> selectConstantsForSecurities();
	
	int updateCommonStockPrice(PortfolioItemConstantVO portfolioItemConstantVO);
	
	int updateCallOptionPrice(PortfolioItemConstantVO portfolioItemConstantVO);
	
	int updatePutOptionPrice(PortfolioItemConstantVO portfolioItemConstantVO);
}
