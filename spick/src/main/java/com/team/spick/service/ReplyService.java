package com.team.spick.service;

import com.team.spick.dto.ReplyDto;
import com.team.spick.model.Reply;
import com.team.spick.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public Reply createReply(ReplyDto requestDto, Long reply_id) {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Reply reply = new Reply(requestDto, reply_id);

        replyRepository.save(reply);

        return reply;
    }
}
