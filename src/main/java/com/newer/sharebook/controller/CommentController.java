package com.newer.sharebook.controller;

import java.util.ArrayList;
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
import com.newer.sharebook.mapper.CommentMapper;
import com.newer.sharebook.pojo.Book;
import com.newer.sharebook.pojo.Comment;
import com.newer.sharebook.pojo.User;

@Controller
public class CommentController {
	@Autowired
	CommentMapper mapper;
	
	@Autowired
	BookMapper bmapper;

	//  获得我的书评
	@GetMapping("/book/comment/all")
	@ResponseBody
	public List<Comment> C_list(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		return mapper.findCommentall(user.getUser_id());

	}
	
	//  获得此书书评
	@GetMapping("/comments")
	@ResponseBody
	public List<Comment> getBComments(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Book book = (Book)session.getAttribute("book");
		
		return mapper.findCommentBid(book.getBook_id());

	}
	
	//添加书评
	@PostMapping("/addcomm")
	@ResponseBody
	public Map<String, Object> insertComment(@RequestBody Comment comm,HttpServletRequest req) {
		System.out.println("书评："+comm);
		
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		//未登录
		if(u==null) {
			map.put("url", "/login");
			session.setAttribute("lastpage", "/bookinfo.html");
			
		}else {
			//已经登录
			mapper.insert(comm);
			String str = "/book/"+comm.getBook_id();
			
			map.put("url", str);
		}
		
		return map;
	}
	

	// 获得评论最多的前几条记录,对应的书籍
		@GetMapping("/booksort")
		@ResponseBody
		public List<Book> booksort() {
			List<Integer> lists = mapper.booksort();
			List<Book> books = new ArrayList<Book>();
			System.out.println("评论最多：");
			for(int i=0;i<lists.size()&&i<5;i++)  {
				books.add(bmapper.getBook(lists.get(i)));
				System.out.println(books.get(i));
			}
			return books;
		}

}
