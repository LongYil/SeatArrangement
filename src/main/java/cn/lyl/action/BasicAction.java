package cn.lyl.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class BasicAction extends ActionSupport implements SessionAware {
	
	private Map sesion;
	@Override
	public void setSession(Map<String, Object> arg) {
		this.sesion = arg;
	}
	public Map getSesion() {
		return sesion;
	}
	public void setSesion(Map sesion) {
		this.sesion = sesion;
	}

}