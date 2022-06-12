package com.team.spick.repository;

import com.team.spick.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository <Reply, Long>{
    List<Reply> findAllByReply_id(Long reply_Id);
}
