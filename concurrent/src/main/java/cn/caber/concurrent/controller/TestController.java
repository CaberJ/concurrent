package cn.caber.concurrent.controller;

import cn.caber.concurrent.distributed.lock.redis.RedisLockService;
import cn.caber.concurrent.distributed.lock.zk.ZKLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ZKLockService lockService;

    @Autowired
    private RedisLockService redisLockService;

    /**
     * zk 分布式锁测试
     * @return
     */
    @RequestMapping("/zkLock")
    public String zkLock(){
         lockService.sellTicket(200);
        return "success";
    }


    /**
     * redis 分布式锁测试
     * @return
     */
        @RequestMapping("/redisLock")
    public String redisLock(){
        redisLockService.sellTicket(200);
        return "success";
    }
}
