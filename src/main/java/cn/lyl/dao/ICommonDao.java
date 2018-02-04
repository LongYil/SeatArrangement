package cn.lyl.dao;

public interface ICommonDao<T>{
	public void save(T entity);
	public void update(T entity);
	public void delete(T entity);
}
