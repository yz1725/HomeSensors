package com.letaotao.services.controller;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.handler.UserHandler;
import com.letaotao.services.model.UserModel;
import com.letaotao.services.request.UserUpdateRequest;
import com.letaotao.services.response.HttpBaseResponse;
import com.letaotao.services.response.UserQueryResponse;
import com.letaotao.services.response.UserUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserHandler userHandler;


    @RequestMapping(method = RequestMethod.POST, value = "/user/update")
    public ResponseEntity<HttpBaseResponse> queryUser(@RequestBody UserUpdateRequest request) {
        LogUtil.info(log, "request update user : "+request);

        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setResult("1");

        return new ResponseEntity(userUpdateResponse, HttpStatus.OK);
    }


        @RequestMapping(method = RequestMethod.GET, value = "/user/query")
    public ResponseEntity<HttpBaseResponse> queryUser(@RequestParam("password") String password) {
        LogUtil.info(log, "request: "+password);

        UserQueryResponse userQueryResponse = new UserQueryResponse();
        List<UserModel> userList = new ArrayList<>();
        UserModel userModel = new UserModel();
        userModel.setId(123);
        userModel.setDescribes("test setDescribes");
        userModel.setName("test test");
        userList.add(userModel);
        userQueryResponse.setContent(userList);

        userQueryResponse.setResult("1");

        // 		String querySql = "select * from gonginfo where password = '"+password+"'";
        return new ResponseEntity(userQueryResponse, HttpStatus.OK);
    }
}
