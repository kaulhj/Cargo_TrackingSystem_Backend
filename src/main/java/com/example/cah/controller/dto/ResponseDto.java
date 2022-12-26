package com.example.cah.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ResponseDto<T> {

    private String status;


    private T userInfo;

    public ResponseDto(String m, T result){
        this.status = m;
        this.userInfo = result;

    }

}
