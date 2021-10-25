package com.letaotao.services.response;

import lombok.Data;

@Data
public class ReviewListResponse extends HttpBaseResponse {
    String startIndex;
    int blockSize;
    boolean isDirectionUp;
}
