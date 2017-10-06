package cn.cloud.auth.service;


import cn.cloud.auth.domain.User;

public interface UserService {

	void create(User user);

	User findOne(String username);

}
