package com.huangli.web.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.huangli.web.service.UserLoginService;
import com.huangli.web.vo.BaseLoginVO;
import com.huangli.web.vo.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxb
 * @version 1.0
 * @title UserLoginControlelr
 * @description
 * @create 2024/1/5 17:48
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    private UserLoginService loginService;

    /**
     * 用户登录
     * @Author liuxb
     * @param: loginVO
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@Validated @RequestBody BaseLoginVO loginVO) {
        return BaseResponse.success(loginService.login(loginVO));
    }
    /**
     * 用户登出
     * @Author liuxb
     * @param: loginVO
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public BaseResponse logout(HttpServletRequest request) {
        loginService.logout(request.getHeader("X-Token"));
        return BaseResponse.success();
    }

    /**
     * 构造角色等假数据
     * @Author liuxb
     * @param:
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("构造角色等假数据")
    @GetMapping("/info")
    public BaseResponse getInfo(@RequestParam("token") String token) {
        Map<String, Object> data = new HashMap<>();
        data.put("roles", JSONUtil.formatJsonStr("[\n" +
                "  {\n" +
                "    key: 'admin',\n" +
                "    name: 'admin',\n" +
                "    description: 'Super Administrator. Have access to view all pages.',\n" +
                "    routes: routes\n" +
                "  },\n" +
                "  {\n" +
                "    key: 'editor',\n" +
                "    name: 'editor',\n" +
                "    description: 'Normal Editor. Can see all pages except permission page',\n" +
                "    routes: routes.filter(i => i.path !== '/permission')// just a mock\n" +
                "  },\n" +
                "  {\n" +
                "    key: 'visitor',\n" +
                "    name: 'visitor',\n" +
                "    description: 'Just a visitor. Can only see the home page and the document page',\n" +
                "    routes: [{\n" +
                "      path: '',\n" +
                "      redirect: 'dashboard',\n" +
                "      children: [\n" +
                "        {\n" +
                "          path: 'dashboard',\n" +
                "          name: 'Dashboard',\n" +
                "          meta: { title: 'dashboard', icon: 'dashboard' }\n" +
                "        }\n" +
                "      ]\n" +
                "    }]\n" +
                "  }\n" +
                "]"));
        data.put("name", "管理员");
        data.put("avatar", "");
        data.put("introduction", "测试项目");
        return BaseResponse.success(data);
    }
}
