package com.team.spick.controller;

import com.team.spick.domain.Reply;
import com.team.spick.dto.ReplytRequestDto;
import com.team.spick.security.UserDetailsImpl;
import com.team.spick.service.ReplyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {

        this.replyService = replyService;
    }

    // 댓글 등록
    @PostMapping("/api/detail/{board_id}/reply")
    public Map<String, Object> createComment(@PathVariable Long board_id, @RequestBody ReplytRequestDto replytRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Reply reply = replyService.createReply(board_id, replytRequestDto, userDetails);

        Map<String, Object> result = new HashMap<>();
        result.put("nickname", reply.getReply_nickname());
        result.put("picURL", reply.getReply_picURL());
        result.put("comment", reply.getReply_text());
        result.put("CreatedAt", reply.getCreatedAt());
        result.put("id", reply.getReplyId());

        return result;
    }

    // 댓글 수정
    @PostMapping("/api/detail/{board_id}/{replyId}")
    public Map<String, Object> updateReply(@PathVariable Long replyId, @RequestBody ReplytRequestDto replytRequestDto) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");

        Reply reply = replyService.updateReply(replyId, replytRequestDto);
        result.put("reply", reply);
        return result;
    }

    // 댓글 삭제
    @DeleteMapping("/api/detail/{board_id}/{replyId}")
    public Map<String, String> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");

        return result;
    }
}
