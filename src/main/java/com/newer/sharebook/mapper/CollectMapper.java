package com.newer.sharebook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.Collect;

@Mapper
public interface CollectMapper {
	
	//查询收藏列表
	@Select("select * from collect where user_id=#{userid}")
	public List<Collect> findBookselfall(@Param("userid")int userid);
	
	//查询收藏项
	@Select("select * from collect where user_id=#{user_id} and book_id=#{book_id}")
	public Collect findBooksel(Collect c);

	//收藏
	@Insert("insert into collect(user_id,book_id) values(#{user_id},#{book_id})")
	public boolean collect(Collect col);

}
