package cn.hongfei.redisPubSub.redisSub.RedisMsgImpl;


import cn.hongfei.redisPubSub.redisSub.RedisMsg;

public class ScheduleSub implements RedisMsg {
    @Override
    public void receiveMessage(String message) {
        System.out.println("ScheduleSub通道---" + message);
    }
}
