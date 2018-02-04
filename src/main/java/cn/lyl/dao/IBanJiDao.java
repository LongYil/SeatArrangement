package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.BanJi;

public interface IBanJiDao {
	public List<BanJi> findAll(String arg);
}
