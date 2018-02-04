package cn.lyl.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import cn.lyl.entity.YongHu;
import cn.lyl.service.YongHuSevc;


public class YongHuAction extends BasicAction  implements ModelDriven<YongHu>{

	@Autowired
	private YongHu yh ;
	@Autowired
	private YongHuSevc yhs;


	//用户登录手机号和密码
	private String upass;
	private String utel;

	private String username;
	private String usercode;

	public static YongHu yonghu;
	@Override
	public YongHu getModel() {
		return yh;
	}

	public String save(){
		yonghu = yh;
		username = yh.getYHMC();
		yhs.save(yh);
		return "login";
	}

	public String find(){

		return "";
	}

	public String login(){
		yh = yhs.login(utel, upass);
		if(yh==null){
			return "pleaselogin";
		}else{
			yonghu = yh;
			username = yh.getYHMC();
			usercode = String.valueOf(yh.getYHBH());
			return "login";
		}
	}

	public String tuichu(){
		this.yonghu=null;
		return "pleaselogin";
	}






	public YongHu getYh() {
		return yh;
	}

	public void setYh(YongHu yh) {
		this.yh = yh;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getUtel() {
		return utel;
	}

	public void setUtel(String utel) {
		this.utel = utel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}
