package cn.lyl.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lyl.dao.IBaoBiao;
import cn.lyl.entity.BaoBiao;
import cn.lyl.entity.YongHu;

@Repository(value="baoBiaoDaoImpl")
public class BaoBiaoDaoImpl extends UnusualDaoImpl<BaoBiao, YongHu> implements IBaoBiao {

	@Override
	public void save(BaoBiao arg1, YongHu arg2) {
		arg1.setYongh(arg2);
		ht.save(arg1);
	}

	@Override
	public List<BaoBiao> findAll(String arg) {
		return (List<BaoBiao>) ht.find("from BaoBiao where yongh = "+arg+"");
	}

	@Override
	public void update(BaoBiao entity1, YongHu entity2) {
		// TODO Auto-generated method stub
		
	}
	
	public BaoBiao find(String arg){
		return ht.get(BaoBiao.class, Integer.parseInt(arg));
	}
	
	public void delete(BaoBiao arg){
		ht.delete(arg);
	}
}
