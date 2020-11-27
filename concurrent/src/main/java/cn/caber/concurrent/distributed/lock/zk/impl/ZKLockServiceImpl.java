package cn.caber.concurrent.distributed.lock.zk.impl;

import cn.caber.concurrent.Runable.ZKTicketRunnable;
import cn.caber.concurrent.distributed.lock.zk.ZKLockService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZKLockServiceImpl implements ZKLockService {

    @Autowired
    private CuratorFramework client;

    private String lockPath = "/caber-lock";

    public  void sellTicket(Integer ticketNum) {

        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        ZKTicketRunnable ticketRunnable = new ZKTicketRunnable(ticketNum,lock);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(ticketRunnable,"线程"+i);
            thread.start();
            System.out.println(thread.getName()+"启动了");
        }
    }
}
