package com.overware.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Inject
	SqlSession sqlSession;
	
	private static String namespace="com.overware.mapper.PointMapper";
	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("uid", uid);
		map.put("point", point);
		
		sqlSession.update(namespace+".updatePoint", map);
	}

}
