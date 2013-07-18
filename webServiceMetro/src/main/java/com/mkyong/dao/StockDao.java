package com.mkyong.dao;

import java.util.List;

import com.mkyong.bean.Stock;

public interface StockDao {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	List<Stock>  findByStockCode(String stockCode);
}
