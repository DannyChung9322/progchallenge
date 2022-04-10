package com.candidate.dannychung.progchallenge.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.candidate.dannychung.progchallenge.dao.impl.StockAndOptionDetailsDAOImpl;
import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;
import com.candidate.dannychung.progchallenge.service.CalculationByItem;

@Component
public class UpdatePosition {
	
	@Autowired	
	private StockAndOptionDetailsDAOImpl stockAndOptionDetailsDAOImpl;

	@Autowired
	private CalculationByItem calculationByItem;
	
	public Map<String, Double> updatePrices(double delta_t) {
		List<PortfolioItemConstantVO> latestPricInfo = new ArrayList<>();
		// To fetch all prices for all securities
		stockAndOptionDetailsDAOImpl.selectConstantsForSecurities().forEach(latestPricInfo::add);
		//filter type = 1 to get all common stocks
		latestPricInfo.stream().filter(l -> l.getType() == 1).forEach((l) -> {
		// set updated price by applying Discrete Time Geometric Brownian motion for stock prices
			l.setCurrentPrice(calculationByItem.calculateCommonStockPrice(l, delta_t));
		// update common stock price and related_stock_price used by options
			stockAndOptionDetailsDAOImpl.updateCommonStockPrice(l);
		});
		
		latestPricInfo.clear();
		// To fetch all prices for all securities again
		stockAndOptionDetailsDAOImpl.selectConstantsForSecurities().forEach(latestPricInfo::add);
		//filter type = 2 to get all call options
		latestPricInfo.stream().filter(l -> l.getType() == 2).forEach((l) -> {
		// set updated price by applying European Option PricingFormula
			l.setCallOptionPrice(calculationByItem.calculateCallOptionPrice(l));
		// update call option price
			stockAndOptionDetailsDAOImpl.updateCallOptionPrice(l);
		});

		// To update each put option price
		latestPricInfo.clear();
		// To fetch all prices for all securities again
		stockAndOptionDetailsDAOImpl.selectConstantsForSecurities().forEach(latestPricInfo::add);
		//filter type = 3 to get all put options
		latestPricInfo.stream().filter(l -> l.getType() == 3).forEach((l) -> {
		// set updated price by applying European Option PricingFormula
			l.setPutOptionPrice(calculationByItem.calculatePutOptionPrice(l));
		// update put option price
			stockAndOptionDetailsDAOImpl.updatePutOptionPrice(l);
		});

		// Store updated price information for each security to updatedPriceMap
		latestPricInfo.clear();
		stockAndOptionDetailsDAOImpl.selectConstantsForSecurities().forEach(latestPricInfo::add);
		Map<String, Double> updatedPriceMap = latestPricInfo.stream()
				.collect(Collectors.toMap(PortfolioItemConstantVO::getTicker, a -> a.getType() == 1
						? a.getCurrentPrice() : a.getType() == 2 
						? a.getCallOptionPrice() : a.getType() == 3 
						? a.getPutOptionPrice() : 0));
		return updatedPriceMap;
	}
	
}
