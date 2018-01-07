package com.overware.spring;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.overware.domain.MemberVO;
import com.overware.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception{
		
		System.out.println(dao.getTime());
	}
	@Test
	public void testInserMember() throws Exception{
		// vo객체에 set 해주고 insert매소드 실행 
		MemberVO vo= new MemberVO();
		vo.setUserid("jaehuniya");
		vo.setUserpw("wognsl83");
		vo.setUsername("심재훈");
		vo.setEmail("tlawogns3456@naver.com");
		dao.insertMember(vo);
	}

}
