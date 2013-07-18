package com.mkyong.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.mkyong.bean.Stock;
import com.mkyong.ws.HelloWorld;
import com.mkyong.ws.StockService;

public class HelloWorldClient{
	
	public static void main(String[] args) throws Exception {
	   
		URL url;
		Service service;
		Stock[] list = null;
		try {
			url = new URL("http://localhost:8080/webServiceMetro/stock?wsdl");

			QName qname = new QName("http://ws.mkyong.com/", "StockServiceImplService");

			 service = Service.create(url, qname);
			 
			 StockService stock = service.getPort(StockService.class);
				
			 list= stock.getAllStock();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		
       
    }

}
