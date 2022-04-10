package com.candidate.dannychung.progchallenge.service;

import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;

public interface CalculationByItem {
	
	double calculateCommonStockPrice(PortfolioItemConstantVO l,double delta_t);
	
	double calculateCallOptionPrice(PortfolioItemConstantVO l);
	
	double calculatePutOptionPrice(PortfolioItemConstantVO l);

}
