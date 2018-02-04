package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IBanJiDao;
import cn.lyl.entity.BanJi;
import cn.lyl.entity.KeMu;

@Repository(value="banJiDaoImpl")
public class BanJiDaoImpl extends UnusualDaoImpl<BanJi, KeMu> implements IBanJiDao {

	@Override
	public List<BanJi> findAll(String arg) {
		return (List<BanJi>) ht.find("from BanJi where keMu = "+arg+"");
	}

	@Override
	public void save(BanJi entity1, KeMu entity2) {
		entity1.setKeMu(entity2);

		if(entity1.getBjmc2().length()!=0){
			entity1.setSflb("1");
			if(entity1.getBjrs1()<entity1.getBjrs2()){
				;
			}else{
				int tempinfo1 = 0;
				String tempinfo2 = "";
				tempinfo2 = entity1.getBjmc1();
				tempinfo1 = entity1.getBjrs1();
				entity1.setBjrs1(entity1.getBjrs2());
				entity1.setBjmc1(entity1.getBjmc2());
				entity1.setBjrs2(tempinfo1);
				entity1.setBjmc2(tempinfo2);
			}	
			
		}else{
			entity1.setSflb("0");
		}
		ht.save(entity1);
	}

	@Override
	public void update(BanJi entity1, KeMu entity2) {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
