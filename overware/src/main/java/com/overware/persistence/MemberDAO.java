package com.overware.persistence;

import com.overware.domain.MemberVO;

public interface MemberDAO {
	
	public String getTime();
	
	public void insertMember(MemberVO vo);
	// 회원 정보 일거오기
	public MemberVO readMember(String userid)throws Exception;
	//회원 id와 패스워드 읽어 들여오기
	public MemberVO readWithPW(String userid,String userpw)throws Exception;
}
