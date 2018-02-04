package cn.lyl.dao;

public interface IUnusualDao<X,Y> {
	public void save(X entity1,Y entity2);
	public void update(X entity1,Y entity2);
}
