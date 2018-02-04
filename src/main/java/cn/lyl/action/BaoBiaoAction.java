package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.BaoBiao;
import cn.lyl.service.BaoBiaoSevc;

public class BaoBiaoAction extends BasicAction implements ModelDriven<BaoBiao> {

	@Autowired
	private BaoBiao baoBiao;
	@Autowired
	private BaoBiaoSevc bbs;
	
	private String bbcode;
	
	
	private List<BaoBiao> listbaobiao = new ArrayList<BaoBiao>();
	@Override
	public BaoBiao getModel() {
		return baoBiao;
	}
	
	
	public String findAll(){
		listbaobiao =  bbs.findAll(YongHuAction.yonghu.getYHBH().toString());
		return "findall";
	}
	
	
	public String shanchu(){
		bbs.delete(bbcode);
		listbaobiao =  bbs.findAll(YongHuAction.yonghu.getYHBH().toString());
		return "findall";
	}
	
	
	
	
	
	
	
	public BaoBiao getBaoBiao() {
		return baoBiao;
	}

	public void setBaoBiao(BaoBiao baoBiao) {
		this.baoBiao = baoBiao;
	}

	public List<BaoBiao> getListbaobiao() {
		return listbaobiao;
	}

	public void setListbaobiao(List<BaoBiao> listbaobiao) {
		this.listbaobiao = listbaobiao;
	}

	public String getBbcode() {
		return bbcode;
	}

	public void setBbcode(String bbcode) {
		this.bbcode = bbcode;
	}
	
	
	
	
	

}
