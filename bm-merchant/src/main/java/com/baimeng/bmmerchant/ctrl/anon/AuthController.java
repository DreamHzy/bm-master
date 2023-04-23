/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baimeng.bmmerchant.ctrl.anon;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.baimeng.bmcore.cache.RedisUtil;
import com.baimeng.bmcore.constants.CS;
import com.baimeng.bmcore.exception.BizException;
import com.baimeng.bmcore.model.ApiRes;
import com.baimeng.bmmerchant.ctrl.CommonCtrl;
import com.baimeng.bmmerchant.model.dto.PwdLoginDTO;
import com.baimeng.bmmerchant.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录鉴权
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021-04-27 15:50
 */
@Api(tags = "登录鉴权相关接口")
@Slf4j
@RestController
@RequestMapping("/api/anon/auth")
public class AuthController extends CommonCtrl {

    @Resource
    private AuthService authService;

    /**
     * 密码登录
     **/
    @ApiOperation("密码登录")
    @RequestMapping(value = "/pwdLogin", method = RequestMethod.POST)
    public ApiRes validate(@RequestBody PwdLoginDTO pwdLoginDTO) throws BizException {
//        String account = pwdLoginDTO.getUsername();  //用户名 i account, 已做base64处理
//        String pwd = pwdLoginDTO.getPassword();    //密码 i passport,  已做base64处理


        String account = Base64.decodeStr(pwdLoginDTO.getUsername());  //用户名 i account, 已做base64处理
        String pwd = Base64.decodeStr(pwdLoginDTO.getPassword());    //密码 i passport,  已做base64处理
        // 返回前端 accessToken
        String accessToken = authService.auth(account, pwd);
        return ApiRes.ok4newJson(CS.ACCESS_TOKEN_NAME, accessToken);
    }


}
