package cn.hongfei.common.user.entity;

import lombok.Data;

/**
 * @author : hongfeiBest
 * @date : 2022/8/13 14:47
 * @Version: 1.0
 * @Desc : annotation......
 */
@Data
public class User {
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
