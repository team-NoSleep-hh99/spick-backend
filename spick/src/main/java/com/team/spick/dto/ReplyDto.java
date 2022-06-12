package com.team.spick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private String reply_nickname;
    private String reply_picURL;
    private String reply_text;
}
