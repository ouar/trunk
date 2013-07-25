package com.salah.hello.client;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.salah.hello.shared.Stock;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


//Cette interface expose les propriétées du tableau
public interface StockProperties extends PropertyAccess<Stock> {
	
	@Path("id")
	ModelKeyProvider<Stock> key();
	//Cette annotaion fait le lien avec la propriété du bean Stock	
	@Path("name")
	ModelKeyProvider<Stock> nameLabel();
	
	ValueProvider<Stock, String> name();
	
	ValueProvider<Stock, String> symbol();
	
	ValueProvider<Stock, Double> last();
	
	ValueProvider<Stock, Date> updatedDate();

}
