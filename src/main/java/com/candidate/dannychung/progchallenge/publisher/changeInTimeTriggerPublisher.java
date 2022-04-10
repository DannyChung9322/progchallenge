package com.candidate.dannychung.progchallenge.publisher;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.candidate.dannychung.progchallenge.event.ChangeInTimeEvent;
import com.candidate.dannychung.progchallenge.util.UpdatePosition;

@Component
public class changeInTimeTriggerPublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private UpdatePosition updatePosition;

	
	@EventListener(ApplicationReadyEvent.class)
	public void changeInTime() throws InterruptedException {
		double randomNumber = 0;
		Random r = new Random();
		;
		int i = 0;
		while (true) {
			randomNumber = r.nextDouble() * (2.0d - 0.5d) + 0.5d;
			Thread.sleep((long) (randomNumber * 1000));
			publishchangeInTimeEvent(randomNumber);
			i = 1;

		}
	}
	
	public void publishchangeInTimeEvent(double delta_t) {
		Map<String, Double> updatedPriceMap = updatePosition.updatePrices(delta_t);
		ChangeInTimeEvent changeInTimeEvent = new ChangeInTimeEvent(this, updatedPriceMap);
		applicationEventPublisher.publishEvent(changeInTimeEvent);
	}

	

}
