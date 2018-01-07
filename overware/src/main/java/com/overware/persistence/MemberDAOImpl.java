package com.overware.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.overware.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	private static final String namespace="com.overware.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace+".insertMember",vo);
	}
	@Override
	public MemberVO readMember(String userid) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectMember",userid);
	}
	//파라미터가 2개 이상 전달되기때문에 간략하게 하기 위해서 Map타입의 객체를 구성해서 파라미ㅓ로 사용
	@Override
	public MemberVO readWithPW(String userid, String userpw) throws Exception {
		//Map타입의 객체를 생성해서 파라미터로 map을 줌
		Map<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw",userpw);
		return sqlSession.selectOne(namespace+".readWithPW",paramMap);
	}
}
