package com.letaotao.services.controller;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.request.GongInfoUpdateRequest;
import com.letaotao.services.request.ReveiewCreateRequest;
import com.letaotao.services.response.GongInfoUpdateResponse;
import com.letaotao.services.response.HttpBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GongInfoController {

    @RequestMapping(method = RequestMethod.POST, value = "/gonginfo/update")
    public ResponseEntity<HttpBaseResponse> createReview(@RequestBody GongInfoUpdateRequest request) {
        LogUtil.info(log, "get the incoming GongInfoUpdateRequest " + request);

        GongInfoUpdateResponse response = new GongInfoUpdateResponse();
        response.setResult("1");

        return new ResponseEntity(response, HttpStatus.OK);

    }
}
