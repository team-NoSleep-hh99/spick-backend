package com.team.spick.controller;

import com.team.spick.dto.ReplyDto;
import com.team.spick.model.Reply;
import com.team.spick.repository.ReplyRepository;
import com.team.spick.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/api/detail/:board_id")
    public Reply createReply(@RequestBody ReplyDto replyDto){
        Long username = user
        return reply;
    }

}
