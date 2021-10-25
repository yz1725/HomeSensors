package com.letaotao.services.response;

import lombok.Data;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class HttpBaseResponse implements Serializable {

    private static final long serialVersionUID = -7716999220109658753L;

    private static final DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US);

    private String timestamp = dateformat.format(new Date());

    private String id;
    private String msg;
    private String result;
}
