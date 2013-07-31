package fr.icdc.dei.client.vue;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableResources extends CellTable.Resources {
	@Source("MyCellTable.css")
	CellTable.Style cellTableStyle();
}
