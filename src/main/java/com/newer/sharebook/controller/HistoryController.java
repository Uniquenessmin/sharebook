package com.newer.sharebook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.BookMapper;
import com.newer.sharebook.mapper.HistoryMapper;
import com.newer.sharebook.pojo.Book;
import com.newer.sharebook.pojo.History;
import com.newer.sharebook.pojo.User;

@Controller
public class HistoryController {

	@Autowired
	HistoryMapper mapper;

	@Autowired
	BookMapper bmapper;

	@GetMapping("/history/all")
	@ResponseBody
	public List<History> getMyHistory(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		int userid = user.getUser_id();
		System.out.println("userid:" + userid);

		List<History> list = mapper.getMyList(userid);
		for (History h : list) {
			Book b = bmapper.getBook(h.getBook_id());
			h.setBook(b);
		}
		System.out.println(list);
		return list;
	}

	// 获得历史最多的前几条记录,对应的书籍
	@GetMapping("/booksorthistory")
	@ResponseBody
	public List<Book> bookhistorysort() {
		List<Integer> lists = mapper.bookhistorysort();
		List<Book> books = new ArrayList<Book>();
		System.out.println("历史阅读最多：");
		for(int i=0;i<lists.size()&&i<5;i++)  {
			books.add(bmapper.getBook(lists.get(i)));
			System.out.println(books.get(i));
		}
		return books;
	}

}
