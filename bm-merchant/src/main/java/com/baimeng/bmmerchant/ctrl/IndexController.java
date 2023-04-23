package com.baimeng.bmmerchant.ctrl;

import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmmerchant.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "首页相关接口")
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Resource
    IndexService indexService;

    @ApiOperation("首页信息展示")
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ApiRes show() {
        return indexService.show();
    }

    /**
     * 公告平台信息查看
     */
    @ApiOperation("公告平台信息查看")
    @RequestMapping(value = "/noticeInfo", method = RequestMethod.GET)
    public ApiRes noticeInfo(
            @ApiParam(name = "noticeId", value = "公告平台id") @RequestParam(value = "noticeId") @NonNull Integer noticeId
    ) {
        return indexService.noticeInfo(noticeId);
    }


}
