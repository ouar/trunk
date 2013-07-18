package com.mkyong.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.mkyong.bean.Stock;


//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface StockService {

	
	@WebMethod Stock[] getAllStock();
}
