package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.LouDong;
import cn.lyl.service.LouDongSevc;

public class LouDongAction extends BasicAction implements ModelDriven<LouDong>{

	@Autowired
	private LouDong loudong;
	
	@Autowired
	private LouDongSevc lds;
	
	private static String XQBH;
	private List<LouDong> listloudong = new ArrayList<LouDong>();
	
	@Override
	public LouDong getModel() {
		
		return loudong;
	}
	
	public String findAll(){
		listloudong = lds.findAll(XQBH);
		return "findall";
	}
	
	public String save(){
		lds.save(loudong, XQBH);
		listloudong = lds.findAll(XQBH);
		return "findall";
	}

	public String findAllLouDong(){
		listloudong = lds.findAllLouDong();
		return "findall";
	}
	
	
	


	public String getXQBH() {
		return XQBH;
	}

	public void setXQBH(String xQBH) {
		XQBH = xQBH;
	}

	public List<LouDong> getListloudong() {
		return listloudong;
	}

	public void setListloudong(List<LouDong> listloudong) {
		this.listloudong = listloudong;
	}

	public LouDong getLoudong() {
		return loudong;
	}

	public void setLoudong(LouDong loudong) {
		this.loudong = loudong;
	}
	
}
