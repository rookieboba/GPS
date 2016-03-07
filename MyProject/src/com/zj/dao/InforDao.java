package com.zj.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zj.domain.MyData;
import com.zj.util.DaoUtils;

public class InforDao {
	
	public void Update(String Content)
	{
		String sql = "update mytest set content=? where id=1";
		try{
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,Content);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public MyData QueryData()
	{
		String sql = "select * from mytest  where id=1";
		try{
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<MyData>(MyData.class));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}

}
