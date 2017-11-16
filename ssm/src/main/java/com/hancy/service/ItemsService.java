package com.hancy.service;

import java.util.List;

import com.hancy.model.Items;

public interface ItemsService {
	
	public List<Items> findItemByAll();
	
	public List<Items> findItemsByName(String name);
	
	public Items findItemsById(Integer id);

	public void updateItems(Items items);
	
	public void deleteItems(Integer[] ids);

	public void batchupdateItems(List<Items> list);

}
