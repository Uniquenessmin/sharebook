package com.newer.sharebook.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.Mark;


@Mapper
public interface MarkMapper {
	
	//插入评分
	@Insert("insert into mark(book_id,user_id,mark_score) "
			+ "values(#{bookid},#{userid},#{marks})")
	public boolean addMark(@Param("bookid")int bookid,@Param("userid")int userid,@Param("marks")double marks);

	//获得sb对sbook的查询
	@Select("select * from mark where book_id = #{bookid} and user_id=#{userid}")
	public Mark getMark(@Param("bookid")int bookid,@Param("userid")int userid);
	
	//删除
	@Delete("delete from mark where book_id = #{bookid} and user_id=#{userid}")
	public void remove(@Param("bookid")int bookid,@Param("userid")int userid);

	//书籍平均分
	@Select("select avg(mark_score) from sharebook.mark where book_id=#{bookid};")
	public double getAvg(@Param("bookid")int bookid);
}

