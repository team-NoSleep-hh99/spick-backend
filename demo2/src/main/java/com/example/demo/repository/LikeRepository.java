package com.example.demo.repository;


import com.example.demo.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserIdAndPostId(Long userId, Long boardId);
    List<Likes> findAllByBoardId(Long boardId);
    List<Likes> findAllByUserId(Long userId);
    Optional<Likes> findByBoardId(Long boardId);

}
