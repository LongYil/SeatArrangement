package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.XueXiao;
import cn.lyl.service.XueXiaoSevc;

public class XueXiaoActions extends BasicAction implements ModelDriven<XueXiao>{

	@Autowired
	private XueXiaoSevc xxs;
	@Autowired
	private XueXiao xuexiao;
	public static XueXiao yonghuxuexiao;
	private List<XueXiao> listxuexiao = new ArrayList<XueXiao>();
	
	@Override
	public XueXiao getModel() {
		return xuexiao;
	}
	
	public String save(){
		xxs.save(YongHuAction.yonghu, xuexiao);
		listxuexiao = xxs.findAll();
		return "findAll";
	}

	public String findAll(){
		listxuexiao = xxs.findAll();
		if(listxuexiao.size()>0){
			yonghuxuexiao = listxuexiao.get(0);
		}else{
			;
		}
		
		return "findAll";
	}

	public String delete(){
		xxs.delete(xuexiao.getXXBH().toString());
		listxuexiao = xxs.findAll();
		return "findAll";
	}
	
	
	
	
	
	
	
	
	
	
	public List<XueXiao> getListxuexiao() {
		return listxuexiao;
	}

	public void setListxuexiao(List<XueXiao> listxuexiao) {
		this.listxuexiao = listxuexiao;
	}

}