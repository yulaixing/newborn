package com.kanzhun;

/**
 * @author:xing.yl
 * @date: 18/10/27
 */
public class ResultSet<T>  {


    private int rescode;
    private String resmsg;
    private T content;


    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
