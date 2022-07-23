package cn.hongfei.userTest;

import cn.hongfei.common.util.CompareUtil;
import cn.hongfei.user.dto.UserDTO;
import cn.hongfei.user.entity.User;

public class UserTest {
    public static void main(String[] args) {
        User user = new User();
        user.setId("123456");
        user.setName("aSan");
        user.setPassword("123456");
        UserDTO userDTO = new UserDTO();
        userDTO.setId("123456");
        userDTO.setName("aSan");
        userDTO.setPassword("123456");
        boolean b = CompareUtil. compareObject(user, userDTO);
        System.out.println("两个实体类是否相同："+b);
    }
}
