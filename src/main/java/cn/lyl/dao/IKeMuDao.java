package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.KeMu;
import cn.lyl.entity.YongHu;

public interface IKeMuDao {
	public void save(YongHu arg1,KeMu arg2);
	public List<KeMu> findAll(String arg);
}
