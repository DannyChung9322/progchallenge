package com.candidate.dannychung.progchallenge.event;

import java.util.Map;

import org.springframework.context.ApplicationEvent;

public class ChangeInTimeEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Double>  updatedPriceMap;
	public ChangeInTimeEvent(Object source,Map<String, Double>  updatedPriceMap) {
		super(source);
		this.updatedPriceMap = updatedPriceMap;
		// TODO Auto-generated constructor stub
	}

	
	 public Map<String, Double>  getUpdatedPriceMap() {
	        return updatedPriceMap;
	    }


	
	


}
