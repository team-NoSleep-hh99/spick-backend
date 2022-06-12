package com.team.spick.model;

import com.team.spick.dto.ReplyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reply {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long reply_id;

    @Column(nullable = false)
    private String reply_nickname;

    @Column(nullable = false)
    private String reply_picURL;

    @Column(nullable = false)
    private String reply_text;

    public Reply(ReplyDto requestDto, Long reply_id) {
        this.reply_id = reply_id;
        this.reply_nickname = requestDto.getReply_nickname();
        this.reply_picURL = requestDto.getReply_picURL();
        this.reply_text = requestDto.getReply_text();
    }
}
