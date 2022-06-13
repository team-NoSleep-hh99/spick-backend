package com.example.demo.controller;

import com.example.demo.dto.LikeDto;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeRepository likeRepository, LikeService likeService) {
        this.likeRepository = likeRepository;
        this.likeService = likeService;
    }

    //클라이언트가 관심 눌럿을 때
    @PutMapping("/api/boards/{boardId}")
    public Long updatelike(@PathVariable Long boardId, @RequestBody LikeDto likeDto) {
        //객체가 있으면 수정
        if (likeRepository.findByUserIdAndBoardId((likeDto.getUserId()), boardId).isPresent()) {
            Long likeCount = likeService.updatelike(boardId, likeDto);
            return likeCount;
        }
        //객체가 없으면 생성
        else {
            Long likeCount = likeService.createlike(boardId, likeDto);
            return likeCount;
        }
    }


}
