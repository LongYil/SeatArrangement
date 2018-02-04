package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.KeMu;
import cn.lyl.service.KeMuSevc;

public class KeMuAction extends BasicAction implements ModelDriven<KeMu>{
	
	@Autowired
	private KeMuSevc kms;
	@Autowired
	private KeMu keMu;
	
	private List<KeMu> listkemu = new ArrayList<KeMu>();
	public String kmcode;
	
	@Override
	public KeMu getModel() {
		return keMu;
	}

	public String save(){
		kms.save(keMu,YongHuAction.yonghu);
		return null;
	}
	public String findAll(){
		listkemu = kms.findAll(YongHuAction.yonghu.getYHBH().toString());
		return "findall";
	}

	public String delete(){
		kms.delete(kmcode);
		listkemu = kms.findAll(YongHuAction.yonghu.getYHBH().toString());
		return "findall";
	}

	public KeMu getKeMu() {
		return keMu;
	}

	public void setKeMu(KeMu keMu) {
		this.keMu = keMu;
	}

	public List<KeMu> getListkemu() {
		return listkemu;
	}
	
	public void setListkemu(List<KeMu> listkemu) {
		this.listkemu = listkemu;
	}

	public String getKmcode() {
		return kmcode;
	}

	public void setKmcode(String kmcode) {
		this.kmcode = kmcode;
	}

}