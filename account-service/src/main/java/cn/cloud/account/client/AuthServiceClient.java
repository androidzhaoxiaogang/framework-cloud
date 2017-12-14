package cn.cloud.account.client;

import cn.cloud.account.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/uaa/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(User user);

	//@RequestMapping(method = RequestMethod.POST, value = "uaa/oauth/token", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)


}
