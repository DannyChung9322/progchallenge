package com.candidate.dannychung.progchallenge.listener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.candidate.dannychung.progchallenge.dao.impl.StockAndOptionDetailsDAOImpl;
import com.candidate.dannychung.progchallenge.event.ChangeInTimeEvent;
import com.candidate.dannychung.progchallenge.model.PortfolioItemConstantVO;
import com.candidate.dannychung.progchallenge.model.PortfolioItemInputVO;
import com.candidate.dannychung.progchallenge.util.FileActionsUtil;
import com.candidate.dannychung.progchallenge.util.UpdatePosition;

@Component
public class DetailReportSubsciber implements ApplicationListener<ChangeInTimeEvent> {

	@Value("${csvinput.path}")
	private String csvInputPath;
	
	@Value("${csvoutput.path}")
	private String csvOutPath;
	
	@Autowired
	private FileActionsUtil fileActionsUtil;
	
	@Autowired	
	private StockAndOptionDetailsDAOImpl stockAndOptionDetailsDAOImpl;
	
	private Map<String, Double> updatedPriceMap;
	private List<PortfolioItemInputVO> inputList;
	
	@Override
	public void onApplicationEvent(ChangeInTimeEvent event) {	
		//Subscribe to updated prices
		updatedPriceMap = event.getUpdatedPriceMap();
		
		// Retrieve input value
		inputList = fileActionsUtil.getInputCsv(csvInputPath);
		
		
	}
	
	public void exportReport() throws IOException {
		// get names for  securities
		Map<String, String> securityNameMap = stockAndOptionDetailsDAOImpl.selectConstantsForSecurities().stream()
				.collect(Collectors.toMap(PortfolioItemConstantVO::getTicker, PortfolioItemConstantVO::getStockName));
		
		// Add updated price and total NAV adjacent to input details
		for (PortfolioItemInputVO inputListItem : inputList) {
			inputListItem.setStockName(securityNameMap.get(inputListItem.getTicker()));
			inputListItem.setUpdatedPrice(updatedPriceMap.get(inputListItem.getTicker()));
			inputListItem.setNav( inputListItem.getLongVolume()*inputListItem.getUpdatedPrice()
						  + inputListItem.getShortVolume()*inputListItem.getUpdatedPrice()*-1);
			
			fileActionsUtil.outputMarketValuesReport(inputList,csvOutPath);
				
		}
		
	}
	

	

	

}
