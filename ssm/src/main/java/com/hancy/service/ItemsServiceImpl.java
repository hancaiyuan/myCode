package com.hancy.service;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hancy.dao.ItemsMapper;
import com.hancy.model.Items;

//@Service自动扫描并注册到spring容器
//@Transactional注解事务
@Service
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class ItemsServiceImpl implements ItemsService {

	@Resource(name="itemsMapper")
	private ItemsMapper itemsMapper;
	
	/**
	 * 查询所有
	 */
	@Override
	public List<Items> findItemByAll() {
		List<Items> items = itemsMapper.findItemByAll();
		return items;
	}

	/**
	 * 模糊查询
	 */
	@Override
	public List<Items> findItemsByName(String name) {
		List<Items> items = itemsMapper.findItemsByName(name);
		return items;
	}
	
	/**
	 * ID查询
	 */
	@Override
	public Items findItemsById(Integer id) {
		Items items = itemsMapper.findItemsById(id);
		return items;
	}

	/**
	 * 修改
	 */
	@Override
	public void updateItems(Items items) {
		itemsMapper.updateItems(items);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteItems(Integer[] ids) {
		itemsMapper.deleteItems(ids);
	}

	/**
	 * 批量修改
	 */
	@Override
	public void batchupdateItems(List<Items> list) {
		itemsMapper.batchupdateItems(list);
	}
	

}
