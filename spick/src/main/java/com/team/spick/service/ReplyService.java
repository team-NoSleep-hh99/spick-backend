package com.team.spick.service;

import com.team.spick.domain.Board;
import com.team.spick.domain.Reply;
import com.team.spick.dto.ReplytDto;
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
    public Reply updateReply(Long replyId, ReplytDto replytDto) {
        Reply reply = replyRepository.findById(replyId).orElse(null);
        reply.update(replytDto);

        return reply;
    }

    //삭제
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    //등록
    public Reply createReply(ReplytDto replytDto, UserDetailsImpl userDetails) {
        Board board = boardRepository.findById(replytDto.getBoard_id()).orElseThrow(
                () -> new IllegalArgumentException("에러")
        );

        String nickname = "test 확인";
        if (userDetails != null) {
            nickname = userDetails.getUser().getNickname();
        }
        Reply reply = new Reply(nickname, replytDto.getReply_text(), userDetails.getUser().getUser_picURL(), board);
        Reply saveReply = replyRepository.save(reply);

        return saveReply;
    }

    //삭제
    @Transactional
    public void deletePage(Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(null);
        replyRepository.deleteByBoard(board);
    }
}

