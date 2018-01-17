package com.overware.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overware.domain.MessageVO;
import com.overware.persistence.MessageDAO;
import com.overware.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		//새로운 매세지를 추가하면서 메세지를 보낸사람에게 10포인트를 추가함 
		messageDAO.create(vo); //
		pointDAO.updatePoint(vo.getSender(), 10);

	}
	
	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		//메세지를 조회하고
		messageDAO.updateState(mno);
		//메세지를 조회한 사람은 포인트가 5포인트 올라가고
		pointDAO.updatePoint(uid, 5);
		//다작업한후에는 메세지를 조회해서 반환 
		return messageDAO.readMessage(mno);
	}

}
