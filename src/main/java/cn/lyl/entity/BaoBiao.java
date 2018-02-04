package cn.lyl.entity;

public class BaoBiao {
	private Integer bbbh;//报表编号
	private String bbmc;//报表名称
	private String bblj;//报表路径
	private String bbkz1;//报表扩展1
	private String bbkz2;//报表扩展2
	private String bbkz3;//报表扩展3
	private String bbkz4;//报表扩展4
	private String bbkz5;//报表扩展5

	//在报表里面表示所属用户，一个报表只能属于一个用户；
	private YongHu yongh;//

	public Integer getBbbh() {
		return bbbh;
	}

	public void setBbbh(Integer bbbh) {
		this.bbbh = bbbh;
	}

	public String getBbmc() {
		return bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getBblj() {
		return bblj;
	}

	public void setBblj(String bblj) {
		this.bblj = bblj;
	}

	public String getBbkz1() {
		return bbkz1;
	}

	public void setBbkz1(String bbkz1) {
		this.bbkz1 = bbkz1;
	}

	public String getBbkz2() {
		return bbkz2;
	}

	public void setBbkz2(String bbkz2) {
		this.bbkz2 = bbkz2;
	}

	public String getBbkz3() {
		return bbkz3;
	}

	public void setBbkz3(String bbkz3) {
		this.bbkz3 = bbkz3;
	}

	public String getBbkz4() {
		return bbkz4;
	}

	public void setBbkz4(String bbkz4) {
		this.bbkz4 = bbkz4;
	}

	public String getBbkz5() {
		return bbkz5;
	}

	public void setBbkz5(String bbkz5) {
		this.bbkz5 = bbkz5;
	}

	public YongHu getYongh() {
		return yongh;
	}

	public void setYongh(YongHu yongh) {
		this.yongh = yongh;
	}

}
