package com.letaotao.services.response;

import com.letaotao.services.model.TemperatureSensorModel;
import com.letaotao.services.model.UserModel;
import lombok.Data;

import java.util.List;

@Data
public class TemperatureListResponse extends HttpBaseResponse {
    private List<TemperatureSensorModel> content;

}
