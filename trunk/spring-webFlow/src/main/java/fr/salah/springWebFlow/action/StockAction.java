package fr.salah.springWebFlow.action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mkyong.bean.Stock;
import com.mkyong.ws.StockService;

public class StockAction {

	public List<Stock> getAllStocks() {
		URL url;
		Service service;
		Stock[]  list = null;
		List<Stock>  listStock = new ArrayList<Stock>();
		try {
			url = new URL("http://localhost:8080/webServiceMetro/stock?wsdl");

			QName qname = new QName("http://ws.mkyong.com/", "StockServiceImplService");

			 service = Service.create(url, qname);
			 
			 StockService stock = service.getPort(StockService.class);
				
			 list= stock.getAllStock();
			 
			 listStock = Arrays.asList(list);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		return listStock;

		 
	}

}
