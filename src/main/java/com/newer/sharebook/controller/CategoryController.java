package com.newer.sharebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.sharebook.mapper.CategoryMapper;
import com.newer.sharebook.pojo.Category;

@RestController
public class CategoryController {
	@Autowired
	CategoryMapper cmapper;
	

	//获得分类列表
	@GetMapping("/category")
	public List<Category> getCategories(){
		List<Category> list = new ArrayList<>();
		list = cmapper.getCates();
		return list;
	}

}
