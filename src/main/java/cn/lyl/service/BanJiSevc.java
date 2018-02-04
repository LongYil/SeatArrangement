package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.BanJiDaoImpl;
import cn.lyl.entity.BanJi;
import cn.lyl.entity.KeMu;

@Transactional
@Service(value="banJiSevc")
public class BanJiSevc extends UnusualSevc<BanJi, KeMu> {
	
	@Autowired
	private BanJiDaoImpl bjdi;
	
	@Override
	public void save(BanJi arg1, KeMu arg2) {
		bjdi.save(arg1, arg2);
	}

	@Override
	public BanJi find(String arg) {
		return null;
	}
	
	public List<BanJi> findAll(String arg){
		return bjdi.findAll(arg);
	}

}
