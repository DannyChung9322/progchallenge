package com.candidate.dannychung.progchallenge.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.candidate.dannychung.progchallenge.model.PortfolioItemInputVO;



@Component
public class FileActionsUtil {
	
	private DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	private NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance();
	
	
	public List<PortfolioItemInputVO> getInputCsv(String csvPath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(csvPath);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		List<PortfolioItemInputVO> portfolioInputList = new ArrayList<>();
		int count = 0;
		try {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				
				// split on comma(',')
				String[] personCsv = line.split(",");
				if(count !=0) // skip header line
				{
				// create object to store values
				PortfolioItemInputVO portfolioItemInputVO = new PortfolioItemInputVO();

				// add values from csv to the object
				portfolioItemInputVO.setTicker(personCsv[0]);
				portfolioItemInputVO.setType(Integer.parseInt(personCsv[1]));
				portfolioItemInputVO.setLongVolume(Double.parseDouble(personCsv[2]));
				portfolioItemInputVO.setShortVolume(Double.parseDouble(personCsv[3]));

				// adding objects to a list
				portfolioInputList.add(portfolioItemInputVO);
				}
				count++;
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return portfolioInputList;
	}
	
	
	public void outputMarketValuesReport(List<PortfolioItemInputVO> inputList, String csvOutPutPath) throws IOException {
		PrintWriter writer  = new PrintWriter(csvOutPutPath);
		 writer.println("Market Value of the entire profolio as at " + LocalDateTime.now().format(dtFormatter));
		 String header = "Ticker,Stock name,Long volume,Short volume,Current Price,"
		 		+ "Market Value for long position,Market Value for short position,Total Net Asset Value";
		 writer.println(header);
	        for (PortfolioItemInputVO inputListItem : inputList) {
	            writer.println(
	            				inputListItem.getTicker()+","+
	            				inputListItem.getStockName()+","+
	            				inputListItem.getLongVolume()+","+
	            				inputListItem.getShortVolume()+","+
	            				"\""+dollarFormatter.format(inputListItem.getUpdatedPrice())+"\""+","+
	            				"\""+dollarFormatter.format(inputListItem.getLongVolume()*inputListItem.getUpdatedPrice())+"\""+","+
	            				"\""+dollarFormatter.format(inputListItem.getShortVolume()*inputListItem.getUpdatedPrice())+"\""+","+
	            				"\""+dollarFormatter.format(inputListItem.getNav())+"\""
	            		);
	        }
	        writer.write(",,,,,,,"+ "\""+dollarFormatter.format(inputList.stream().mapToDouble(l->l.getNav()).sum())+"\"");
	        writer.close();
	}

}
