package com.baimeng.bmmerchant.service.Impl;

import com.baimeng.bmcomponentsoss.config.AliyunOssYmlConfig;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmmerchant.mapper.IndexMapper;
import com.baimeng.bmmerchant.model.vo.BannerVO;
import com.baimeng.bmmerchant.model.vo.NoticeVO;
import com.baimeng.bmmerchant.model.vo.ShowVO;
import com.baimeng.bmmerchant.service.IndexService;
import com.baimeng.bmservice.impl.IBBannerService;
import com.baimeng.bmservice.impl.IBNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    IBBannerService ibBannerService;
    @Resource
    IBNoticeService ibNoticeService;
    @Resource
    AliyunOssYmlConfig aliyunOssYmlConfig;
    @Resource
    IndexMapper indexMapper;

    @Override
    public ApiRes show() {
        List<BannerVO> bannerResList = indexMapper.queryBanners(aliyunOssYmlConfig.getUrl());
        List<NoticeVO> noticeResList = indexMapper.queryNotices(aliyunOssYmlConfig.getUrl());
        ShowVO showRes = new ShowVO();
        showRes.setNoticeRes(noticeResList);
        showRes.setBannerRes(bannerResList);
        return ApiRes.ok(showRes);
    }

    @Override
    public ApiRes noticeInfo(Integer noticeId) {
        String content = indexMapper.queryNoticesById(noticeId);
        return ApiRes.ok(content);
    }
}
