package com.mingben.betplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    @Builder.Default
    private boolean success = Boolean.TRUE;

    private Object data;

    private String message;
}
