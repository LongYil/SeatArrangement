package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.LouDong;

public interface ILouDongDao {

	public List<LouDong> findAll(String arg);
	
}
