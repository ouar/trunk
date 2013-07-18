package com.mkyong.service.persistance;

import java.util.List;

import com.mkyong.bean.Stock;

public interface StockBo {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	List<Stock>  findByStockCode(String stockCode);
}
