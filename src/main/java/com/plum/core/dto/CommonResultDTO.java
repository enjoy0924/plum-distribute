package com.plum.core.dto;

/**
 * Created by Andy on 2015/10/11.
 */
public class CommonResultDTO {

    public static class Result{
        private int code = 0x0000;
        private String msg = "OK";

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    private Result result = new Result();
    private Object attach;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Object getAttach() {
        return attach;
    }

    public void setAttach(Object attach) {
        this.attach = attach;
    }
}
