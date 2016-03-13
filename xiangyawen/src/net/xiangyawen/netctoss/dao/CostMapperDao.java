package net.xiangyawen.netctoss.dao;

import java.util.List;

import net.xiangyawen.netctoss.entity.Cost;
import net.xiangyawen.netctoss.util.MyBatisDao;
import net.xiangyawen.netctoss.vo.Page;

@MyBatisDao
public interface CostMapperDao {

	public List<Cost> findAll();
	public void deleteCost(int id);
	public void saveCost(Cost cost);
	public Cost findById(int id);
	public void updateCost(Cost cost);
	public List<Cost> findPage(Page page);
	public int findRows();
	public Cost findByName(String name);
	
}
