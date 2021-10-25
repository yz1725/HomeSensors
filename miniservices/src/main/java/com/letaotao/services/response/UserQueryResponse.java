package com.letaotao.services.response;

import com.letaotao.services.model.UserModel;
import lombok.Data;

import java.util.List;

@Data
public class UserQueryResponse extends HttpBaseResponse {
    private List<UserModel> content;
}
