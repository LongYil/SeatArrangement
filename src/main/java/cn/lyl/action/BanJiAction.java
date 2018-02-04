package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.BanJi;
import cn.lyl.entity.XiaoQu;
import cn.lyl.service.BanJiSevc;
import cn.lyl.service.KeMuSevc;

public class BanJiAction extends BasicAction implements ModelDriven<BanJi>{

	@Autowired
	private BanJi banJi;
	@Autowired
	private BanJiSevc bjs;
	@Autowired
	private KeMuSevc kms;
	
	private List<BanJi> listbanji = new ArrayList<BanJi>();
	private List<XiaoQu> listxiaoqu = new ArrayList<XiaoQu>();
	
	public static String kmbh;
	
	@Override
	public BanJi getModel() {
		return banJi;
	}
	
	public String save(){
		bjs.save(banJi, kms.find(kmbh));
		return null;
	}
	
	
	public String findAll(){
		listbanji = bjs.findAll(kmbh);
		return "findall";
	}
	
	
	
	
	
	
	
	
	
	public BanJi getBanJi() {
		return banJi;
	}

	public void setBanJi(BanJi banJi) {
		this.banJi = banJi;
	}

	public List<BanJi> getListbanji() {
		return listbanji;
	}

	public void setListbanji(List<BanJi> listbanji) {
		this.listbanji = listbanji;
	}

	public String getKmbh() {
		return kmbh;
	}

	public void setKmbh(String kmbh) {
		this.kmbh = kmbh;
	}

	public List<XiaoQu> getListxiaoqu() {
		return listxiaoqu;
	}

	public void setListxiaoqu(List<XiaoQu> listxiaoqu) {
		this.listxiaoqu = listxiaoqu;
	}
	
	
	
	
	
	
}
