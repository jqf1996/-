package com.example.demo.vo.banner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Banner响应对象")
public class BannerRes {
    @ApiModelProperty("bannerId")
    Integer id;
    @ApiModelProperty("图片地址")
    private String imageUrl;
    @ApiModelProperty("ios链接")
    String iosUrl;
    @ApiModelProperty("android链接")
    String androidUrl;
    @ApiModelProperty("是否需要交易登录")
    private boolean needLogin;
}
