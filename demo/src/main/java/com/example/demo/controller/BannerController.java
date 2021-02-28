package com.example.demo.controller;

import com.example.demo.service.BannerService;
import com.example.demo.utils.ResultMessage;
import com.example.demo.vo.banner.BannerReq;
import com.example.demo.vo.banner.BannerRes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.elasticsearch.rest.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class BannerController {

    private static final Logger log = LoggerFactory.getLogger(BannerController.class);
    @Autowired
    private BeanFactory beanFactory;





    @Autowired
    private ResultMessage resultMessage;

    @ApiOperation("banner")
    @PostMapping({"/banner"})
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "",
            response = BannerRes.class
    )})
    public ResultMessage  getBanner(@RequestBody BannerReq bannerReq) {
        BannerService bannerService = beanFactory.getBean(BannerService.class);
        Optional<List<BannerRes>> optional = bannerService.getBanner(bannerReq);
        resultMessage.success("200","查询成功",optional);

        System.out.println(bannerService.hashCode());
        return resultMessage;
    }



    @ApiOperation("banner")
    @PostMapping({"/gBanner"})
    @ApiResponses({@ApiResponse(
            code = 200,
            message = "",
            response = BannerRes.class
    )})
    public ResultMessage  gBanner(@RequestBody BannerReq bannerReq) {
        BannerService bannerService = beanFactory.getBean(BannerService.class);
        Optional<List<BannerRes>> optional = bannerService.getBanner(bannerReq);
        resultMessage.success("200","查询成功",optional);

        System.out.println(bannerService.hashCode());
        return resultMessage;
    }

}
