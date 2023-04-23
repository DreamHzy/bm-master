package com.baimeng.bmmerchant.service;

import com.baimeng.bmcore.model.ApiRes;

public interface IndexService {
    ApiRes show();

    ApiRes noticeInfo(Integer noticeId);
}
