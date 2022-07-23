package cn.hongfei.user.redisSub;

import cn.hongfei.redisPubSub.redisSub.RedisMsg;
import cn.hongfei.user.entity.User;
import cn.hongfei.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSub implements RedisMsg {

    private static UserService userService;

    /**
     *  直接注入UserService会失败，这样直接注入会报空指针异常，因为spring不会给静态变量注入
     *  原因：当有连接接入时，会创建一个新的服务器类对象，而spring只会给IOC容器启动时创建的对象注入userService，连接接入时创建的对象并没有注入
     * @param userService
     */
    @Autowired
    public void  autoAutowired (UserService userService){
        this.userService = userService;
    }

    /**
     * 接收到redis通道消息后执行加积分操作
     * 消息规则：userID+","+score+","+money+","+count
     *
     * @param message 订阅的消息
     */
    @Override
    public void receiveMessage(String message) {
        System.out.println(message);
        String[] split = message.split(",");
        String s = split[0];
        User user = userService.getById(s);
        int score = user.getScore()+Integer.parseInt(split[1]);
        int money = user.getMoney()-(Integer.parseInt(split[2])*Integer.parseInt(split[3]));
        user.setScore(score);
        user.setMoney(money);
        userService.updateById(user);
    }

}
