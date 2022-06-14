package com.team.spick.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplytDto {

    private Long board_id;
    private String reply_nickname;
    private String reply_picURL;
    private String reply_text;

}
