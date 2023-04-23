package com.baimeng.bmmerchant.mapper;

import com.baimeng.bmmerchant.model.vo.BannerVO;
import com.baimeng.bmmerchant.model.vo.NoticeVO;

import java.util.List;

public interface IndexMapper {
    List<BannerVO> queryBanners(String url);

    List<NoticeVO> queryNotices(String url);

    String queryNoticesById(Integer noticeId);
}
