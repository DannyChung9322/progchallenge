package com.candidate.dannychung.progchallenge.listener;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.candidate.dannychung.progchallenge.event.ChangeInTimeEvent;
import com.candidate.dannychung.progchallenge.model.PortfolioItemInputVO;
import com.candidate.dannychung.progchallenge.util.FileActionsUtil;
import com.candidate.dannychung.progchallenge.util.UpdatePosition;

@Component
public class TotalNavSubsciber implements ApplicationListener<ChangeInTimeEvent> {
	
	@Value("${csvinput.path}")
	private String csvInputPath;
	
	@Autowired
	private FileActionsUtil fileActionsUtil;
	
	private Map<String, Double> updatedPriceMap;
	private List<PortfolioItemInputVO> inputList;
	private DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	private NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance();
	
	@Override
	public void onApplicationEvent(ChangeInTimeEvent event) {	
		//Subscribe to updated prices
		updatedPriceMap = event.getUpdatedPriceMap();
		
		// Retrieve input value
		inputList = fileActionsUtil.getInputCsv(csvInputPath);

		// Add updated price and total NAV adjacent to input details
		for (PortfolioItemInputVO inputListItem : inputList) {
			inputListItem.setUpdatedPrice(updatedPriceMap.get(inputListItem.getTicker()));
			inputListItem.setNav( inputListItem.getLongVolume()*inputListItem.getUpdatedPrice()
						  + inputListItem.getShortVolume()*inputListItem.getUpdatedPrice()*-1);
				
		}
		
		System.out.println(
				LocalDateTime.now().format(dtFormatter)+"  "+
				"Total portfolio¡¦s NAV : " + 
				dollarFormatter.format(inputList.stream().mapToDouble(l->l.getNav()).sum()));
	}

	

	

}
