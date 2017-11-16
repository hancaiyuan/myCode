package com.hancy.controller;

import com.hancy.model.Items;
import com.hancy.model.QueryVo;
import com.hancy.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemsController {

	//自动注入ItemsServiceImpl
	@Autowired
	private ItemsService itemsService;
	
	/**
	 * 查询所有商品
	 * @return
	 */
	@RequestMapping(value="/itemlist.action")
	public ModelAndView getItemsList(){
//		测试异常处理器
//		int i = 1/0;
//		if(null == null){
//			throw new MessageException("捕获的异常");
//		}
		List<Items> items = itemsService.findItemByAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("items", items);
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}

/**
	Model/ModelMap也可以向页面返回数据
	Model是一个接口，ModelMap是Model接口的实现类
	1.在参数里直接声明model即可
	2.如果使用Model则可以不使用ModelAndView对象
	3.View对象可以使用String返回值替代，返回jsp的文件名称
 */
	/**
	 * 模糊查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/queryitem.action")
	public String getItemsByName(String name, Model model){
		List<Items> items = itemsService.findItemsByName(name);
		model.addAttribute("items", items);
		return "itemList";
	}
	
	/**
	 * ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView getItemsById(Integer id){
		Items item = itemsService.findItemsById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", item);
		modelAndView.setViewName("editItem");
		return modelAndView;
	}
	
	/**
	 * 修改，使用pojo实体类型接收参数
	 * @param items
	 * @return
	 */
	@RequestMapping(value="/updateitem.action")
	public ModelAndView updateItems(Items items){
		itemsService.updateItems(items);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteitem.action")
	public String deleteItems(Integer[] ids){
		itemsService.deleteItems(ids);
		
		return "itemList";
	}
	
	/**
	 * 批量更新
	 * @param queryVo
	 * @return
	 */
	@RequestMapping(value="/batchupdateitem.action")
	public String batchupdateItems(QueryVo queryVo){
		itemsService.batchupdateItems(queryVo.getList());

		return "redirect:/itemlist.action";
	}
	
	/**
	 * 测试json
	 * 一个方法可配置多个请求地址，用逗号分隔
	 * @param items
	 * @return
	 */
	@RequestMapping(value={"/json.action","/testjson.action"})
	public @ResponseBody Items testjson(@RequestBody Items items){
		System.out.println(items.getName());
		return items;
	}

	/**
	 * 用于接收请求转发
	 * method = RequestMethod.GET表示只适用于get请求
	 * @return
	 */
	@RequestMapping(value="/login.action",method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/**
	 * 登录业务处理
	 * @param username
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/login.action",method = RequestMethod.POST)
	public String login(String username, String password, HttpSession httpSession){
		//自动在cookie放入JSESSIONID
		httpSession.setAttribute("USER_SESSION", username);
		return "redirect:/itemlist.action";
	}
	
}
