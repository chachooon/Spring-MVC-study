package org.zerock.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO messageDAO;

	@Inject
	private PointDAO pointDAO;

	/* @ Transactional 애노테이션의 적용 우선순위
	 * : 1. 메소드  > 2. 클래스 > 3. 인터페이스
	 */
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {

		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {

		messageDAO.updateState(mno);

		pointDAO.updatePoint(uid, 5);

		return messageDAO.readMessage(mno);
	}
}