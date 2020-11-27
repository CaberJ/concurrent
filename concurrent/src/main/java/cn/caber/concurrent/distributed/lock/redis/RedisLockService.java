package cn.caber.concurrent.distributed.lock.redis;

public interface RedisLockService {

    void sellTicket(Integer ticketNum);
}
