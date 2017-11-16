package com.hancy.dao;

import java.util.List;

import com.hancy.model.Items;

public interface ItemsMapper {

	//查找所有商品
	public List<Items> findItemByAll();
	//模糊查询
	public List<Items> findItemsByName(String name);
	//ID查询
	public Items findItemsById(Integer id);
	//修改
	public void updateItems(Items items);
	//删除
	public void deleteItems(Integer[] ids);
	//批量修改
	public void batchupdateItems(List<Items> list);
}
