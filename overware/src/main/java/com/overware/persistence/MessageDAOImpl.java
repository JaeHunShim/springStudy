package com.overware.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.overware.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	@Inject
	SqlSession sqlSession;
	
	private static String namespace="com.overware.mapper.MemberMapper";

	@Override
	public void create(MessageVO vo) throws Exception {
		sqlSession.insert(namespace+".create", vo);

	}

	@Override
	public MessageVO readMessage(Integer mno) throws Exception {
		
		return sqlSession.selectOne(namespace+".read", mno);
	}

	@Override
	public void updateState(Integer mno) throws Exception {
		
		sqlSession.update(namespace+".updateState", mno);

	}

}
