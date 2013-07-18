package com.mkyong.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.context.ApplicationContext;

import com.mkyong.bean.Stock;
import com.mkyong.hibernate.ConnectionHibernate;
import com.mkyong.service.persistance.StockBo;

//Service Implementation Bean

@WebService(endpointInterface = "com.mkyong.ws.StockService")
public class StockServiceImpl implements StockService {

	@Override
	public Stock[] getAllStock() {
		ApplicationContext appContext = ConnectionHibernate.getInstance().getAppContext();

		StockBo stockBo = (StockBo) appContext.getBean("stockBo");

		ArrayList<Stock> listeStock = new ArrayList<Stock>();
		
		/** select **/
		List<Stock> list = stockBo.findByStockCode("7668");
		listeStock.addAll(list);
		Stock[] beans = new Stock[listeStock.size()];
		for (int i = 0; i < listeStock.size(); i++) {
			beans[i]=listeStock.get(i);
		}	
			
		return beans;
	}

}
