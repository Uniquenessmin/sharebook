package com.newer.sharebook.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.MarkMapper;
import com.newer.sharebook.pojo.Book;
import com.newer.sharebook.pojo.Mark;
import com.newer.sharebook.pojo.User;

@Controller
public class MarkController {
	@Autowired
	MarkMapper mapper;

	/**
	 * 设置评分
	 * 
	 * @param req
	 * @param mark
	 * @return
	 */
	@PostMapping("/setmark")
	@ResponseBody
	public Map<String, Object> addMark(HttpServletRequest req, @RequestBody Mark mark) {
		System.out.println("hello,here is setmark...");
		User user = (User) req.getSession().getAttribute("user");
		int bookid = mark.getBook_id();
		double marks = mark.getMark_score();
		Map<String, Object> map = new HashMap<String, Object>();

		// 如果未登录
		if (user == null) {
			System.out.println("1=========");
			map.put("url", "/login");
			req.getSession().setAttribute("lastpage", "/bookinfo.html");

		} else {
			// 已登录才能评价
			System.out.println("2=========");
			int userid = mark.getUser_id();

			// 判断该用户对该书籍的评论是否已经存在
			// 1 已经存在，则删除处理
			Mark temp = mapper.getMark(bookid, userid);
			System.out.println("temp:" + temp);
			if (temp != null) {
				mapper.remove(bookid, userid);
			}

			// 2 插入
			mapper.addMark(bookid, userid, marks);
			String str = "/book/"+bookid;
			map.put("url", str);
			map.put("marks", marks);

		}
		return map;
	}

	/**
	 * 获得我对本书的评分
	 * 
	 * @param mark
	 * @return
	 */
	@GetMapping("/mymark")
	@ResponseBody
	public double getBMark(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Book book = (Book) session.getAttribute("book");
		User user = (User) session.getAttribute("user");
		int bookid = book.getBook_id();
		if (user != null) {
			int userid = user.getUser_id();

			Mark temp = mapper.getMark(bookid, userid);
			System.out.println("获得评分：" + temp);
			if (temp != null) {
				return temp.getMark_score();
			}
		}
		return 0;
	}

	/**
	 * 获得平均分
	 * @param req
	 * @return
	 */
	@GetMapping("/avg")
	@ResponseBody
	public Map<String,Object> getAvgs(HttpServletRequest req) {
		Map<String,Object> map = new HashMap<>();
		HttpSession session = req.getSession();
		Book book = (Book) session.getAttribute("book");
		System.out.println("book:"+book);
		int bookid = book.getBook_id();
		double s =mapper.getAvg(bookid);
		map.put("score", String.format("%.1f", s));
		return map;
	}

}
