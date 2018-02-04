package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IXiaoQuDao;
import cn.lyl.entity.XiaoQu;
import cn.lyl.entity.XueXiao;

@Repository(value="xiaoQuDaoImpl")
public class XiaoQuDaoImpl extends UnusualDaoImpl<XueXiao, XiaoQu> implements IXiaoQuDao{

	
	@Override
	public void save(XueXiao entity1, XiaoQu entity2) {
		entity2.setXuexiao(entity1);
		ht.saveOrUpdate(entity2);
	}

	@Override
	public void update(XueXiao entity1, XiaoQu entity2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public XiaoQu find(String arg) {
		
		return ht.get(XiaoQu.class, new Integer(arg));
	}

	@Override
	public List<XiaoQu> findAll(String arg) {
		return (List<XiaoQu>) ht.find("from XiaoQu where xuexiao ="+arg+"");
	}

}
