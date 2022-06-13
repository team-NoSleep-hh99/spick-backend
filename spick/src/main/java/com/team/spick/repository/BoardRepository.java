package com.team.spick.repository;


import com.team.spick.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findAllByOrderByCreatedAtDesc();
    Optional<Board> findByBoardId (Long boardId);
    List<Board> findAllByUserId (Long userId);
    List<Board> findAllByBoardId (Long boardId);
    List<Board> findAllByOrderByLikeCountDesc ();
}
