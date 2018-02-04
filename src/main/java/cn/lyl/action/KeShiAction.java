package cn.lyl.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lyl.entity.KeShi;
import cn.lyl.entity.LouDong;
import cn.lyl.entity.ZuoWei;
import cn.lyl.service.KeShiSevc;
import cn.lyl.service.LouDongSevc;
import cn.lyl.service.ZuoWeiSevc;
import net.sf.json.JSONObject;

public class KeShiAction extends BasicAction {

	private String resultinfo;
	private String resultinfo1;
	@Autowired
	private KeShi keShi;

	private ZuoWei zuoWei;
	@Autowired
	private ZuoWeiSevc zws;
	@Autowired
	private KeShiSevc kss;
	@Autowired
	private LouDongSevc lds;

	private String kmcode;
	private static String LDBH;
	private static String KSMC;
	private static String KSHS;
	private static String KSLS;
	private List<String> listhang = new ArrayList<String>();
	private List<String> listlie = new ArrayList<String>();
	private List<String> temp = new ArrayList<String>();

	private List<KeShi> listKeShi = new ArrayList<KeShi>();
	private List<LouDong> listLouDong = new ArrayList<LouDong>();
	private LouDong louDong;

	public String findAll(){
		listKeShi = kss.findAll(LDBH);
		return "findall";
	}




	public String shengCheng(){

		for(int i=0;i<(Integer.parseInt(KSHS));i++){
			listhang.add(String.valueOf(i));
		}

		for(int i=0;i<(Integer.parseInt(KSLS));i++){
			listlie.add(String.valueOf(i));
		}

		return "shengcheng";
	}


	public String save(){
		//1：可用座位      0：不可用座位
		HttpServletRequest request =  ServletActionContext.getRequest();
		System.out.println("保存课室座位信息:"+"楼栋编号:"+LDBH+"课室名称"+KSMC+"课室行数"+KSHS+"课室列数"+KSLS);
		String[] zuowei = request.getParameterValues("zuowei");
		String[] guodao = request.getParameterValues("guodao");

		int hang = Integer.parseInt(KSHS);
		int lie = Integer.parseInt(KSLS);
		louDong = lds.find(LDBH);

		keShi.setKSMC(louDong.getLDJC()+KSMC);
		keShi.setKSHS(hang);
		keShi.setKSLS(lie);
		StringBuffer tempKSGDL = new StringBuffer("");
		for(int i=0;i<guodao.length;i++){
			tempKSGDL.append(guodao[i]+"##");
		}
		keShi.setKSGDL(tempKSGDL.toString());
		String[] tempinfo = (kss.sortMethod1(keShi, zuowei)).split("##");

		keShi.setKSZS(Integer.parseInt(tempinfo[0]));
		keShi.setKSKZ1(tempinfo[1]);
		keShi.setKSKZ2(Integer.parseInt(tempinfo[2]));
		keShi.setKSKZ3(Integer.parseInt(tempinfo[3]));
		keShi.setKSKZ4(Integer.parseInt(tempinfo[4]));

		kss.save(keShi, louDong);
		kss.saveAllZuoWei(keShi,zuowei);
		listKeShi = kss.findAll(LDBH);
		return "save";
	}


	public String bianpai() throws Exception{
		kss.executeAllArrange(kmcode);
		return "bianpai";
	}



	public String getLouDong(){
		HttpServletRequest request =  ServletActionContext.getRequest();
		String xiaoqu = request.getParameter("xxbh");
		listLouDong = lds.findAll(xiaoqu);
		JSONObject jo = new JSONObject();
		StringBuffer loudonginfo = new StringBuffer();
		for(int i=0;i<listLouDong.size();i++){
			loudonginfo.append("{'id':'"+listLouDong.get(i).getLDBH()+"','name':'"+listLouDong.get(i).getLDJC()+"'}");
			if(i!=(listLouDong.size()-1)){
				loudonginfo.append(",");
			}
		}

		jo.put("info", "["+loudonginfo+"]");//{'name':'李银龙'},{'name':'邓海京'}
		this.resultinfo = jo.toString();
		return "ajaxresult";
	}


	public String getKeShi(){
		HttpServletRequest request =  ServletActionContext.getRequest();
		String loudong = request.getParameter("ldbh");
		System.out.println("ajax请求提交成功"+loudong);
		listKeShi = kss.findAll(loudong);
		JSONObject jo = new JSONObject();
		StringBuffer keshiinfo = new StringBuffer();
		for(int i=0;i<listKeShi.size();i++){
			keshiinfo.append("{'id':'"+listKeShi.get(i).getKSBH()+"  ','name':'"+listKeShi.get(i).getKSMC()+"【"+listKeShi.get(i).getKSZS()+"】"+"'}");
			if(listKeShi.get(i).getKSKZ1().equals("1")){
				keshiinfo.append(",{'id':'"+listKeShi.get(i).getKSBH()+"  ','name':'"+listKeShi.get(i).getKSMC()+"【"+listKeShi.get(i).getKSKZ2()+","+listKeShi.get(i).getKSKZ3()+"】"+"'}");
			}
			if(i!=(listLouDong.size()-1)){
				keshiinfo.append(",");
			}
		}

		jo.put("info", "["+keshiinfo+"]");//{'name':'李银龙'},{'name':'邓海京'}
		this.resultinfo1 = jo.toString();
		return "ajaxresult1";
	}














	public String getLDBH() {
		return LDBH;
	}

	public void setLDBH(String lDBH) {
		LDBH = lDBH;
	}

	public String getKSMC() {
		return KSMC;
	}

	public void setKSMC(String kSMC) {
		KSMC = kSMC;
	}

	public String getKSHS() {
		return KSHS;
	}

	public void setKSHS(String kSHS) {
		KSHS = kSHS;
	}

	public String getKSLS() {
		return KSLS;
	}

	public void setKSLS(String kSLS) {
		KSLS = kSLS;
	}

	public List<String> getListhang() {
		return listhang;
	}

	public void setListhang(List<String> listhang) {
		this.listhang = listhang;
	}

	public List<String> getListlie() {
		return listlie;
	}

	public void setListlie(List<String> listlie) {
		this.listlie = listlie;
	}

	public List<KeShi> getListKeShi() {
		return listKeShi;
	}

	public void setListKeShi(List<KeShi> listKeShi) {
		this.listKeShi = listKeShi;
	}

	public String getResultinfo() {
		return resultinfo;
	}

	public void setResultinfo(String resultinfo) {
		this.resultinfo = resultinfo;
	}

	public String getResultinfo1() {
		return resultinfo1;
	}

	public void setResultinfo1(String resultinfo1) {
		this.resultinfo1 = resultinfo1;
	}

	public List<LouDong> getListLouDong() {
		return listLouDong;
	}

	public void setListLouDong(List<LouDong> listLouDong) {
		this.listLouDong = listLouDong;
	}

	public String getKmcode() {
		return kmcode;
	}

	public void setKmcode(String kmcode) {
		this.kmcode = kmcode;
	}






}
