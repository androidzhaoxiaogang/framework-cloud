package cn.cloud.auth.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "id-service", fallback = IdServiceClient.IdServiceClientFallback.class)
public interface IdServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/idgen/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	long getId();

	@Component
	class IdServiceClientFallback implements IdServiceClient {

		private static final Logger log = LoggerFactory.getLogger(IdServiceClientFallback.class);

		@Override
		public long getId() {
			log.info("获取用户id失败，请查看id-service是否正常工作");

			return 0;
		}
	}

}
