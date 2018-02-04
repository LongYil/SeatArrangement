package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class XueXiao {
	private Integer XXBH;//学校编号
	private String XXMC;//学校名称
	private String XXJC;//学校简称
	private String XXKZ1;//扩展字段1
	private String XXKZ2;//扩展字段2
	private String XXKZ3;//扩展字段3
	private String XXKZ4;//扩展字段4
	private String XXKZ5;//扩展字段5

	//在学校里面表示所属用户，一个学校只能属于一个用户
	private YongHu yonghu;
	//在学校里面表示所拥有的校区，一个学校可以拥有多个校区
	private Set<XiaoQu> setXiaoQu = new HashSet<XiaoQu>();


	public String getXXMC() {
		return XXMC;
	}
	public void setXXMC(String xXMC) {
		XXMC = xXMC;
	}
	public String getXXJC() {
		return XXJC;
	}
	public void setXXJC(String xXJC) {
		XXJC = xXJC;
	}
	public Integer getXXBH() {
		return XXBH;
	}
	public void setXXBH(Integer xXBH) {
		XXBH = xXBH;
	}
	public String getXXKZ1() {
		return XXKZ1;
	}
	public void setXXKZ1(String xXKZ1) {
		XXKZ1 = xXKZ1;
	}
	public String getXXKZ2() {
		return XXKZ2;
	}
	public void setXXKZ2(String xXKZ2) {
		XXKZ2 = xXKZ2;
	}
	public String getXXKZ3() {
		return XXKZ3;
	}
	public void setXXKZ3(String xXKZ3) {
		XXKZ3 = xXKZ3;
	}
	public String getXXKZ4() {
		return XXKZ4;
	}
	public void setXXKZ4(String xXKZ4) {
		XXKZ4 = xXKZ4;
	}
	public String getXXKZ5() {
		return XXKZ5;
	}
	public void setXXKZ5(String xXKZ5) {
		XXKZ5 = xXKZ5;
	}
	public YongHu getYonghu() {
		return yonghu;
	}
	public void setYonghu(YongHu yonghu) {
		this.yonghu = yonghu;
	}
	public Set<XiaoQu> getSetXiaoQu() {
		return setXiaoQu;
	}
	public void setSetXiaoQu(Set<XiaoQu> setXiaoQu) {
		this.setXiaoQu = setXiaoQu;
	}


}
