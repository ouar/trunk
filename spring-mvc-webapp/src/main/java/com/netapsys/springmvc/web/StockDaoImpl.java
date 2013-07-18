package com.netapsys.springmvc.web;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("stockDao")
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao {

	public void save(Stock stock) {
		getHibernateTemplate().save(stock);
	}

	public void update(Stock stock) {
		getHibernateTemplate().update(stock);
	}

	public void delete(Stock stock) {
		getHibernateTemplate().delete(stock);
	}

	public List<Stock> findByStockCode(String stockCode) {
		@SuppressWarnings("unchecked")
		List<Stock> list = getHibernateTemplate().find("from Stock");
		return list;
	}

}
