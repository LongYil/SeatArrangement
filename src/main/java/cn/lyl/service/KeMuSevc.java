package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.KeMuDaoImpl;
import cn.lyl.entity.KeMu;
import cn.lyl.entity.YongHu;


@Transactional
@Service(value="keMuSevc")
public class KeMuSevc extends UnusualSevc<KeMu, YongHu> {

	@Autowired
	private KeMuDaoImpl kmdi;
	@Override
	public void save(KeMu arg2,YongHu arg1) {
		kmdi.save(arg1, arg2);		
	}

	@Override
	public KeMu find(String arg) {
		return kmdi.find(arg);
	}
	
	public List<KeMu> findAll(String arg){
		return kmdi.findAll(arg);
	}
	
	public void delete(String arg){
		kmdi.delete(kmdi.find(arg));
	}
	
}
