package net.masterthought.data;

import java.util.ArrayList;
import java.util.List;

public class Category<T> {

    private List<Any> keys = new ArrayList<Any>();

    private String name;

    private T t;

    public Category(T t, String name){
        this.t = t;
        this.name = name;
    }

    public <KEY> Category(T t, String name, KEY... keys){
        this.t = t;
        this.name = name;
        for(KEY key : keys){
            Any<KEY> keyObj = new Any<KEY>(key);
            this.keys.add(keyObj);
        }
    }
}
