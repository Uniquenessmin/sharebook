package com.newer.sharebook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.Book;

@Mapper
public interface BookMapper {

	@Select("select * from book limit 8")
	public List<Book> findall();

	// 根据id获得指定书籍
	@Select("select * from book where book_id = #{bookid}")
	public Book getBook(@Param("bookid") int bookid);

	// 根据种类获得书籍列表
	@Select("select * from book where category = #{category}")
	public List<Book> getBooks(@Param("category") int category);

	// 根据书籍名称或者是书籍作者检索图书
	@Select("select * from book where book_name=#{check_book_info} OR book_author=#{check_book_info}")
	public List<Book> getBooks_Name(@Param("check_book_info") String check_book_info);

}
