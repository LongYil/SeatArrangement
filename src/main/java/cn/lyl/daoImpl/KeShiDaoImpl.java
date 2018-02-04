package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IKeShiDao;
import cn.lyl.entity.KeShi;
import cn.lyl.entity.LouDong;

@Repository(value="keShiDaoImpl")
public class KeShiDaoImpl extends UnusualDaoImpl<LouDong, KeShi> implements IKeShiDao{

	@Override
	public void save(LouDong entity1, KeShi entity2) {
		entity2.setLoudong(entity1);
		ht.saveOrUpdate(entity2);
	}

	@Override
	public void update(LouDong entity1, KeShi entity2) {

		
	}

	@Override
	public KeShi find(String arg) {
		return ht.get(KeShi.class, Integer.parseInt(arg));
	}

	@Override
	public List<KeShi> findAll(String arg) {
		return (List<KeShi>) ht.find("from KeShi where loudong = "+arg+"");
	}
	
}
