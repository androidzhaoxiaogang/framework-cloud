package cn.cloud.auth.service;

import cn.cloud.auth.client.IdServiceClient;
import cn.cloud.auth.domain.User;
import cn.cloud.auth.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private IdServiceClient idServiceClient;

	@Autowired
	private UserMapper userMapper;

	@Override
	public User create(User user) {
		final long id = idServiceClient.getId();
		final String pwd = encoder.encode(user.getPassword());

		user.setId(id);
		user.setPassword(pwd);

		userMapper.insert(user);

		log.info("new user has been created: {}", id);

		return user;
	}

	@Override
	public User findOne(long id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
