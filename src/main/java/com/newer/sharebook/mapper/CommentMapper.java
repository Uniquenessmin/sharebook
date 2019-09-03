package com.newer.sharebook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.Comment;

@Mapper
public interface CommentMapper {

	// userid---->comments
	@Select("select * from comment where user_id=#{userid} ")
	public List<Comment> findCommentall(@Param("userid")int userid);

	//bookid--->comments
	@Select("select * from comment where book_id=#{book_id}")
	public List<Comment> findCommentBid(int book_id);

	//加书评
	@Insert("insert into comment(user_id,book_id,comment_context,date) "
			+ "values(#{user_id},#{book_id},#{comment_context},now())")
	public boolean insert(Comment com);
	
	// 对评论中的数据数量进行统计,返回保存book_id的列表;
		@Select("select book_id from sharebook.comment group by book_id order by count(*) desc;")
		public List<Integer> booksort();
}
