package com.team.spick.service;

import com.team.spick.domain.Board;
import com.team.spick.domain.Reply;
import com.team.spick.dto.ReplytRequestDto;
import com.team.spick.repository.BoardRepository;
import com.team.spick.repository.ReplyRepository;
import com.team.spick.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public List<Reply> getReplys(Board board) {

        return replyRepository.findAllByBoardOrderByModifiedAtDesc(board);
    }

    //수정
    @Transactional
    public Reply updateReply(Long replyId, ReplytRequestDto replytRequestDto) {
        Reply reply = replyRepository.findById(replyId).orElse(null);
        reply.update(replytRequestDto);

        return reply;
    }

    //삭제
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    //댓글 등록
    public Reply createReply(Long board_id, ReplytRequestDto replytRequestDto, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("에러")
        );
        Reply reply = new Reply(userDetails.getUser().getNickname(), replytRequestDto.getReply_text(), userDetails.getUser().getUser_picURL(), board);
        return replyRepository.save(reply);
    }

    //삭제
    @Transactional
    public void deletePage(Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(null);
        replyRepository.deleteByBoard(board);
    }
}

