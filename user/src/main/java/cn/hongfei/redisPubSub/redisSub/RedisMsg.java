package cn.hongfei.redisPubSub.redisSub;

import org.springframework.stereotype.Component;

@Component
public interface RedisMsg {
    /**
     * Redis订阅者接受消息的接口
     *
     * @param message 订阅的消息
     */
    void receiveMessage(String message);
}
