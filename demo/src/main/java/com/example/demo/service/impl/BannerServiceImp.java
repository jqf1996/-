package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.controller.BannerController;
import com.example.demo.dao.Banner;
import com.example.demo.mapper.BannerMapper;
import com.example.demo.service.BannerService;
import com.example.demo.utils.redis.Const;
import com.example.demo.utils.redis.RedisUtil;
import com.example.demo.vo.banner.BannerReq;
import com.example.demo.vo.banner.BannerRes;
import lombok.val;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
@Scope("prototype")
public class BannerServiceImp implements BannerService {

    private static final Logger log = LoggerFactory.getLogger(BannerService.class);

    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private RedisUtil redisUtil;
  //  @Value("${file.picUrl:http://47.100.52.139/bwnewsres/img/}")
    private String picUrl;

    @Override
    public Optional<List<BannerRes>> getBanner(BannerReq req) {
        List<BannerRes> arrayList = new ArrayList();  //返回一个bannerres实体的list

        if(req.getType()!=null){ //判断请求类型是否是空，如果不是空,默认走缓存方式取
            List<BannerRes> list = redisUtil.lGet(Const.allBannersByTypeKey + req.getType(), 0, -1);
            //判断取过来的是不是空的，如果不是空的
            if (list!=null&&!list.isEmpty()){
                arrayList=list;
                log.info("缓存中获取成功",arrayList);
            }
            else{//如果是空的，调用cacheBannersByType 查询数据库，加入缓存的函数
                log.info("开始查询数据库");
                arrayList = cacheBannersByType(req.getType());
            }
        }

        return Optional.of(arrayList);
    }

    public List<BannerRes> cacheBannersByType(Integer type) {
        List<BannerRes> resList = new ArrayList();//new 一个list容器，存放返回数据
        if (type != null) {  //type不是空 就去数据库找

            //plus构造器wapper  注意类型是实体
            QueryWrapper<Banner> wrapper = new QueryWrapper<>();
            //构造器条件设置
            wrapper.eq("type", type)
                    .orderByAsc(new String[]{"sort_num"});
            //从数据库中查询到了数据 list
            List<Banner> list = bannerMapper.selectList(wrapper);

            BannerRes bannerRes;
            for (Banner banner: list) {//遍历list 得到一个个实体
                bannerRes = new BannerRes();
                BeanUtils.copyProperties(banner, bannerRes);//工具类copy进去 也可以get,set方法
                if (!TextUtils.isEmpty(banner.getBannerImgName())) {
                    bannerRes.setImageUrl(this.picUrl + banner.getBannerImgName());//图片地址另外set进去
                }
                resList.add(bannerRes);//每个返回的实体类值，加到对应的List容器中去
            }
            redisUtil.lSetList(Const.allBannersByTypeKey + type,resList);//加入到缓存中去
        }

        return resList;
    }
}
