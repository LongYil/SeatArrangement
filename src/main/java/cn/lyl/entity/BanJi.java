package cn.lyl.entity;

public class BanJi {
	private Integer bjbh;//班级编号
	private String sflb;//是否两个班    1:是   2：否
	private String bjmc1;//班级名称1
	private String bjmc2;//班级名称2
	private Integer bjrs1;//班级人数1
	private Integer bjrs2;//班级人数2
	private String bjks;//班级考试课室编号
	private String bjkz1;
	private String bjkz2;
	private String bjkz3;
	private String bjkz4;
	private String bjkz5;

	//在班级里面表示所属科目，一个班级只能属于一个科目
	private KeMu keMu;

	public Integer getBjbh() {
		return bjbh;
	}

	public void setBjbh(Integer bjbh) {
		this.bjbh = bjbh;
	}

	public String getSflb() {
		return sflb;
	}

	public void setSflb(String sflb) {
		this.sflb = sflb;
	}

	public String getBjmc1() {
		return bjmc1;
	}

	public void setBjmc1(String bjmc1) {
		this.bjmc1 = bjmc1;
	}

	public String getBjmc2() {
		return bjmc2;
	}

	public void setBjmc2(String bjmc2) {
		this.bjmc2 = bjmc2;
	}

	public Integer getBjrs1() {
		return bjrs1;
	}

	public void setBjrs1(Integer bjrs1) {
		this.bjrs1 = bjrs1;
	}

	public Integer getBjrs2() {
		return bjrs2;
	}

	public void setBjrs2(Integer bjrs2) {
		this.bjrs2 = bjrs2;
	}

	public String getBjks() {
		return bjks;
	}

	public void setBjks(String bjks) {
		this.bjks = bjks;
	}

	public String getBjkz1() {
		return bjkz1;
	}

	public void setBjkz1(String bjkz1) {
		this.bjkz1 = bjkz1;
	}

	public String getBjkz2() {
		return bjkz2;
	}

	public void setBjkz2(String bjkz2) {
		this.bjkz2 = bjkz2;
	}

	public String getBjkz3() {
		return bjkz3;
	}

	public void setBjkz3(String bjkz3) {
		this.bjkz3 = bjkz3;
	}

	public String getBjkz4() {
		return bjkz4;
	}

	public void setBjkz4(String bjkz4) {
		this.bjkz4 = bjkz4;
	}

	public String getBjkz5() {
		return bjkz5;
	}

	public void setBjkz5(String bjkz5) {
		this.bjkz5 = bjkz5;
	}

	public KeMu getKeMu() {
		return keMu;
	}

	public void setKeMu(KeMu keMu) {
		this.keMu = keMu;
	}




}
