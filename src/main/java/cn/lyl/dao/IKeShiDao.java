package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.KeShi;

public interface IKeShiDao {

	public KeShi find(String arg);
	public List<KeShi> findAll(String arg);
	
}
