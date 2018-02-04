package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class XiaoQu {
	private String XQMC;//校区名称
	private String XQJC;//校区简称
	private Integer XQBH;//校区编号
	private String XQKZ1;//扩展字段1
	private String XQKZ2;//扩展字段2
	private String XQKZ3;//扩展字段3
	private String XQKZ4;//扩展字段4
	private String XQKZ5;//扩展字段5

	//在校区里面表示所属学校，一个校区只能属于一个学校
	private XueXiao xuexiao;

	//在校区里面表示所拥有的楼栋，一个校区拥有多个楼栋
	private Set<LouDong> setloudong = new HashSet<LouDong>();

	public String getXQMC() {
		return XQMC;
	}

	public void setXQMC(String xQMC) {
		XQMC = xQMC;
	}

	public String getXQJC() {
		return XQJC;
	}

	public void setXQJC(String xQJC) {
		XQJC = xQJC;
	}

	public Integer getXQBH() {
		return XQBH;
	}

	public void setXQBH(Integer xQBH) {
		XQBH = xQBH;
	}

	public String getXQKZ1() {
		return XQKZ1;
	}

	public void setXQKZ1(String xQKZ1) {
		XQKZ1 = xQKZ1;
	}

	public String getXQKZ2() {
		return XQKZ2;
	}

	public void setXQKZ2(String xQKZ2) {
		XQKZ2 = xQKZ2;
	}

	public String getXQKZ3() {
		return XQKZ3;
	}

	public void setXQKZ3(String xQKZ3) {
		XQKZ3 = xQKZ3;
	}

	public String getXQKZ4() {
		return XQKZ4;
	}

	public void setXQKZ4(String xQKZ4) {
		XQKZ4 = xQKZ4;
	}

	public String getXQKZ5() {
		return XQKZ5;
	}

	public void setXQKZ5(String xQKZ5) {
		XQKZ5 = xQKZ5;
	}

	public XueXiao getXuexiao() {
		return xuexiao;
	}

	public void setXuexiao(XueXiao xuexiao) {
		this.xuexiao = xuexiao;
	}

	public Set<LouDong> getSetloudong() {
		return setloudong;
	}

	public void setSetloudong(Set<LouDong> setloudong) {
		this.setloudong = setloudong;
	}



}
