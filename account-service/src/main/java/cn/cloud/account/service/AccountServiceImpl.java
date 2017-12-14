package cn.cloud.account.service;

import cn.cloud.account.client.AuthServiceClient;
import cn.cloud.account.client.IdGenServiceClient;
import cn.cloud.account.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/12.
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private IdGenServiceClient idGenServiceClient;

    @Override
    public User createUser(User user) {
        long userId = idGenServiceClient.generateId();
        user.setId(userId);

        authServiceClient.createUser(user);
        return user;
    }

}
