package cn.cloud.auth.service;

import cn.cloud.auth.domain.User;
import cn.cloud.auth.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserMapper userMapper;

	@Override
	public void create(User user) {

		User existing = userMapper.selectOne(user);
		Assert.isNull(existing, "user already exists: " + user.getUsername());

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		userMapper.insert(user);

		log.info("new user has been created: {}", user.getUsername());
	}

	@Override
	public User findOne(String username) {
		return userMapper.selectByPrimaryKey(username);
	}
}
