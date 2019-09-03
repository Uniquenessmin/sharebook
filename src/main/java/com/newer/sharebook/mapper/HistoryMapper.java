package com.newer.sharebook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.History;

@Mapper
public interface HistoryMapper {
	// 查询我的足迹
	@Select("select * from history where user_id=#{user_id}")
	public List<History> getMyList(@Param("user_id") int user_id);

	// 对阅读记录中的数据数量进行统计,返回保存book_id的列表;
		@Select("select book_id from sharebook.history group by book_id order by count(*)  desc;")
		public List<Integer> bookhistorysort();

}
