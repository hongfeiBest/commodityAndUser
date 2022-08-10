package cn.hongfei.user.controller;

import cn.hongfei.common.response.entity.Response;
import cn.hongfei.user.entity.User;
import cn.hongfei.user.helper.UserHelper;
import cn.hongfei.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserHelper userHelper;

    @GetMapping("/login")
    public Response login(HttpServletRequest request, @RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord){
        return userHelper.login(request,userName,passWord,userService,redisTemplate);
    }

    @PostMapping("/add")
    public String add(@RequestBody User user){
        return UserHelper.add(user,userService);
    }

    @GetMapping("/list")
    public Response list(@RequestParam(required = false,defaultValue = "0") int page, @RequestParam(required = false,defaultValue = "10")int limit){
        List list = UserHelper.list(userService, page, limit);
        return Response.successAndData(list);
    }

    @GetMapping("/getRedisTime")
    public String getRedisTime(){
        return UserHelper.getRedisTime(redisTemplate);
    }
}
