package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class KeMu {
	private Integer kmbh;//科目编号
	private String kmmc;//科目名称
	private String kmkz1;//科目扩展字段1
	private String kmkz2;//科目扩展字段2
	private String kmkz3;//科目扩展字段3
	private String kmkz4;//科目扩展字段4
	private String kmkz5;//科目扩展字段5

	//在考试科目里面表示用户，一个考试科目只能给属于一个用户
	private YongHu yhu;
	//在考试科目里表示所拥有的班级，一个考试科目包含多个考试班级
	private Set<BanJi> setBanJi = new HashSet<BanJi>();

	public Integer getKmbh() {
		return kmbh;
	}
	public void setKmbh(Integer kmbh) {
		this.kmbh = kmbh;
	}
	public String getKmmc() {
		return kmmc;
	}
	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}
	public String getKmkz1() {
		return kmkz1;
	}
	public void setKmkz1(String kmkz1) {
		this.kmkz1 = kmkz1;
	}
	public String getKmkz2() {
		return kmkz2;
	}
	public void setKmkz2(String kmkz2) {
		this.kmkz2 = kmkz2;
	}
	public String getKmkz3() {
		return kmkz3;
	}
	public void setKmkz3(String kmkz3) {
		this.kmkz3 = kmkz3;
	}
	public String getKmkz4() {
		return kmkz4;
	}
	public void setKmkz4(String kmkz4) {
		this.kmkz4 = kmkz4;
	}
	public String getKmkz5() {
		return kmkz5;
	}
	public void setKmkz5(String kmkz5) {
		this.kmkz5 = kmkz5;
	}
	public YongHu getYhu() {
		return yhu;
	}
	public void setYhu(YongHu yhu) {
		this.yhu = yhu;
	}
	public Set<BanJi> getSetBanJi() {
		return setBanJi;
	}
	public void setSetBanJi(Set<BanJi> setBanJi) {
		this.setBanJi = setBanJi;
	}

}
