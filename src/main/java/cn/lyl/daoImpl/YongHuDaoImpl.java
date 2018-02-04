package cn.lyl.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.lyl.dao.IYongHuDao;
import cn.lyl.entity.YongHu;

@Repository(value="YongHuDaoImpl")
public class YongHuDaoImpl extends CommonDaoImpl<YongHu> implements IYongHuDao {
	
	@Autowired
	public YongHu yh;

	@Override
	public void save(YongHu entity) {
		ht.saveOrUpdate(entity);
	}

	@Override
	public void update(YongHu entity) {
		ht.saveOrUpdate(entity);
	}

	@Override
	public void delete(YongHu entity) {
		
	}

	@Override
	public YongHu find(String arg1) {
		
		return null;
	}
	
	public YongHu login(String arg1,String arg2){
		List<YongHu> yhlist = new ArrayList<YongHu>();
		yhlist = (List<YongHu>) ht.find("from YongHu where YHKZ2 = ? and YHMM = ?", arg1,arg2);
		if(yhlist.size()>0){
			return yhlist.get(0);
		}else{
			return null;
		}

	}
	
}
