package cn.caber.concurrent.distributed.lock.redis.impl;

import cn.caber.concurrent.Runable.RedisTicketRunnable;
import cn.caber.concurrent.distributed.lock.redis.RedisLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisLockServiceImpl implements RedisLockService {

    @Autowired
    private RedissonClient redissonClient;
    String name = "lock";
    @Override
    public void sellTicket(Integer ticketNum) {

        RLock lock = redissonClient.getLock(name);
        RedisTicketRunnable redisTicketRunnable = new RedisTicketRunnable(ticketNum, lock);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(redisTicketRunnable,"线程"+i);
            thread.start();
            System.out.println(thread.getName()+"启动了");
        }
    }
}
