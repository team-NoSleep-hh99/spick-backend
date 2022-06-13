package com.team.spick.service;

import com.team.spick.domain.Likes;
import com.team.spick.dto.LikeDto;
import com.team.spick.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardService boardService;

    @Autowired
    public LikeService(LikeRepository likeRepository, BoardService boardService){

        this.likeRepository=likeRepository;
        this.boardService=boardService;
    }


    // 관심 객체 없을 때 (처음 눌렀을 때)
    public Long createlike(Long boardtId, LikeDto likeDto){
        Likes like = new Likes(boardtId, likeDto);
        likeRepository.save(like); // repository에는 Like 객체 자체가 들어가야만 된다.
        System.out.println(boardtId +"관심 눌러서 객체 생성");

        //공감 카운트 수 업데이트 기능
        Long userId = likeDto.getUserId();
        Long likeCount = boardService.likeCount(boardtId,userId);

        return likeCount;
    }

    // 관심 객체 생성 후 클릭 시
    public Long updatelike(Long boardId, LikeDto likeDto) {
        Likes like = likeRepository.findByUserIdAndBoardId(likeDto.getUserId(), boardId).orElseThrow(
                ()-> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        like.update(likeDto);
        likeRepository.save(like);

        //공감 카운트 수 업데이트 기능
        Long userId = likeDto.getUserId();
        Long likeCount = boardService.likeCount(boardId,userId);

        return likeCount;
    }
}
