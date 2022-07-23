package cn.hongfei.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type= IdType.UUID)
    private String id;
    private String name;
    private String loginName;
    private String password;
    private Integer age;
    private Integer sex;
    private String phone;
    private String emil;
    private Integer score;
    private Integer money;
}
