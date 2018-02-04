package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.XueXiaoDaoImpl;
import cn.lyl.entity.XueXiao;
import cn.lyl.entity.YongHu;

@Transactional
@Service(value="xueXiaoSevc")
public class XueXiaoSevc extends UnusualSevc<YongHu,XueXiao>{
	@Autowired
	private XueXiaoDaoImpl xxdi;
	
	@Override
	public void save(YongHu arg1, XueXiao arg2) {
		xxdi.save(arg1, arg2);
	}

	@Override
	public YongHu find(String arg) {
		
		return null;
	}
	
	public List<XueXiao> findAll(){
		return xxdi.findAll();
	}
	
	public XueXiao findById(String arg){
		return xxdi.find(arg);
	}
	public void delete(String arg){
		xxdi.delete(arg);
	}
}
