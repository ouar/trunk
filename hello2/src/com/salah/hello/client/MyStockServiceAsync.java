package com.salah.hello.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.salah.hello.shared.Stock;

public interface MyStockServiceAsync {

	void getListStock(AsyncCallback<List<Stock>> calback);

}
