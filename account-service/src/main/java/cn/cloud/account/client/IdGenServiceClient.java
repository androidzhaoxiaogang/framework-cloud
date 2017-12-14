package cn.cloud.account.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/12/12.
 */

@FeignClient(name = "id-service")
public interface IdGenServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/idgen/new", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    long generateId();
}
