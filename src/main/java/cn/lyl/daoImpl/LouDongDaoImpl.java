package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.ILouDongDao;
import cn.lyl.entity.LouDong;
import cn.lyl.entity.XiaoQu;

@Repository(value="louDongDaoImpl")
public class LouDongDaoImpl extends UnusualDaoImpl<XiaoQu, LouDong> implements ILouDongDao {

	@Override
	public List<LouDong> findAll(String arg) {
		return (List<LouDong>) ht.find("from LouDong where xiaoqu = "+arg+"");
	}
	
	public List<LouDong> findAllLouDong(){
		return (List<LouDong>) ht.find("from LouDong ");
	}
	@Override
	public void save(XiaoQu entity1, LouDong entity2) {
		entity2.setXiaoqu(entity1);
		ht.saveOrUpdate(entity2);		
	}

	@Override
	public void update(XiaoQu entity1, LouDong entity2) {
		// TODO Auto-generated method stub
		
	}
	
	public LouDong find(String arg){
		return ht.get(LouDong.class, Integer.parseInt(arg));
	}

}
