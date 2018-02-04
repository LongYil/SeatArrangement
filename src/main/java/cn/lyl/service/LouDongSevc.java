package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.LouDongDaoImpl;
import cn.lyl.daoImpl.XiaoQuDaoImpl;
import cn.lyl.entity.LouDong;

@Transactional
@Service(value="louDongSevc")
public class LouDongSevc extends UnusualSevc<LouDong, String> {

	@Autowired
	private LouDongDaoImpl lddi;
	@Autowired
	private XiaoQuDaoImpl xqdi;
	
	
	@Override
	public void save(LouDong arg1, String arg2) {
		lddi.save(xqdi.find(arg2),arg1);	
	}

	
	@Override
	public LouDong find(String arg) {
		return lddi.find(arg);
	}
	
	public List<LouDong> findAll(String arg){
		return lddi.findAll(arg);
	}
	
	public List<LouDong> findAllLouDong(){
		return lddi.findAllLouDong();
	}
}
