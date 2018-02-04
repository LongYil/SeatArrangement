package cn.lyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.YongHuDaoImpl;
import cn.lyl.entity.YongHu;


@Transactional
@Service(value="YongHuSevc")
public class YongHuSevc extends CommonSevc<YongHu>{
	
	@Autowired
	private YongHuDaoImpl yhi;

	@Override
	public void save(YongHu arg1) {
		yhi.save(arg1);		
	}


	public void update(YongHu arg1) {
		
	}


	@Override
	public YongHu find(String arg1) {

		return null;
	}
	
	public YongHu login(String arg1,String arg2){
		return yhi.login(arg1, arg2);
	}
}