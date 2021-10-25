package com.letaotao.services.model;

import lombok.Data;

@Data
public class ReviewModel {

    private String id;
    private String result; // 1 success, 0 fail
    private String msg;
}
