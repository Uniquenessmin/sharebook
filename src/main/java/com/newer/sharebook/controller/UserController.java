package com.newer.sharebook.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newer.sharebook.mapper.UserMapper;
import com.newer.sharebook.pojo.User;

@Controller
public class UserController {

	@Autowired
	UserMapper usermapper;

	/*
	 * 添加新用户
	 */
	@GetMapping("/adduser")
	public String add(HttpServletRequest req) {
		// 获得想要创建的用户名，用户密码，用户创建时间
		String user_name = req.getParameter("username");
		String user_pwd = req.getParameter("password");
		System.out.println(user_name);
		System.out.println(user_pwd);
		Date register = new Date();
		int num = usermapper.count(user_name);
		if (num == 0) {
			usermapper.add(user_name, user_pwd);
			System.out.println("ok.........");
			return "/login";
		} else {
			return "/register";
		}
	}
	
	//得到当前登录的用户
	@GetMapping("/thisUser")
	@ResponseBody
	public User getUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		return user;
	}

	//  更改密码
	@PostMapping("/updatepwd")
	@ResponseBody
	public String updatepwd(@RequestBody HashMap<String, String> map,HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		System.out.println("正在修改密码....");
		System.out.println(map);
		//旧密码验证
		if (!map.get("oldpwd").equals(user.getUser_pwd())) {
			return "erropwd";
		}

		if (!map.get("pwd1").equals(map.get("pwd2"))) {
			return "noequal";
		}

		if (map.get("pwd1").equals(map.get("pwd2"))) {

			usermapper.updatepwd(user_id, map.get("pwd2"));
			user.setUser_pwd(map.get("pwd2"));
			session.setAttribute("user", user);
		System.out.println("修改密码成功后："+user);
		}

		return "ok";
		
	}

	//更新用户信息
	@GetMapping("/userset")
	public String updateUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		int user_id = user.getUser_id();
		String user_name = req.getParameter("user_name");
		String sign = req.getParameter("sign");
		
		usermapper.updateUser(user_id, user_name, sign);
		user.setSign(sign);
		user.setUser_name(user_name);
		session.setAttribute("user", user);
		return "redirect:/mypage/myinfo.html";
	}
	
}
