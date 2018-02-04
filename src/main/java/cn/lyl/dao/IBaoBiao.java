package cn.lyl.dao;

import java.util.List;

import cn.lyl.entity.BaoBiao;
import cn.lyl.entity.YongHu;

public interface IBaoBiao {
	public void save(BaoBiao arg1,YongHu arg2);
	public List<BaoBiao> findAll(String arg);
}
