package com.salah.hello.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.salah.hello.client.MyStockService;
import com.salah.hello.shared.Stock;

public class MyStockServiceImpl extends RemoteServiceServlet implements MyStockService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Stock> getListStock() {
		List<Stock> ar = new ArrayList<Stock>();
		ar.add(new Stock(1, 5.0, "ouar salah", "symbol1", new Date()));
		ar.add(new Stock(2, 8.0, "atkin", "symbol2", new Date()));
		ar.add(new Stock(3, 9.0, "perrins", "symbol3", new Date()));
		return ar;
	}

}
