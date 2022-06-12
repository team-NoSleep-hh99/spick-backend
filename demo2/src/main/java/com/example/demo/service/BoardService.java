package com.example.demo.service;


import com.example.demo.domain.Board;
import com.example.demo.domain.Likes;
import com.example.demo.dto.BoardRequestDto;
import com.example.demo.dto.BoardResponseDto;
import com.example.demo.dto.DetailBoardResponseDto;
import com.example.demo.dto.LikeDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
//    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
//    private final UserRepository userRepository;

    @Transactional
    public Long update(Long userId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        board.update(boardRequestDto);
        return board.getUserId();
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    //포스트 작성 시 이미지 업로드
    @Transactional
    public Long Imageupdate(BoardResponseDto boardRequestDto) {
        Long boardId = boardRequestDto.getBoardId();
        Board board = BoardRepository.findByBoardId(boardId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        board.imageUpdate(boardRequestDto);
        return board.getUserId();
    }


    //게시글 상세
    public DetailBoardResponseDto detailPost(BoardResponseDto boardResponseDto) {

        Long boardId = boardResponseDto.getBoardId();

        Board board = boardRepository.findByBoardId(boardId).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
        Long postUserId = board.getUserId();
//        String nickname = userRepository.findById(postUserId).get().getNickname();


        Long userId = boardResponseDto.getUserId();
        System.out.println("userId 0 체크 전");

        // is_Check
        if (userId==0) {
            System.out.println("게시글 상세 userId가 0일 때 : " +userId);
            boolean is_check = false;

            Long likeCount = Board.getLikeCount();

            LikeDto likes = new LikeDto(is_check,likeCount);

            DetailBoardResponseDto detailBoardResponseDto = new DetailBoardResponseDto(board,likes);
            return detailBoardResponseDto;

        } else {
            System.out.println("Optional<Likes> like 생성 전");

            if(likeRepository.findByUserIdAndBoardId(userId,boardId).isPresent()){
                System.out.println("Optional<Likes> like가 있음");
                Optional<Likes> like = likeRepository.findByUserIdAndBoardId(userId,boardId);

                boolean is_check = like.get().getIs_Check();

                Long likeCount = board.getLikeCount();

                System.out.println("게시글 상세조회 userId 0이 아닐 때 likeCount : " + likeCount );
                LikeDto likes = new LikeDto(is_check,likeCount);
                System.out.println("LikeDto 생성완료");
                DetailBoardResponseDto detailBoardResponseDto = new DetailBoardResponseDto(board,likes);
                System.out.println("DetailBoardResponseDto 생성완료");
                return detailBoardResponseDto;

            } else{

                boolean is_check = false;
                System.out.println("게시글 상세조회 userId 0이 아닐 때 is_check : " + is_check );

                Long likeCount = Board.getLikeCount();
                System.out.println("게시글 상세조회 userId 0이 아닐 때 likeCount : " + likeCount );
                LikeDto likes = new LikeDto(is_check,likeCount);
                System.out.println("LikeDto 생성완료");
                DetailBoardResponseDto detailBoardResponseDto = new DetailBoardResponseDto(board,likes, nickname);
                System.out.println("DetailBoardResponseDto 생성완료");
                return detailBoardResponseDto;
            }


        }

    }

    //내가 작성한 게시글 조회 서비스
    public List<DetailBoardResponseDto> mypageResponse(Long userId) {

        List<Board> boardList = boardRepository.findAllByUserId(userId);

        //빈 리스트 선언
        List<DetailBoardResponseDto> detailBoardResponseDtoList = new ArrayList<>();

        //반복문 돌아서 DetailPostResponseDto 생성후 위에 리스트에 추가
        for (Board board : boardList) {

            Long likeCount = board.getLikeCount();
            System.out.println("내가 작성한 게시글 조회 서비스에 like count : "+likeCount);

            DetailBoardResponseDto detailPostResponseDto = new DetailBoardResponseDto(Board,likeCount);

            detailBoardResponseDtoList.add(detailBoardResponseDtoList);

        }
        return detailBoardResponseDtoList;
    }

    //내가 관심을 누른 게시글 조회 서비스
    public List<DetailBoardResponseDto> mylikeResponse(Long userId) {

        List<Likes> likesList = likeRepository.findAllByUserId(userId);

        //빈 리스트 선언
        List<DetailBoardResponseDto> detailBoardResponseDtoList = new ArrayList<>();

        for (Likes like : likesList) {
            Long Board_id = like.getBoard_Id();

            Board board = boardRepository.findByBoardId(boardId).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            Long likeCount = Board.getLikeCount();
            System.out.println("내가 작성한 게시글 조회 서비스에 like count : " + likeCount);

            DetailBoardResponseDto detailBoardResponseDto = new DetailBoardResponseDto(board,likeCount);

            detailBoardResponseDtoList.add(detailBoardResponseDto);

        }

        return detailBoardResponseDtoList;
    }

    // 공감 수 카운트 업데이트
    public Long likeCount(Long BoardId, Long userId) {

        Board board = boardRepository.findByBoardId(boardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        //likeCount 세는 조건문
        List<Likes>likesList = likeRepository.findAllByBoardId(boardId);
        Long likeCount = 0L;
        for (Likes likes : likesList) {
            boolean is_check = likes.getIs_Check();
            if(is_check) {
                likeCount +=1;
            }
        }

        System.out.println("포스트에 likeCount 업데이트 수 (진행 전) : " + likeCount);
        board.likeCount(likeCount);
        BoardRepository.save(board);
        System.out.println("포스트에 likeCount 업데이트 수 : " + likeCount);

        return likeCount;

    }







}
