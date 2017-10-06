package cn.cloud.auth.controller;

import cn.cloud.auth.domain.User;
import cn.cloud.auth.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取当前用户信息")
    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    //@PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }
}
