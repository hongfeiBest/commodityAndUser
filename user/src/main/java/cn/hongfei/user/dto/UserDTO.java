package cn.hongfei.user.dto;

import lombok.Data;

@Data
public class UserDTO {
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
