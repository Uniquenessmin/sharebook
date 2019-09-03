package com.newer.sharebook.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.sharebook.pojo.User;

@Mapper
public interface UserMapper {

	// 前端传入用户的姓名,返回用户的各个信心
	@Select("select * from user where user_name=#{user_name} ")
	public User find(@Param("user_name") String user_name);

	// 向user表中插入新用户
	@Insert("insert into user(user_name,user_pwd,register,icon)" + "values(#{user_name},#{user_pwd},now(),'imgs/1.jpg')")
	public void add(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd);

	// 注册检验是否有相同的用户名
	@Select("select count(*) from user where user_name=#{user_name}")
	public int count(@Param("user_name") String user_name);

	// 登录检验，用户名是否存在，密码是否正确。
	@Select("select * from user where user_name=#{user_name} and user_pwd=#{user_pwd}")
	public User check(@Param("user_name") String user_name, @Param("user_pwd") String user_pwd);

	//更新密码
	@Update("update user set user_pwd=#{user_pwd} where user_id=#{user_id}")
	public void updatepwd(@Param("user_id")int user_id,@Param("user_pwd") String user_pwd);
	
	//更改头像
	@Update("update user set icon=#{icon} where user_id=#{user_id}")
	public void updateHead(@Param("user_id")int user_id,@Param("icon") String icon);
	
	//更改用户信息
	@Update("update user set user_name=#{user_name},sign=#{sign} where user_id=#{user_id}")
	public void updateUser(@Param("user_id")int user_id,
			@Param("user_name") String user_name,
			@Param("sign") String sign);
}
