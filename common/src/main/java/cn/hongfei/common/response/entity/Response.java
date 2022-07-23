package cn.hongfei.common.response.entity;

import cn.hongfei.common.response.responseEnum.ResponseStatusEnum;
import lombok.Data;

/**
 * 统一处理返回类型
 */

@Data
public class Response {
    private int code;
    private String message;
    private Object data;

    public Response setData(Object data){
        this.data=data;
        return this;
    }

    public Response(ResponseStatusEnum responseStatusEnum){
        this.code=responseStatusEnum.getCode();
        this.message=responseStatusEnum.getMessage();
    }

    public Response(ResponseStatusEnum responseStatusEnum, Object data){
        this.code=responseStatusEnum.getCode();
        this.message=responseStatusEnum.getMessage();
        this.data=data;
    }

    public Response(int code, String message){
        this.code=code;
        this.message=message;
    }

    public static Response SUCCESS(){
        return new Response(ResponseStatusEnum.SUCCESS);
    }


    public static Response successAndData(Object data){
        return new Response(ResponseStatusEnum.SUCCESS,data);
    }

    public static Response FAILED(){
        return new Response(ResponseStatusEnum.FAILED);
    }

    public static Response failedAndMessage(String message){
        return new Response(ResponseStatusEnum.FAILED.getCode(),message);
    }

    public static Response SERVER_ERROR(){
        return new Response(ResponseStatusEnum.SERVER_ERROR);
    }

}
