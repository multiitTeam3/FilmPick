package com.multi.mini.common.model.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private String receiveAddress;
    private String mailTitle;
    private String mailContent;
}
