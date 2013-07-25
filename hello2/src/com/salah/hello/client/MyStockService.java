package com.salah.hello.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.salah.hello.shared.Stock;

@RemoteServiceRelativePath("myStockService")
public interface MyStockService extends RemoteService {

	List<Stock> getListStock();

}
