package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IXueXiaoDao;
import cn.lyl.entity.XueXiao;
import cn.lyl.entity.YongHu;

@Repository(value="xueXiaoDaoImpl")
public class XueXiaoDaoImpl extends UnusualDaoImpl<YongHu, XueXiao> implements IXueXiaoDao {


	@Override
	public XueXiao find(String arg) {
		return ht.get(XueXiao.class, Integer.parseInt(arg));
	}

	@Override
	public void save(YongHu entity1, XueXiao entity2) {
		entity2.setYonghu(entity1);
		ht.saveOrUpdate(entity2);
	}

	@Override
	public void update(YongHu entity1, XueXiao entity2) {
		
	}
	
	public List<XueXiao> findAll(){
		return (List<XueXiao>) ht.find("from XueXiao ");
//		return (List<XueXiao>) ht.find("from XueXiao where yonghu = "+(YongHuAction.yonghu.getYHBH())+"");
	}

	@Override
	public void delete(String arg) {
		ht.delete(find(arg));
	}
	
	
	
	
	
	
	
	
	
	
	
}