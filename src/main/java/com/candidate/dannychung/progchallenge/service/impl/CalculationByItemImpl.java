package com.candidate.dannychung.progchallenge.service.impl;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import com.candidate.dannychung.progchallenge.constant.ConstantValue;
import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;
import com.candidate.dannychung.progchallenge.service.CalculationByItem;

@Service
public class CalculationByItemImpl implements CalculationByItem {
	double r = ConstantValue.riskFreeInterestRate;
	
	@Override
	public double calculateCommonStockPrice(PortfolioItemConstantVO l,double delta_t) {
		// TODO Auto-generated method stub
		//delta_S / s = ( mil*delta_t / 7257600)+ (sigma * episnon *sqrt(delta_t/7257600))
		//delta_S / s = (St+1 - St) / St 	
		//(St+1 - St) / St = delta_S / s
		//St+1 = St*(1+delta_s/s)
		//St+1 = St*(1+ (mil*delta_t / 7257600)+ (sigma * episnon *sqrt(delta_t/7257600)))
		NormalDistribution dist = new NormalDistribution(0, 1);
		double sigma = l.getAnnualizedSd();
		double mil = l.getExpectedReturn();
		double episnon = dist.sample();
		return 
				l.getCurrentPrice()* (1+ (mil*delta_t / 7257600)+ (sigma * episnon * Math.sqrt(delta_t/7257600)));
	}

	@Override
	public double calculateCallOptionPrice(PortfolioItemConstantVO l) {
		NormalDistribution dist = new NormalDistribution(0, 1);
		double S = l.getCurrentPrice();
		double K = l.getStrikePrice();
		double sigma = l.getAnnualizedSd();
		int t = l.getTimeToMaturity();
		double d1 = (Math.log(S/K)+ (r + (Math.pow(sigma,2)/2) * t)) / sigma*Math.sqrt(t);
		double d2 = d1 - sigma * Math.sqrt(t);
		return S*dist.cumulativeProbability(d1)-K*Math.exp(-1*r*t)*dist.cumulativeProbability(d2);
	}

	@Override
	public double calculatePutOptionPrice(PortfolioItemConstantVO l) {
		NormalDistribution dist = new NormalDistribution(0, 1);
		double S = l.getCurrentPrice();
		double K = l.getStrikePrice();
		double sigma = l.getAnnualizedSd();
		int t = l.getTimeToMaturity();
		double d1 = (Math.log(S/K)+ (r + (Math.pow(sigma,2)/2) * t)) / sigma*Math.sqrt(t);
		double d2 = d1 - sigma * Math.sqrt(t);
		return K*Math.exp(-1*r*t)*dist.cumulativeProbability(-1*d2)-S*dist.cumulativeProbability(-1*d1);
	}

}
