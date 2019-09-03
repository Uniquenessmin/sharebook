package com.newer.sharebook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.sharebook.pojo.Category;

@Mapper
public interface CategoryMapper {
	@Select("select * from category where category_id=#{category_id}")
	public Category getCate(@Param("category_id")int category_id);
	
	@Select("select * from category")
	public List<Category> getCates();
	
	@Select("select * from category where category_name=#{category_name}")
	public Category getCid(@Param("category_name")String category_name);

}
