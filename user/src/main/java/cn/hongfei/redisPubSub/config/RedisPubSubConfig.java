package cn.hongfei.redisPubSub.config;


import cn.hongfei.redisPubSub.redisSub.RedisMsg;
import cn.hongfei.redisPubSub.redisSub.RedisMsgImpl.ScheduleSub;
import cn.hongfei.user.redisSub.UserSub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisPubSubConfig {
    /**
     * Redis消息监听器容器
     *
     * @param connectionFactory /
     * @return /
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫ScheduleSub的通道，container.addMessageListener可以设置多通道
        container.addMessageListener(listenerAdapter(new ScheduleSub()), new PatternTopic("ScheduleSub"));
        //订阅商品模块，购买商品用户加积分
        container.addMessageListener(listenerAdapter(new UserSub()), new PatternTopic("user_score"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 配置消息接收处理类
     *
     * @param redisMsg 自定义消息接收类
     * @return Redis的监听适配器
     */
    @Bean
    @Scope("prototype")
    MessageListenerAdapter listenerAdapter(RedisMsg redisMsg) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以到源码里面看
        //注意2个通道调用的方法都要为receiveMessage
        return new MessageListenerAdapter(redisMsg, "receiveMessage");
    }
}
