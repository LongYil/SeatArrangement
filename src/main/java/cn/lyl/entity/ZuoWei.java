package cn.lyl.entity;

public class ZuoWei {
	private Integer ZWBH;//座位编号
	private Integer ZWXH;//座位序号（从1开始）
	private Integer ZWH;//座位所在行
	private Integer ZWL;//座位所在列
	private String ZWZT;//座位状态
	private String ZWKZ1;//座位扩展1
	private String ZWKZ2;//座位扩展2
	private String ZWKZ3;//座位扩展3
	private String ZWKZ4;//座位扩展4
	private String ZWKZ5;//座位扩展5

	//在座位里面表示所属课室，一个座位只能属于一个课室
	private KeShi keshi;

	public Integer getZWBH() {
		return ZWBH;
	}

	public void setZWBH(Integer zWBH) {
		ZWBH = zWBH;
	}

	public Integer getZWXH() {
		return ZWXH;
	}

	public void setZWXH(Integer zWXH) {
		ZWXH = zWXH;
	}

	public Integer getZWH() {
		return ZWH;
	}

	public void setZWH(Integer zWH) {
		ZWH = zWH;
	}

	public Integer getZWL() {
		return ZWL;
	}

	public void setZWL(Integer zWL) {
		ZWL = zWL;
	}

	public String getZWZT() {
		return ZWZT;
	}

	public void setZWZT(String zWZT) {
		ZWZT = zWZT;
	}

	public String getZWKZ1() {
		return ZWKZ1;
	}

	public void setZWKZ1(String zWKZ1) {
		ZWKZ1 = zWKZ1;
	}

	public String getZWKZ2() {
		return ZWKZ2;
	}

	public void setZWKZ2(String zWKZ2) {
		ZWKZ2 = zWKZ2;
	}

	public String getZWKZ3() {
		return ZWKZ3;
	}

	public void setZWKZ3(String zWKZ3) {
		ZWKZ3 = zWKZ3;
	}

	public String getZWKZ4() {
		return ZWKZ4;
	}

	public void setZWKZ4(String zWKZ4) {
		ZWKZ4 = zWKZ4;
	}

	public String getZWKZ5() {
		return ZWKZ5;
	}

	public void setZWKZ5(String zWKZ5) {
		ZWKZ5 = zWKZ5;
	}

	public KeShi getKeshi() {
		return keshi;
	}

	public void setKeshi(KeShi keshi) {
		this.keshi = keshi;
	}



}
