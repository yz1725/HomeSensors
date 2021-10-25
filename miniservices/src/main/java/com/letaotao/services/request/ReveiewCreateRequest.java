package com.letaotao.services.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReveiewCreateRequest implements Serializable {

    private static final long serialVersionUID = 8998446055754642157L;

    String id;
    String userId;
    List<String> data;

}
