package cn.cloud.idgen.controller;

import cn.cloud.idgen.util.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(value="IdGeneratorController", description = "用户模块", produces= MediaType.APPLICATION_JSON_VALUE)
@RestController
public class IdGeneratorController {

    @Value(("${metadata.worker:1}"))
    private long  workerId;

    @Value(("${metadata.datacenter:1}"))
    private long  datacenterId;

    @ApiOperation("生成全局唯一id")
    @GetMapping(value = "/new")
    public long genId() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(workerId, datacenterId);
        return idWorker.nextId();
    }

}
