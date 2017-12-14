package cn.cloud.auth.controller;

import cn.cloud.auth.domain.User;
import cn.cloud.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Api(value="UserController", description = "用户模块", produces= MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取当前用户信息")
    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user) {
        return userService.create(user);
    }
}
