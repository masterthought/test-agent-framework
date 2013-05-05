package net.masterthought;


import java.io.OutputStream;

public abstract class Result<T> {

    private T t;

    public Result(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }

    public abstract void attach(OutputStream outputStream);
    public abstract void attach(String s);
    public abstract void attach(StringBuffer s);
}
