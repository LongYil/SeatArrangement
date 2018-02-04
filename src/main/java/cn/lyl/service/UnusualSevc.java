package cn.lyl.service;

public abstract class UnusualSevc<T,P> {
	public abstract void save(T arg1,P arg2);
	public abstract T find(String arg);
}
