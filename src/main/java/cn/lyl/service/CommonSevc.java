package cn.lyl.service;

public abstract class CommonSevc<T> {
	public abstract void save(T arg);
	public abstract T find(String arg);
}
