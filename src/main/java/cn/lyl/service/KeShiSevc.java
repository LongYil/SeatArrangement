package cn.lyl.service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lyl.action.YongHuAction;
import cn.lyl.daoImpl.KeShiDaoImpl;
import cn.lyl.entity.BanJi;
import cn.lyl.entity.BaoBiao;
import cn.lyl.entity.KeMu;
import cn.lyl.entity.KeShi;
import cn.lyl.entity.LouDong;
import cn.lyl.entity.ZuoWei;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import myutils.MyUtil;

@Transactional
@Service(value="keShiSevc")
public class KeShiSevc extends UnusualSevc<KeShi, LouDong> {

	@Autowired
	private KeShiDaoImpl ksdi;
	@Autowired
	private ZuoWeiSevc zws;
	@Autowired
	private BanJiSevc bjs;
	@Autowired
	private KeShi keShi;
	@Autowired
	private BanJi banJi;
	@Autowired
	private KeMuSevc kms;
	@Autowired
	private BaoBiaoSevc bbs;
	@Autowired
	private BaoBiao baoBiao;


	private ZuoWei zuoWei;

	private List<Integer> listkaohao1 = new ArrayList<Integer>();
	private List<Integer> listkaohao2 = new ArrayList<Integer>();
	Random random = new Random();

	@Override
	public void save(KeShi arg1, LouDong arg2) {
		ksdi.save(arg2, arg1);
	}

	@Override
	public KeShi find(String arg) {
		return ksdi.find(arg);
	}

	public List<KeShi> findAll(String arg){
		return ksdi.findAll(arg);
	}

	public String sortMethod1(KeShi keShi,String[] zuowei){//座位编排方式1：前后排不间隔，左右间隔一个（或两个）座位

		int hang = keShi.getKSHS();
		int lie = keShi.getKSLS();

		String[] guodaolie1 = keShi.getKSGDL().split("##");
		int[] guodaolie2 = new int[guodaolie1.length];
		for(int i=0;i<guodaolie2.length;i++){
			guodaolie2[i] = Integer.parseInt(guodaolie1[i]);
		}

		List<Integer> temphead = new ArrayList<Integer>();

		//左右间隔一个座位，前后不间隔座位编排规则
		if(guodaolie2.length==1){//如果只有一个过道
			//制定列区域1的列头编排规则
			System.out.println("只有一个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域2的列头编排规则
			if(((lie-guodaolie2[0])%2)==0&&(lie-guodaolie2[0])!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<lie;j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((lie-guodaolie2[0])==4){
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}else if(guodaolie2.length==2){//如果有两个过道
			//制定列区域1的列头编排规则
			System.out.println("有两个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((lie-guodaolie2[1])%2)==0&&lie-guodaolie2[1]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[1]==4){
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}
		}else if(guodaolie2.length==3){//如果有三个过道
			System.out.println("有三个过道");
			//制定列区域1的列头编排规则
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((guodaolie2[2]-guodaolie2[1])%2)==0&&(guodaolie2[2]-guodaolie2[1]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[2]-guodaolie2[1]-1)==4){
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域4的列头编排规则
			if(((lie-guodaolie2[2])%2)==0&&lie-guodaolie2[2]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[2]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[2]==4){
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}


		for (Integer integer : temphead) {
			System.out.println(integer+"**************");
		}


		int a = 0;
		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++){
				for(int k=0;k<temphead.size();k++){
					if((j+1)==temphead.get(k)&&zuowei[(i*lie)+j].equals("1")){
						a++;
					}
				}
			}
		}

		String classinfo;

		int num1 = a;//课室最适人数
		int num2 = 0;//如果课室总座位数大于90，可容纳班级人数1（较大）
		int num3 = 0;//如果课室总座位数大于90，可容纳班级人数2（较小）
		int num4 = 0;
		System.out.println("课室最适人数-------------------->"+a);
		if(a>80){
			List<Integer> headlist1 = new ArrayList<Integer>();
			List<Integer> headlist2 = new ArrayList<Integer>();
			int tempnum = temphead.size();
			num4 = temphead.get(temphead.size()/2)-1;
			for(int i=0;i<tempnum;i++){
				if(i<tempnum/2){
					headlist1.add(temphead.get(i));
				}else{
					headlist2.add(temphead.get(i));
				}
			}

			for(int i=0;i<hang;i++){
				for(int j=0;j<lie;j++){
					for(int k=0;k<headlist1.size();k++){
						if((j+1)==headlist1.get(k)&&zuowei[(i*lie)+j].equals("1")){
							num2++;
						}
					}
				}
			}
			for(int i=0;i<hang;i++){
				for(int j=0;j<lie;j++){
					for(int k=0;k<headlist2.size();k++){
						if((j+1)==headlist2.get(k)&&zuowei[(i*lie)+j].equals("1")){
							num3++;
						}
					}
				}
			}

			System.out.println("课室最适人数:"+a+"班级人数1:"+num2+"班级人数2:"+num3+"班级分隔列"+num4);
			return a+"##"+"1"+"##"+num2+"##"+num3+"##"+num4;
		}else{
			return a+"##"+"0"+"##"+num2+"##"+num3+"##"+num4;
		}
	}



	public void saveAllZuoWei(KeShi keShi,String[] zuowei){
		int lie = keShi.getKSLS();
		int hang = keShi.getKSHS();
		for(int i=0;i<keShi.getKSHS();i++){
			for(int j=0;j<keShi.getKSLS();j++){
//				System.out.println("第"+(i+1)+"行"+"第"+(j+1)+"列:"+zuowei[(i*lie)+j]);
				zuoWei = new ZuoWei();
				zuoWei.setZWXH((i*lie)+j);
				zuoWei.setZWZT(zuowei[(i*lie)+j]);
				zws.save(keShi, zuoWei);
			}
		}
	}


	public void executeAllArrange(String arg) throws Exception{//参数为科目编号，根据科目编号查找参与本科目考试的所有班级
		KeMu keMu = kms.find(arg);

		List<BanJi> listbanji = bjs.findAll(arg);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyddHHmmss");
		String time=format.format(date);

		HttpServletRequest request =  ServletActionContext.getRequest();
		String path = request.getServletContext().getRealPath("/myfile");

		String pathname = path+"/"+keMu.getKmmc()+"_科目考试座位表_"+time+".xls";

		baoBiao.setBbmc(keMu.getKmmc()+"_科目考试座位表_"+time+".xls");
		baoBiao.setBblj(MyUtil.ADDRESSHEADER+"myfile/"+keMu.getKmmc()+"_科目考试座位表_"+time+".xls");
		bbs.save(baoBiao, YongHuAction.yonghu);

		File f = new File(pathname);
		if(f.exists()){
			f.delete();
		}else{
			;
		}
		WritableWorkbook wwb = Workbook.createWorkbook(f);

		for(int i=0;i<listbanji.size();i++){
			keShi = this.find(listbanji.get(i).getBjks().trim());
			banJi = listbanji.get(i);
			String tempflag = listbanji.get(i).getSflb().trim();
			if(tempflag.equals("1")){//两个班级，执行两个班编排方法
				executeArrange2(keShi,banJi,wwb,i);
			}else{//一个班级考试，执行一个班编排方法
				executeArrange1(keShi,banJi,wwb,i);
			}
		}
		wwb.write();
		wwb.close();
	}



	public void executeArrange1(KeShi keShi,BanJi banji,WritableWorkbook wwb,int num) throws Exception{//1个班级考试所用到的编排方法，此方法还需传递一个考试班级信息参数，该参数包含以下数据：考试班级名称，考试班级人数

		List<ZuoWei> listzuowei = new ArrayList<ZuoWei>();
		listzuowei = zws.findAll(keShi.getKSBH().toString());
		String[] zuowei = new String[listzuowei.size()];
		int renshu = banji.getBjrs1();
		listkaohao1 = new ArrayList<Integer>();
		int tempkaohao = 0;
		for(int i=0;i<renshu;i++){
			if(listkaohao1.size()<1){
				listkaohao1.add(random.nextInt(renshu)+1);
			}else{
				tempkaohao = random.nextInt(renshu)+1;
				for(int j=0;j<listkaohao1.size();j++){
					if(tempkaohao==listkaohao1.get(j)){
						tempkaohao = random.nextInt(renshu)+1;
						j=-1;
					}else{
						;
					}
				}
				listkaohao1.add(tempkaohao);
			}
		}


		for(int i=0;i<zuowei.length;i++){
			zuowei[i]=listzuowei.get(i).getZWZT();
		}

		int hang = keShi.getKSHS();
		int lie = keShi.getKSLS();
		String[] zuoweilie = keShi.getKSGDL().split("##");

		WritableSheet ws = wwb.createSheet(banji.getBjmc1(),num);
		SheetSettings sheetset = ws.getSettings();

		sheetset.setPaperSize(PaperSize.A4_SMALL);
		sheetset.setOrientation(PageOrientation.LANDSCAPE);
		sheetset.setDefaultColumnWidth(112/lie);
		sheetset.setVerticalCentre(true);
		sheetset.setHorizontalCentre(true);

		WritableFont wf0 = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,false);//加粗样式,字号  18
		WritableFont wf1 = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,false);//加粗样式
		WritableFont wf2 = new WritableFont(WritableFont.TIMES,18,WritableFont.NO_BOLD,false);//不加粗样式
		WritableFont wf3 = new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD,false);//不加粗样式
		WritableFont wf4 = new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD,false);//不加粗样式

		WritableCellFormat wcf0 = new WritableCellFormat(wf0);
		WritableCellFormat wcf1 = new WritableCellFormat(wf1);
		WritableCellFormat wcf2 = new WritableCellFormat(wf2);
		WritableCellFormat wcf3 = new WritableCellFormat(wf3);
		WritableCellFormat wcf4 = new WritableCellFormat(wf4);

		wcf1.setBorder(jxl.format.Border.NONE,jxl.format.BorderLineStyle.NONE);
		wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THICK);
		wcf3.setBorder(jxl.format.Border.NONE,jxl.format.BorderLineStyle.NONE);
		wcf4.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

		wcf0.setAlignment(Alignment.CENTRE);
		wcf1.setAlignment(Alignment.CENTRE);
		wcf2.setAlignment(Alignment.CENTRE);
		wcf3.setAlignment(Alignment.CENTRE);
		wcf4.setAlignment(Alignment.LEFT);

		wcf2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf2.setWrap(true);

		wcf4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf4.setWrap(true);

		Label zw = null;
		//0:添加考试班级信息以及头信息
		zw = new Label(0,0,keShi.getLoudong().getLDMC()+("("+keShi.getKSMC()+"课室)")+"    "+"考场座位安排表",wcf0);
		ws.addCell(zw);
		zw = new Label(0,1,"考试座位总数:"+keShi.getKSZS()+";  本次考试人数:"+banJi.getBjrs1(),wcf3);
		ws.addCell(zw);
		zw = new Label(0,2,"本次考试班级:"+banJi.getBjmc1(),wcf3);
		ws.addCell(zw);
		zw = new Label(0,3,"讲台",wcf0);
		ws.addCell(zw);
		//合并首行单元格作为标题
		ws.mergeCells(0, 0, lie-1, 0);
		ws.mergeCells(0, 1, lie-1, 1);
		ws.mergeCells(0, 2, lie-1, 2);
		ws.mergeCells(0, 3, lie-1, 3);
		//1:添加座位

		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++){
				if(zuowei[(i*lie)+j].equals("1")){
					zw = new Label(j,i+5,"",wcf1);
					ws.addCell(zw);
				}else{
					;
				}
			}
		}


		//2:添加过道
		Label gd = null;
		for(int i=0;i<zuoweilie.length;i++){
			gd = new Label(Integer.parseInt(zuoweilie[i])-1,5,"过\r\n道",wcf2);
			ws.addCell(gd);
			ws.mergeCells(Integer.parseInt(zuoweilie[i])-1,5,Integer.parseInt(zuoweilie[i])-1,hang+4);
		}


		String[] guodaolie1 = keShi.getKSGDL().split("##");
		int[] guodaolie2 = new int[guodaolie1.length];
		for(int i=0;i<guodaolie2.length;i++){
			guodaolie2[i] = Integer.parseInt(guodaolie1[i]);
			System.out.println("过道--->"+guodaolie2[i]);
		}

		List<Integer> temphead = new ArrayList<Integer>();

		//左右间隔一个座位，前后不间隔座位编排规则
		if(guodaolie2.length==1){//如果只有一个过道
			//制定列区域1的列头编排规则
			System.out.println("只有一个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域2的列头编排规则
			if(((lie-guodaolie2[0])%2)==0&&(lie-guodaolie2[0])!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<lie;j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((lie-guodaolie2[0])==4){
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}else if(guodaolie2.length==2){//如果有两个过道
			//制定列区域1的列头编排规则
			System.out.println("有两个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((lie-guodaolie2[1])%2)==0&&lie-guodaolie2[1]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[1]==4){
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}
		}else if(guodaolie2.length==3){//如果有三个过道
			System.out.println("有三个过道");
			//制定列区域1的列头编排规则
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((guodaolie2[2]-guodaolie2[1])%2)==0&&(guodaolie2[2]-guodaolie2[1]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[2]-guodaolie2[1]-1)==4){
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域4的列头编排规则
			if(((lie-guodaolie2[2])%2)==0&&lie-guodaolie2[2]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[2]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[2]==4){
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}


		for (Integer integer : temphead) {
			System.out.println(integer+"**************");
		}
		List<Integer> listnum = new ArrayList<Integer>();

		int a = 0;
		int tempflag = 0;
		Label ks = null;
		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++){
				for(int k=0;k<temphead.size();k++){
					if((j+1)==temphead.get(k)&&zuowei[(i*lie)+j].equals("1")){
						if(a<listkaohao1.size()){
							ks = new Label(j,i+5,listkaohao1.get(a).toString(),wcf1);
						}else{
							ks = new Label(j,i+5,"",wcf1);
						}
						ws.addCell(ks);
						a++;
					}else{
					}
				}
			}
		}

//		1、监考员必须于开考前10分钟将此考场座位安排表抄在黑板上，以便考生对号就座。
//		2、此考场的1-63号由电脑随机编排座位，若考生人数少于63人时也必须严格按学号就座。
//		3、学号超过63号的考生由监考员随机编排座位并告知巡考员。
		zw = new Label(0,hang+6,"1、监考员必须于开考前10分钟将此考场座位安排表抄在黑板上，以便考生对号就座。\r\n"
				+ "2、此考场的1-"+banJi.getBjrs1()+"号由电脑随机编排座位，若考生人数必须严格按学号就座。\r\n"
				+ "3、学号超过"+banJi.getBjrs1()+"号的考生由监考员随机编排座位并告知巡考员。",wcf4);
		ws.addCell(zw);
		ws.mergeCells(0, hang+6, lie-1, hang+9);
	}





	public void executeArrange2(KeShi keShi,BanJi banji,WritableWorkbook wwb,int num) throws Exception{//两个班级考试所用的方法，还需传递一个考试班级信息参数，该参数包含以下数据：

		List<ZuoWei> listzuowei = new ArrayList<ZuoWei>();
		listzuowei = zws.findAll(keShi.getKSBH().toString());
		String[] zuowei = new String[listzuowei.size()];
		int renshu1 = banji.getBjrs1();
		int renshu2 = banji.getBjrs2();

		listkaohao1 = new ArrayList<Integer>();
		listkaohao2 = new ArrayList<Integer>();

		int tempkaohao1 = 0;
		for(int i=0;i<renshu1;i++){//人数较少的班级
			if(listkaohao1.size()<1){
				listkaohao1.add(random.nextInt(renshu1)+1);
			}else{
				tempkaohao1 = random.nextInt(renshu1)+1;
				for(int j=0;j<listkaohao1.size();j++){
					if(tempkaohao1==listkaohao1.get(j)){
						tempkaohao1 = random.nextInt(renshu1)+1;
						j=-1;
					}else{
						;
					}
				}
				listkaohao1.add(tempkaohao1);
			}
		}

		int tempkaohao2 = 0;
		for(int i=0;i<renshu2;i++){//人数较多的班级
			if(listkaohao1.size()<1){
				listkaohao1.add(random.nextInt(renshu2)+1);
			}else{
				tempkaohao2 = random.nextInt(renshu2)+1;
				for(int j=0;j<listkaohao2.size();j++){
					if(tempkaohao2==listkaohao2.get(j)){
						tempkaohao2 = random.nextInt(renshu2)+1;
						j=-1;
					}else{
						;
					}
				}
				listkaohao2.add(tempkaohao2);
			}
		}



		for(int i=0;i<zuowei.length;i++){
			zuowei[i]=listzuowei.get(i).getZWZT();
		}

		int hang = keShi.getKSHS();
		int lie = keShi.getKSLS();
		String[] zuoweilie = keShi.getKSGDL().split("##");

		WritableSheet ws = wwb.createSheet(banji.getBjmc1()+","+banji.getBjmc2(),num);
		SheetSettings sheetset = ws.getSettings();

		sheetset.setPaperSize(PaperSize.A4_SMALL);
		sheetset.setOrientation(PageOrientation.LANDSCAPE);
		sheetset.setDefaultColumnWidth(112/lie);
		sheetset.setVerticalCentre(true);
		sheetset.setHorizontalCentre(true);

		WritableFont wf0 = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,false);//加粗样式,字号  18
		WritableFont wf1 = new WritableFont(WritableFont.TIMES,18,WritableFont.BOLD,false);//加粗样式
		WritableFont wf2 = new WritableFont(WritableFont.TIMES,18,WritableFont.NO_BOLD,false);//不加粗样式
		WritableFont wf3 = new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD,false);//不加粗样式
		WritableFont wf4 = new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD,false);//不加粗样式

		WritableCellFormat wcf0 = new WritableCellFormat(wf0);
		WritableCellFormat wcf1 = new WritableCellFormat(wf1);
		WritableCellFormat wcf2 = new WritableCellFormat(wf2);
		WritableCellFormat wcf3 = new WritableCellFormat(wf3);
		WritableCellFormat wcf4 = new WritableCellFormat(wf4);

		wcf1.setBorder(jxl.format.Border.NONE,jxl.format.BorderLineStyle.NONE);
		wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THICK);
		wcf3.setBorder(jxl.format.Border.NONE,jxl.format.BorderLineStyle.NONE);
		wcf4.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

		wcf0.setAlignment(Alignment.CENTRE);
		wcf1.setAlignment(Alignment.CENTRE);
		wcf2.setAlignment(Alignment.CENTRE);
		wcf3.setAlignment(Alignment.CENTRE);
		wcf4.setAlignment(Alignment.LEFT);

		wcf2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf2.setWrap(true);

		wcf4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcf4.setWrap(true);

		Label zw = null;
		//0:添加考试班级信息以及头信息
		zw = new Label(0,0,keShi.getLoudong().getLDMC()+("("+keShi.getKSMC()+"课室)")+"    "+"考场座位安排表",wcf0);
		ws.addCell(zw);
		zw = new Label(0,1,"考试座位总数:"+keShi.getKSZS()+";  本次考试人数:"+(banJi.getBjrs1()+banJi.getBjrs2()),wcf3);
		ws.addCell(zw);
		zw = new Label(0,2,"本次考试班级:"+banJi.getBjmc1()+"左 ("+banJi.getBjrs1()+"人 ) "+banJi.getBjmc2()+"右 ("+banJi.getBjrs2()+"人 ) ",wcf3);
		ws.addCell(zw);
		zw = new Label(0,3,"讲台",wcf0);
		ws.addCell(zw);
		//合并首行单元格作为标题
		ws.mergeCells(0, 0, lie-1, 0);
		ws.mergeCells(0, 1, lie-1, 1);
		ws.mergeCells(0, 2, lie-1, 2);
		ws.mergeCells(0, 3, lie-1, 3);
		//1:添加座位

		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++){
				if(zuowei[(i*lie)+j].equals("1")){
					zw = new Label(j,i+5,"",wcf1);
					ws.addCell(zw);
				}else{
					;
				}
			}
		}


		//2:添加过道
		Label gd = null;
		for(int i=0;i<zuoweilie.length;i++){
			gd = new Label(Integer.parseInt(zuoweilie[i])-1,5,"过\r\n道",wcf2);
			ws.addCell(gd);
			ws.mergeCells(Integer.parseInt(zuoweilie[i])-1,5,Integer.parseInt(zuoweilie[i])-1,hang+4);
		}

		String[] guodaolie1 = keShi.getKSGDL().split("##");
		int[] guodaolie2 = new int[guodaolie1.length];
		for(int i=0;i<guodaolie2.length;i++){
			guodaolie2[i] = Integer.parseInt(guodaolie1[i]);
			System.out.println("过道--->"+guodaolie2[i]);
		}

		List<Integer> temphead = new ArrayList<Integer>();

		//左右间隔一个座位，前后不间隔座位编排规则
		if(guodaolie2.length==1){//如果只有一个过道
			//制定列区域1的列头编排规则
			System.out.println("只有一个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域2的列头编排规则
			if(((lie-guodaolie2[0])%2)==0&&(lie-guodaolie2[0])!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<lie;j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((lie-guodaolie2[0])==4){
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}else if(guodaolie2.length==2){//如果有两个过道
			//制定列区域1的列头编排规则
			System.out.println("有两个过道");
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((lie-guodaolie2[1])%2)==0&&lie-guodaolie2[1]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[1]==4){
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}
		}else if(guodaolie2.length==3){//如果有三个过道
			System.out.println("有三个过道");
			//制定列区域1的列头编排规则
			if(((guodaolie2[0]-1)%2)==0&&(guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=2;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[0]-1)==4){
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=1;j<guodaolie2[0];j++){
						temphead.add(j);
						j++;
					}
				}

			}
			//制定列区域2的列头编排规则
			if(((guodaolie2[1]-guodaolie2[0]-1)%2)==0&&(guodaolie2[1]-guodaolie2[0]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[0]+1);j<guodaolie2[1];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[0]+2);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[1]-guodaolie2[0]-1)==4){
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[0]+1);j<(guodaolie2[1]);j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域3的列头编排规则
			if(((guodaolie2[2]-guodaolie2[1])%2)==0&&(guodaolie2[2]-guodaolie2[1]-1)!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[1]+2);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if((guodaolie2[2]-guodaolie2[1]-1)==4){
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[1]+1);j<guodaolie2[2];j++){
						temphead.add(j);
						j++;
					}
				}
			}
			//制定列区域4的列头编排规则
			if(((lie-guodaolie2[2])%2)==0&&lie-guodaolie2[2]!=4){//如果区域内座位总列数为偶数，并且总列数不等于4，则制定偶数列头编排规则
				if(zuowei[(2)*lie].trim().equals("1")&&zuowei[(hang-3)*lie].trim().equals("1")){//如果第三排第一列并且第一列倒数第三排座位均可用，则以该列作为列头
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}else {//反之以第二列作为列头
					for(int j=(guodaolie2[2]+2);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}

			}else{//如果区域内座位总列数为奇数，或者总列数等于4，则制定区域内奇数列头编排规则，奇数列或列数为4的列区域必须从第一列开始编排
				if(lie-guodaolie2[2]==4){
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
						j++;
					}
				}else{
					for(int j=(guodaolie2[2]+1);j<(lie+1);j++){
						temphead.add(j);
						j++;
					}
				}
			}

		}

		List<Integer> listnum = new ArrayList<Integer>();

		int a = 0;
		int tempflag = 0;

		for(int i=0;i<hang;i++){
			for(int j=0;j<lie;j++){
				for(int k=0;k<temphead.size();k++){
					if((j+1)==temphead.get(k)&&zuowei[(i*lie)+j].equals("1")){
						a++;
					}else{
					}
				}
			}
		}

		String classinfo;

		int num1 = a;//课室最适人数
		int num2 = 0;//如果课室总座位数大于80，可容纳班级人数1（较小）
		int num3 = 0;//如果课室总座位数大于80，可容纳班级人数2（较大）
		int num4 = 0;

		List<Integer> headlist1 = new ArrayList<Integer>();
		List<Integer> headlist2 = new ArrayList<Integer>();
		int tempnum = temphead.size();
		num4 = temphead.get(temphead.size()/2)-1;
		for(int i=0;i<tempnum;i++){
			if(i<tempnum/2){
				headlist1.add(temphead.get(i));
			}else{
				headlist2.add(temphead.get(i));
			}
		}
		Label ks = null;
		for(int i=0;i<hang;i++){//headlist1较小，应编排人数较少的班级，左边
			for(int j=0;j<lie;j++){
				for(int k=0;k<headlist1.size();k++){
					if((j+1)==headlist1.get(k)&&zuowei[(i*lie)+j].equals("1")){
						if(num2<listkaohao1.size()){
							ks = new Label(j,i+5,listkaohao1.get(num2).toString(),wcf1);
						}else{
							ks = new Label(j,i+5,"",wcf1);
						}
						ws.addCell(ks);
						num2++;
					}
				}
			}
		}
		for(int i=0;i<hang;i++){//headlist2较大，应编排人数较多的班级，右边
			for(int j=0;j<lie;j++){
				for(int k=0;k<headlist2.size();k++){
					if((j+1)==headlist2.get(k)&&zuowei[(i*lie)+j].equals("1")){
						if(num3<listkaohao2.size()){
							ks = new Label(j,i+5,listkaohao2.get(num3).toString(),wcf1);
						}else{
							ks = new Label(j,i+5,"",wcf1);
						}
						ws.addCell(ks);
						num3++;
					}
				}
			}
		}
		Label fg = null;
		int banjifenge = keShi.getKSKZ4()-1;//班级分隔列
		fg = new Label(banjifenge,5,"班级分隔线",wcf2);
		ws.addCell(fg);
		ws.mergeCells(banjifenge,5,banjifenge,hang+4);

//		1、监考员必须于开考前10分钟将此考场座位安排表抄在黑板上，以便考生对号就座。
//		2、此考场的1-63号由电脑随机编排座位，若考生人数少于63人时也必须严格按学号就座。
//		3、学号超过63号的考生由监考员随机编排座位并告知巡考员。
		zw = new Label(0,hang+6,"1、监考员必须于开考前10分钟将此考场座位安排表抄在黑板上，以便考生对号就座。\r\n"
				+ "2、此考场的1-"+banJi.getBjrs1()+"号由电脑随机编排座位，若考生人数必须严格按学号就座。\r\n"
				+ "3、学号超过"+banJi.getBjrs1()+"号的考生由监考员随机编排座位并告知巡考员。",wcf4);
		ws.addCell(zw);
		ws.mergeCells(0, hang+6, lie-1, hang+9);
	}
}
