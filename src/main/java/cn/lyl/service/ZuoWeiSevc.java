package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.ZuoWeiDaoImpl;
import cn.lyl.entity.KeShi;
import cn.lyl.entity.ZuoWei;

@Transactional
@Service(value="zuoWeiSevc")
public class ZuoWeiSevc extends UnusualSevc<KeShi, ZuoWei> {

	@Autowired
	private ZuoWeiDaoImpl zwdi;
	
	@Override
	public void save(KeShi arg1, ZuoWei arg2) {
		zwdi.save(arg2, arg1);
	}

	@Override
	public KeShi find(String arg) {

		return null;
	}
	
	public List<ZuoWei> findAll(String arg){
		return zwdi.findAll(arg);
	}

}
