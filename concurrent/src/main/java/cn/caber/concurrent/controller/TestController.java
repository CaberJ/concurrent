package cn.caber.concurrent.controller;

import cn.caber.concurrent.distributed.lock.zk.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private LockService lockService;

    /**
     * zk 分布式锁测试
     * @return
     */
    @RequestMapping("/zkLock")
    public String doIt(){
         lockService.sellTicket(200);
        return "success";
    }
}
