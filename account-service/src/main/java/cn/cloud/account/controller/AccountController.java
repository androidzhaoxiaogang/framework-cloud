package cn.cloud.account.controller;

import cn.cloud.account.client.IdGenServiceClient;
import cn.cloud.account.domain.User;
import cn.cloud.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/12/12.
 */

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public User createNewAccount(@Valid @RequestBody User user) {
        return accountService.createUser(user);
    }


}



