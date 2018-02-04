package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class YongHu {
	private Integer YHBH;//用户编号
	private String YHMC;//用户名称
	private String YHMM;//用户密码
	private String YHKZ1;//扩展字段1  用户性别
	private String YHKZ2;//扩展字段2
	private String YHKZ3;//扩展字段3
	private String YHKZ4;//扩展字段4
	private String YHKZ5;//扩展字段5

	//在用户里面表示所拥有的学校，一个用户有多个学校
	private Set<XueXiao> setXueXiao = new HashSet<XueXiao>();
	//在用户里面表示所拥有的报表，一个用户拥有多个报表
	private Set<BaoBiao> setBaoBiao = new HashSet<BaoBiao>();
	//在用户里面表示所拥有的科目，一个用户拥有多个科目
	private Set<KeMu> setKeMu = new HashSet<KeMu>();

	public String getYHMC() {
		return YHMC;
	}
	public void setYHMC(String yHMC) {
		YHMC = yHMC;
	}
	public Integer getYHBH() {
		return YHBH;
	}
	public void setYHBH(Integer yHBH) {
		YHBH = yHBH;
	}
	public String getYHMM() {
		return YHMM;
	}
	public void setYHMM(String yHMM) {
		YHMM = yHMM;
	}
	public String getYHKZ1() {
		return YHKZ1;
	}
	public void setYHKZ1(String yHKZ1) {
		YHKZ1 = yHKZ1;
	}
	public String getYHKZ2() {
		return YHKZ2;
	}
	public void setYHKZ2(String yHKZ2) {
		YHKZ2 = yHKZ2;
	}
	public String getYHKZ3() {
		return YHKZ3;
	}
	public void setYHKZ3(String yHKZ3) {
		YHKZ3 = yHKZ3;
	}
	public String getYHKZ4() {
		return YHKZ4;
	}
	public void setYHKZ4(String yHKZ4) {
		YHKZ4 = yHKZ4;
	}
	public String getYHKZ5() {
		return YHKZ5;
	}
	public void setYHKZ5(String yHKZ5) {
		YHKZ5 = yHKZ5;
	}
	public Set<XueXiao> getSetXueXiao() {
		return setXueXiao;
	}
	public void setSetXueXiao(Set<XueXiao> setXueXiao) {
		this.setXueXiao = setXueXiao;
	}
	public Set<BaoBiao> getSetBaoBiao() {
		return setBaoBiao;
	}
	public void setSetBaoBiao(Set<BaoBiao> setBaoBiao) {
		this.setBaoBiao = setBaoBiao;
	}
	public Set<KeMu> getSetKeMu() {
		return setKeMu;
	}
	public void setSetKeMu(Set<KeMu> setKeMu) {
		this.setKeMu = setKeMu;
	}

}
