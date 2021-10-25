package com.letaotao.services.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GongInfoUpdateRequest implements Serializable {
    private int id;
    private String formId;
    private String token;
    private String openId;
}
