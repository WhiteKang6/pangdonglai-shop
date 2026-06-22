package com.database.homework.pangdonglaishop.VO;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code; //0成功 1失败
    private String msg;//错误信息
    private T data;//返回的数据
    //有返回数据
    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>();
        result.setCode(0);
        result.setData(data);
        return result;
    }
    //无返回数据
    public static <T> Result<T> success(){
        Result<T> result=new Result<>();
        result.setCode(0);
        return result;
    }

    //失败
    public static<T> Result<T> error(String msg){
        Result<T> result=new Result<>();
        result.setCode(1);
        result.setMsg(msg);
        return result;
    }


}
