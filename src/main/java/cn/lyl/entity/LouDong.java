package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class LouDong {
	private Integer LDBH;//楼栋编号
	private String LDMC;//楼栋名称
	private String LDJC;//楼栋简称
	private String LDKZ1;//扩展字段1
	private String LDKZ2;//扩展字段2
	private String LDKZ3;//扩展字段3
	private String LDKZ4;//扩展字段4
	private String LDKZ5;//扩展字段5

	//在楼栋里面表示所属校区，一个楼栋只能属于一个校区
	private XiaoQu xiaoqu;

	//在楼栋里面表示所拥有的课室，一个楼栋可以拥有多个课室
	private Set<KeShi> setkeshi = new HashSet<KeShi>();

	public Integer getLDBH() {
		return LDBH;
	}

	public void setLDBH(Integer lDBH) {
		LDBH = lDBH;
	}

	public String getLDMC() {
		return LDMC;
	}

	public void setLDMC(String lDMC) {
		LDMC = lDMC;
	}

	public String getLDJC() {
		return LDJC;
	}

	public void setLDJC(String lDJC) {
		LDJC = lDJC;
	}

	public String getLDKZ1() {
		return LDKZ1;
	}

	public void setLDKZ1(String lDKZ1) {
		LDKZ1 = lDKZ1;
	}

	public String getLDKZ2() {
		return LDKZ2;
	}

	public void setLDKZ2(String lDKZ2) {
		LDKZ2 = lDKZ2;
	}

	public String getLDKZ3() {
		return LDKZ3;
	}

	public void setLDKZ3(String lDKZ3) {
		LDKZ3 = lDKZ3;
	}

	public String getLDKZ4() {
		return LDKZ4;
	}

	public void setLDKZ4(String lDKZ4) {
		LDKZ4 = lDKZ4;
	}

	public String getLDKZ5() {
		return LDKZ5;
	}

	public void setLDKZ5(String lDKZ5) {
		LDKZ5 = lDKZ5;
	}

	public XiaoQu getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(XiaoQu xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public Set<KeShi> getSetkeshi() {
		return setkeshi;
	}

	public void setSetkeshi(Set<KeShi> setkeshi) {
		this.setkeshi = setkeshi;
	}



}
