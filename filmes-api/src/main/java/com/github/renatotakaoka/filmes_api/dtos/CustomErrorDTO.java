package com.github.renatotakaoka.filmes_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomErrorDTO {

    private String timestamp;
    private Integer status;
    private String error;
    private String path;
}