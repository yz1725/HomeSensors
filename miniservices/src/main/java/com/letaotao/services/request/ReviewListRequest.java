package com.letaotao.services.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewListRequest implements Serializable {

    private static final long serialVersionUID = 766698401792465943L;
    String id;
    String type;
    String userId;


}
