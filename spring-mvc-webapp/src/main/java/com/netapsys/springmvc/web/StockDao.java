package com.netapsys.springmvc.web;

import java.util.List;

public interface StockDao {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	List<Stock>  findByStockCode(String stockCode);
}
