package com.newer.sharebook.pojo;

import lombok.Data;

@Data
public class Mark {


	int mark_id;
	int user_id;
	String user_name;
	int book_id;
	String book_name;
	double mark_score;
	
	public int getMark_id() {
		return mark_id;
	}
	public void setMark_id(int mark_id) {
		this.mark_id = mark_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
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
	public double getMark_score() {
		return mark_score;
	}
	public void setMark_score(double mark_score) {
		this.mark_score = mark_score;
	}

	
}
