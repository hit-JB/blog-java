package com.hit.vueblog.commom.lang;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class Result implements Serializable {
    private int code;//200正常 非200 异常
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result r = new Result();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(data);
        //data是通过map类型，运行时构建，并且可以，通过json格式传回客户端
        return r;
    }
    public static Result success(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static Result fail(String msg){
        return fail(400,msg,null);
    }
    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
