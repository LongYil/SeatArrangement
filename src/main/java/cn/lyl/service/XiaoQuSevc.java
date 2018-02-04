package cn.lyl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.daoImpl.XiaoQuDaoImpl;
import cn.lyl.entity.XiaoQu;
import cn.lyl.entity.XueXiao;


@Transactional
@Service(value="xiaoQuSevc")
public class XiaoQuSevc extends UnusualSevc<XueXiao, XiaoQu> {
	
	@Autowired
	private XiaoQuDaoImpl xqdi;
	
	@Override
	public void save(XueXiao arg1, XiaoQu arg2) {
		xqdi.save(arg1, arg2);
	}


	@Override
	public XueXiao find(String arg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<XiaoQu> findAll(String arg){
		return xqdi.findAll(arg);
	}
	

}
