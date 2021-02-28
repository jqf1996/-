package com.example.demo.vo.banner;


import com.example.demo.vo.BaseReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Banner请求对象")
public class BannerReq extends BaseReq {
    @ApiModelProperty("banner的类型：1 要闻banner；2 VIP资讯banner")
    private Integer type;
}
