package com.example.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
@ApiModel("请求对象")
public class BaseReq {
    @ApiModelProperty("时间戳")
    String ts;
}
