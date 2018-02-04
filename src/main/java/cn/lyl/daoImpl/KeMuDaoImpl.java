package cn.lyl.daoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IKeMuDao;
import cn.lyl.entity.KeMu;
import cn.lyl.entity.YongHu;

@Repository(value="keMuDaoImpl")
public class KeMuDaoImpl extends UnusualDaoImpl<YongHu, KeMu> implements IKeMuDao {

	@Override
	public void save(YongHu arg1, KeMu arg2) {
		arg2.setYhu(arg1);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String time=format.format(date);
		arg2.setKmkz2(time);
		ht.save(arg2);
	}

	@Override
	public List<KeMu> findAll(String arg) {
		return (List<KeMu>) ht.find("from KeMu where yhu = "+arg+"");
	}

	@Override
	public void update(YongHu entity1, KeMu entity2) {
		// TODO Auto-generated method stub

	}
	public KeMu find(String arg){
		return ht.get(KeMu.class, Integer.parseInt(arg));
	}

	public void delete(KeMu arg){
		ht.delete(arg);
	}
}
