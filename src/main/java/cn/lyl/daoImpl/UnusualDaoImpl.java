package cn.lyl.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import cn.lyl.dao.IUnusualDao;

public abstract class UnusualDaoImpl<X,Y> implements IUnusualDao<X, Y> {
	
	@Autowired
	public HibernateTemplate ht;
	
	@Override
	public abstract void save(X entity1, Y entity2) ;

	@Override
	public abstract void update(X entity1, Y entity2) ;
	
}
