package com.team.spick.repository;

import com.team.spick.domain.Board;
import com.team.spick.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository <Reply, Long>{
    List<Reply> findAllByBoardOrderByModifiedAtDesc(Board board);
    void deleteByBoard(Board board);
}
