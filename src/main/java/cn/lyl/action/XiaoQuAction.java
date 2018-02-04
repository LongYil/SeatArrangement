package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.XiaoQu;
import cn.lyl.service.XiaoQuSevc;
import cn.lyl.service.XueXiaoSevc;


public class XiaoQuAction extends BasicAction implements ModelDriven<XiaoQu>{

	@Autowired
	private XiaoQu xq;
	
	@Autowired
	private XiaoQuSevc xqs;
	
	private static String XXBH;
	@Autowired
	private XueXiaoSevc xxs;
	
	private List<XiaoQu> listxiaoqu = new ArrayList<XiaoQu>();
	
	@Override
	public XiaoQu getModel(){
		return xq;
	}
	
	public String save(){
		
		xqs.save(xxs.findById(XXBH), xq);
		listxiaoqu = xqs.findAll(XXBH);
		return "findall";
		
	}
	
	
	public String findAll(){
		listxiaoqu = xqs.findAll(XueXiaoActions.yonghuxuexiao.getXXBH().toString());
		return "findall";
	}
	public String add(){
		listxiaoqu = xqs.findAll(XueXiaoActions.yonghuxuexiao.getXXBH().toString());
		return "add";
	}
	
	
	public String delete(){
		return NONE;
	}
	

	public XiaoQu getXq() {
		return xq;
	}

	public void setXq(XiaoQu xq) {
		this.xq = xq;
	}

	public String getXXBH() {
		return XXBH;
	}

	public void setXXBH(String xXBH) {
		XXBH = xXBH;
	}

	public List<XiaoQu> getListxiaoqu() {
		return listxiaoqu;
	}

	public void setListxiaoqu(List<XiaoQu> listxiaoqu) {
		this.listxiaoqu = listxiaoqu;
	}


}
