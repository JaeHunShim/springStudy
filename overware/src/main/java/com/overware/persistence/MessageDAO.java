package com.overware.persistence;

import com.overware.domain.MessageVO;

public interface MessageDAO {
	//등록
	public void create(MessageVO vo) throws Exception;
	//읽어오기
	public MessageVO readMessage(Integer mid) throws Exception;
	//수정
	public void updateState(Integer mid) throws Exception;
}
