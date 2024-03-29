package com.team.spick.domain;

import com.team.spick.dto.ReplytDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Reply extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long replyId;

    @Column(nullable = false)
    private String reply_nickname;

    @Column(nullable = false)
    private String reply_text;

    @Column(nullable = false)
    private String reply_picURL;

    @ManyToOne
    @JoinColumn(name = "Board_ID", nullable = false)
    private Board board;

    public Reply(String reply_nickname, String reply_text, String reply_picURL, Board board) {
        this.reply_nickname = reply_nickname;
        this.reply_text = reply_text;
        this.reply_picURL = reply_picURL;
        this.board= board;
    }

    public Reply(ReplytDto replytDto) {
        this.reply_nickname = replytDto.getReply_nickname();
        this.reply_text = replytDto.getReply_text();
        this.reply_picURL = replytDto.getReply_picURL();
    }

    public void update(ReplytDto replytDto) {

        this.reply_text = replytDto.getReply_text();
    }
}
