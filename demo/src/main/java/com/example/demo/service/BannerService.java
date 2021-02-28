package com.example.demo.service;

import com.example.demo.vo.banner.BannerReq;
import com.example.demo.vo.banner.BannerRes;

import java.util.List;
import java.util.Optional;

public interface BannerService {
    Optional<List<BannerRes>> getBanner(BannerReq var1);
}
