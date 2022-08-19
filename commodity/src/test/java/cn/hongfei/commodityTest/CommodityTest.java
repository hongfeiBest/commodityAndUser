package cn.hongfei.commodityTest;

import cn.hongfei.common.user.entity.User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author : hongfeiBest
 * @date : 2022/8/13 14:38
 * @Version: 1.0
 * @Desc : annotation......
 */
public class CommodityTest {

    public static void main(String[] args) {
        String url = "http://localhost:8080/user/add2";
        RestTemplate template = new RestTemplate();
        User user1 = new User();
        user1.setName("tom");
        user1.setPassword("123456");
        user1.setAge(23);
        user1.setSex(2);
        user1.setPhone("18526353241");
        user1.setEmil("256945@qq.com");
        User user = template.postForObject(url, user1, User.class);
        System.out.println(user);
    }
}
