package com.team.spick.repository;


import com.team.spick.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByModifiedAtDesc();


}
