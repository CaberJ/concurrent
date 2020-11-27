package cn.caber.concurrent.distributed.lock.zk;

public interface ZKLockService {

    void sellTicket(Integer ticketNum);
}
