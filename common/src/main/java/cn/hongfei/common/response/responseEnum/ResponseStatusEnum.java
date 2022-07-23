package cn.hongfei.common.response.responseEnum;

/**
 * 返回消息类型
 */


public enum ResponseStatusEnum {

    SUCCESS(200,"请求成功"),
    FAILED(400,"请求失败"),
    SERVER_ERROR(500,"服务器错误")
    ;

    private int code;
    private String message;

    ResponseStatusEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code=code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }
}
