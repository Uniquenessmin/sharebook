package com.newer.sharebook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.BookMapper;
import com.newer.sharebook.mapper.CollectMapper;
import com.newer.sharebook.pojo.Collect;
import com.newer.sharebook.pojo.User;

@Controller
public class CollectController {

	@Autowired
	CollectMapper collectmapper;
	
	@Autowired
	BookMapper bmapper;
	
	//我的书架
	@GetMapping("/bookself/all")
	@ResponseBody
	public List<Collect> B_list(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		List<Collect> list =  collectmapper.findBookselfall(user.getUser_id());
		for(Collect c: list) {
			c.setBook(bmapper.getBook(c.getBook_id()));
		}
		session.setAttribute("collections", list);//存收藏的书籍列表
		return list;
	}
	
	//添加收藏
	@PostMapping("/collect")
	@ResponseBody
	public Map<String, Object> addCollection(@RequestBody Collect newcol,HttpServletRequest req) {
		System.out.println("添加收藏："+newcol);
		
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		//未登录
		if(u==null) {
			map.put("url", "/login");
			map.put("msg", "未登录，请登录！！！");
			session.setAttribute("lastpage", "/bookinfo.html");
			
		}else {
			//已经登录
			String str = "/book/"+newcol.getBook_id();
			map.put("url", str);
			if(collectmapper.findBooksel(newcol)!=null) {
				//已经收藏了
				map.put("msg", "已经收藏，请勿重复操作！");
			}else {
				//未收藏
				collectmapper.collect(newcol);
				map.put("msg", "收藏成功！");
			}
	
		}	
		return map;
	}
	
	//

}
