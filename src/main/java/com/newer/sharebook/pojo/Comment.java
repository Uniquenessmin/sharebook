package com.newer.sharebook.pojo;

import java.sql.Date;

import lombok.Data;


public class Comment {

	int comment_id;
	int user_id;
	int book_id;
	String comment_context;
	Date date;

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getComment_context() {
		return comment_context;
	}

	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
