package cn.hongfei.user.service.impl;

import cn.hongfei.user.entity.User;
import cn.hongfei.user.mapper.UserMapper;
import cn.hongfei.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
