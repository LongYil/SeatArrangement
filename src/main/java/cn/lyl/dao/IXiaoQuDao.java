package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.XiaoQu;

public interface IXiaoQuDao {
	
public XiaoQu find(String arg);

public List<XiaoQu> findAll(String arg);
}