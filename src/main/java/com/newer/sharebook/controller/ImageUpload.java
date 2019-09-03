package com.newer.sharebook.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newer.sharebook.mapper.UserMapper;
import com.newer.sharebook.pojo.User;

@Controller
public class ImageUpload {
	
	@Autowired
	UserMapper userMapper;
	
	
	@GetMapping("/image")
	public String image() {
		return "/image.html";
	}
	/*
	 * 实现文件上传
	 */
	@PostMapping("/ImageUpload")
	public String fileUpload(@RequestParam("fileName") MultipartFile file,HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (file.isEmpty()) {
			return "false";
		}
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		// 文件保存的目录
		String path = "D:\\app\\pojo\\sharebook\\src\\main\\resources\\static\\imgs\\head";
		File dest = new File(path + "\\" + fileName);
		
		if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // 保存文件
			User user = (User)session.getAttribute("user");
			int user_id = user.getUser_id();
			String icon="imgs/head/"+fileName;
			System.out.println("更换头像中...:"+icon);
			
			userMapper.updateHead(user_id, icon);
			
			//同时保存到session
			user.setIcon(icon);
			session.setAttribute("user", user);
			System.out.println("更换头像成功！...");
			Thread.sleep(3000);
			return "redirect:/mypage/myinfo.html";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/mypage/myinfo.html";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/mypage/myinfo.html";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/mypage/myinfo.html";
		}
	}

}
