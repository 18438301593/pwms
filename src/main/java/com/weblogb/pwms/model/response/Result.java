package com.weblogb.pwms.model.response;

/**
* @author: Jiajiajia
* @Date: 2021/10/27
* @Description: 通用数据返回体
*/
public class Result {
    private Object data;
    private boolean flag;
    private String msg;

    public Result(Object data,boolean flag,String msg){
        this.data = data;
        this.flag = flag;
        this.msg = msg;

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Result of(Object data){
        return new Result(data,true,"Operation succeeded");
    }
    public static Result ofError(String msg){
        return new Result(null,false,msg);
    }
    public static Result ofException(Exception e){
        return new Result(null,false,e.getMessage());
    }
    public static Result of(Object data,boolean flag,String msg){
        return new Result(data,flag,msg);
    }
}
