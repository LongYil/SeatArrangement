package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.BaoBiaoDaoImpl;
import cn.lyl.entity.BaoBiao;
import cn.lyl.entity.YongHu;

@Transactional
@Service(value="baoBiaoSevc")
public class BaoBiaoSevc extends UnusualSevc<BaoBiao, YongHu> {
	
	@Autowired
	private BaoBiaoDaoImpl bbdi;
	
	@Override
	public void save(BaoBiao arg1, YongHu arg2) {
		bbdi.save(arg1, arg2);		
	}

	@Override
	public BaoBiao find(String arg) {
		return bbdi.find(arg);
	}
	public List<BaoBiao> findAll(String arg){
		return bbdi.findAll(arg);
	}
	
	
	public void delete(String arg){
		bbdi.delete(this.find(arg));
	}
	
	
	
}
