package com.newer.sharebook.pojo;

import org.springframework.beans.factory.annotation.Autowired;

import com.newer.sharebook.mapper.CategoryMapper;

import lombok.Data;


public class Book {
	
	
	int book_id;
	String book_name;
	String book_author;
	String sharer;
	int  category;
	Category cate;
	String describute;
	//封面
	String book_icon;
	//书的来源
	String book_source;
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getSharer() {
		return sharer;
	}
	public void setSharer(String sharer) {
		this.sharer = sharer;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public Category getCate() {
		return cate;
	}
	public void setCate(Category cate) {
		this.cate = cate;
	}
	public String getDescribute() {
		return describute;
	}
	public void setDescribute(String describute) {
		this.describute = describute;
	}
	public String getBook_icon() {
		return book_icon;
	}
	public void setBook_icon(String book_icon) {
		this.book_icon = book_icon;
	}
	public String getBook_source() {
		return book_source;
	}
	public void setBook_source(String book_source) {
		this.book_source = book_source;
	}
	
	
}
