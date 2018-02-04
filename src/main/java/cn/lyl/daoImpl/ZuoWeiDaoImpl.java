package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IZuoWeiDao;
import cn.lyl.entity.KeShi;
import cn.lyl.entity.ZuoWei;

@Repository(value="zuoWeiDaoImpl")
public class ZuoWeiDaoImpl extends UnusualDaoImpl<ZuoWei, KeShi> implements IZuoWeiDao{

	@Override
	public void save(ZuoWei entity1, KeShi entity2) {
		entity1.setKeshi(entity2);
		ht.save(entity1);
	}

	@Override
	public void update(ZuoWei entity1, KeShi entity2) {
		
		
	}

	@Override
	public List<ZuoWei> findAll(String arg) {
		return (List<ZuoWei>) ht.find("from ZuoWei where keshi = "+arg+"");
	}

}
