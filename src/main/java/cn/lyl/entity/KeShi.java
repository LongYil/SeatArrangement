package cn.lyl.entity;

import java.util.HashSet;
import java.util.Set;

public class KeShi {
	private Integer KSBH;//课室编号
	private String KSMC;//课室名称
	private Integer KSHS;//课室行数
	private Integer KSLS;//课室列数
	private Integer KSGDS;//课室过道数
	private Integer KSZS;//课室最适人数
	private String KSGDL;//课室过道所在列，中间用##分开
	private String KSKZ1;//扩展字段1
	private Integer KSKZ2;//扩展字段2
	private Integer KSKZ3;//扩展字段3
	private Integer KSKZ4;//扩展字段4
	private String KSKZ5;//扩展字段5

	//在课室里面表示所属楼栋，一个课室只能属于一个楼栋
	private LouDong loudong;//
	//在课室里面表示所拥有的课室，一个课室可以拥有多个座位
	private Set<ZuoWei> setzuowei = new HashSet<ZuoWei>();

	public Integer getKSBH() {
		return KSBH;
	}
	public void setKSBH(Integer kSBH) {
		KSBH = kSBH;
	}
	public String getKSMC() {
		return KSMC;
	}
	public void setKSMC(String kSMC) {
		KSMC = kSMC;
	}
	public Integer getKSHS() {
		return KSHS;
	}
	public void setKSHS(Integer kSHS) {
		KSHS = kSHS;
	}
	public Integer getKSLS() {
		return KSLS;
	}
	public void setKSLS(Integer kSLS) {
		KSLS = kSLS;
	}
	public Integer getKSGDS() {
		return KSGDS;
	}
	public void setKSGDS(Integer kSGDS) {
		KSGDS = kSGDS;
	}
	public Integer getKSZS() {
		return KSZS;
	}
	public void setKSZS(Integer kSZS) {
		KSZS = kSZS;
	}
	public String getKSGDL() {
		return KSGDL;
	}
	public void setKSGDL(String kSGDL) {
		KSGDL = kSGDL;
	}
	public String getKSKZ1() {
		return KSKZ1;
	}
	public void setKSKZ1(String kSKZ1) {
		KSKZ1 = kSKZ1;
	}
	public Integer getKSKZ2() {
		return KSKZ2;
	}
	public void setKSKZ2(Integer kSKZ2) {
		KSKZ2 = kSKZ2;
	}
	public Integer getKSKZ3() {
		return KSKZ3;
	}
	public void setKSKZ3(Integer kSKZ3) {
		KSKZ3 = kSKZ3;
	}
	public Integer getKSKZ4() {
		return KSKZ4;
	}
	public void setKSKZ4(Integer kSKZ4) {
		KSKZ4 = kSKZ4;
	}
	public String getKSKZ5() {
		return KSKZ5;
	}
	public void setKSKZ5(String kSKZ5) {
		KSKZ5 = kSKZ5;
	}
	public LouDong getLoudong() {
		return loudong;
	}
	public void setLoudong(LouDong loudong) {
		this.loudong = loudong;
	}
	public Set<ZuoWei> getSetzuowei() {
		return setzuowei;
	}
	public void setSetzuowei(Set<ZuoWei> setzuowei) {
		this.setzuowei = setzuowei;
	}


}
