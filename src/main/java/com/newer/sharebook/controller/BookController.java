package com.newer.sharebook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.BookMapper;
import com.newer.sharebook.mapper.CategoryMapper;
import com.newer.sharebook.pojo.Book;
import com.newer.sharebook.pojo.Category;

@Controller
public class BookController {

	@Autowired
	BookMapper mapper;
	@Autowired
	CategoryMapper cmapper;

	@GetMapping("/book/all")
	@ResponseBody
	public List<Book> lists() {
		return mapper.findall();
	}

	// 跳转书籍主页
	@GetMapping("/book/{bookid}")
	public String getBookMain(@PathVariable int bookid, HttpServletRequest req) {
		Book book = mapper.getBook(bookid);

		// 获得book的种类
//		Category cate = setCates(book.getCategory());
//		book.setCate(cate);
		// System.out.println(cate);
		// System.err.println(cmapper);
		System.out.println(book.toString());
		req.getSession().setAttribute("book", book);

		return "/bookinfo.html";
	}

	// 指定书籍信息
	@GetMapping("/bookmsg")
	@ResponseBody
	public Book getBookInfo(HttpServletRequest req) {

		return (Book) req.getSession().getAttribute("book");
	}

	// 分类查询
	@GetMapping("/category/{category}")
	public String getBooks(@PathVariable int category, HttpServletRequest req) {
		List<Book> books = mapper.getBooks(category);

		req.getSession().setAttribute("books", books);

		return "/search1.html";
	}

	// 搜索结果
		@GetMapping("/search")
		public String searchBooks( HttpServletRequest req) {
			HttpSession session = req.getSession();
			String input = req.getParameter("input");
			List<Book> books = mapper.getBooks_Name(input);
			session.setAttribute("books", books);
			if(books.size()==0) {
				//搜索分类
				Category cate = cmapper.getCid(input);
				return "/category/"+cate.getCategory_id();
			}
			
			System.out.println(books);
			return "/search1.html";
		}


	// 搜索页面要显示的书籍列表
	@GetMapping("/getbooks")
	@ResponseBody
	public List<Book> search(HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<Book> list = (List<Book>) session.getAttribute("books");
		System.out.println((List<Book>) session.getAttribute("books"));
		return list;

	}

}
