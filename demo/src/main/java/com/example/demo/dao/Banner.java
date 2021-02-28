package com.example.demo.dao;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("news_biz_banner_tbl")
public class Banner {
    private Integer id;
    private Integer type;
    private String bannerImgName;
    private String iosUrl;
    private String androidUrl;
    private Integer sortNum;
    private String createdUser;
    private Timestamp createdTime;
    private String updatedUser;
    private Timestamp updatedTime;
}
