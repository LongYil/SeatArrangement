package cn.lyl.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import cn.lyl.dao.ICommonDao;

public abstract class CommonDaoImpl<T> implements ICommonDao<T> {
	
	@Autowired
	public HibernateTemplate ht;
	
	@Override
	public abstract void save(T entity) ;

	@Override
	public abstract void update(T entity) ;

	@Override
	public abstract void delete(T entity) ;

}
