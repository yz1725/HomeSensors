package com.letaotao.services.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserUpdateRequest implements Serializable {


    private static final long serialVersionUID = -8006686207279887944L;
    private String openid;
    private String id;
}
