package cn.cloud.auth.service;


import cn.cloud.auth.domain.User;

public interface UserService {

	User create(User user);

	User findOne(long id);

}
