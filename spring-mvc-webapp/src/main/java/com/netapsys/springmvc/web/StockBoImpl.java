package com.netapsys.springmvc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("stockBo")
public class StockBoImpl implements StockBo {

	@Autowired
	StockDao stockDao;

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public void save(Stock stock) {
		stockDao.save(stock);
	}

	public void update(Stock stock) {
		stockDao.update(stock);
	}

	public void delete(Stock stock) {
		stockDao.delete(stock);
	}

	public List<Stock> findByStockCode(String stockCode) {
		return stockDao.findByStockCode(stockCode);
	}
}
