package cn.hongfei.user.helper;

import cn.hongfei.common.response.entity.Response;
import cn.hongfei.common.util.CompareUtil;
import cn.hongfei.user.entity.User;
import cn.hongfei.user.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Component
public class UserHelper {
    //新增用户
    public static String add(User user, UserServiceImpl userService) {
        if (ObjectUtils.isNotEmpty(user.getLoginName())) {
            //校验登录名是否存在
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("login_name", user.getLoginName());
            int count = userService.count(userQueryWrapper);
            if (count > 0) {
                return "用户已存在~~~";
            }
        }
        user.setScore(10);
        user.setMoney(500);
        boolean save = userService.save(user);
        if (save) {
            return "新增用户成功";
        }
        return "新增用户失败";
    }

    //获取用户列表
    public static List<User> list(UserServiceImpl userService, int page, int limit) {
        return userService.list();
    }


    //获取redis中存的时间
    public static String getRedisTime(StringRedisTemplate redisTemplate) {
        String time = redisTemplate.opsForValue().get("time");
        if (time != null) {
            return time;
        }
        return "redis中未找到时间请稍后~~~";
    }

    /**
     * 登陆方法
     *
     * @param request     请求体
     * @param userName    用户名
     * @param passWord    密码
     * @param userService IService
     * @return
     */
    public Response login(HttpServletRequest request, String userName, String passWord, UserServiceImpl userService) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("login_name", userName).eq("password", passWord);
        int count = userService.count(userQueryWrapper);
        if (count > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            session.setAttribute("password", passWord);
            System.out.println("session+name" + session.getAttributeNames());
            System.out.println("session+userName" + session.getAttribute("userName"));
            return Response.successAndData("登录成功~~~");
        }
        return Response.FAILED();
    }

    /**
     * 校验两个实体类是否相同
     */
    public void checkClass() {
        User user1 = new User();
        User user2 = new User();

        user1.setId("123456");
        user1.setName("haha");
        user1.setPassword("1234567");
        user1.setSex(1);
        user1.setAge(21);
        user1.setPhone("123456");
        user1.setScore(10);

        user2.setId("123456");
        user2.setName("haha");
        user2.setPassword("123456");
        user2.setSex(1);
        user2.setAge(21);
        user2.setPhone("123456");

        String[] arr = {"score"};

        CompareUtil.compareObject(user1,user2);

        Map<String, List<Object>> stringListMap = CompareUtil.compareFields(user1, user2, arr);

        System.out.println("比较两个实体类的结果为："+stringListMap);

    }

}
