package com.hancy.model;

import java.io.Serializable;
import java.util.List;

public class QueryVo  implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private List<Items> list;
	
	private Items items;

	public List<Items> getList() {
		return list;
	}

	public void setList(List<Items> list) {
		this.list = list;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
}
