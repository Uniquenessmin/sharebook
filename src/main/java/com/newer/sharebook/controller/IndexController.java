package com.newer.sharebook.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.BookMapper;
import com.newer.sharebook.mapper.UserMapper;
import com.newer.sharebook.pojo.User;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

@Controller
public class IndexController {

	@Autowired
	UserMapper usermapper;
	
	@Autowired
	BookMapper bmapper;

	// 首页
	@GetMapping("/")
	public String index(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.err.println(session.getAttribute("user"));
		return "/index.html";
	}

	// 登录
	@GetMapping("/login")
	public String login() {
		return "/login.html";
	}

	// 注册
	@GetMapping("/register")
	public String register() {
		return "/register.html";
	}

	// 退出
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("user", "");
		session.invalidate();
		return "/index.html";
	}

	// 我的主页
	@GetMapping("/mypage")
	public String mypage() {
		return "/mypage/main.html";
	}

	// 登录检验
	// 在登录页面中，当点击登录时，表单会提交到这个get方法中，并判断是否有这个用户，选择重定向。
	@GetMapping("/check")
	public String login(HttpServletRequest req) {
		String user_name = req.getParameter("username");
		String user_pwd = req.getParameter("password");
		User user = usermapper.check(user_name, user_pwd);
		if (user == null) {
			return "/login.html";
		} else {
			System.out.println("user_name:" + user_name);
			System.out.println("=================");
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			String lastpage = (String) session.getAttribute("lastpage");
			if (lastpage != null) {
				return lastpage;
			}
			return "/index.html";
		}
	}

	@GetMapping("/demo/{id}")
	@ResponseBody
	public String reader(@PathVariable int id,HttpServletRequest req) {
		System.out.println("epub文件解析");
		System.err.println(id);
		EpubReader epubReader = new EpubReader();
		// 处理io流路径
		String currentPath = Thread.currentThread().getClass().getResource("/").toString();
		// 当前路径
		System.out.println(currentPath);
		com.newer.sharebook.pojo.Book book1 = (com.newer.sharebook.pojo.Book)req.getSession().getAttribute("book");
		
		// 当前路径下epub包中的文件
		//String epubPath = "D:\\book\\" + "龙族.epub";
		String epubPath = book1.getBook_source();
		// 读取到epub文件所在的路径
		System.out.println(epubPath);
		Book book = null;
		String str = null;
		try {
			InputStream inputStr = new FileInputStream(epubPath);
			book = epubReader.readEpub(inputStr);
			List<Resource> contents = book.getContents();
			System.out.println(contents);
			System.out.println(contents.get(id).getHref());
			str = new String(contents.get(id).getData());
			int firstindex = str.indexOf("<h1");
			int firstindex2 = str.indexOf("<h2");
			int firstindex3 = str.indexOf("<h3");
			int firstindex4 = str.indexOf("<div");
			if (firstindex == -1) {
				firstindex = firstindex2;
			}
			if (firstindex == -1) {
				firstindex = firstindex3;
			}
			if (firstindex == -1) {
				firstindex = firstindex4;
			}
			System.out.println(firstindex);
			int lastindex = str.indexOf("</body>");
			lastindex = lastindex;
			System.out.println(lastindex);
			System.out.println(str.substring(firstindex, lastindex));
			str = str.substring(firstindex, lastindex);
			// System.out.println(new String(contents.get(1).getData()));
			System.out.println(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	@GetMapping("/demo")
	public String demo() {
		return "/reading/demo.html";
	}
}
