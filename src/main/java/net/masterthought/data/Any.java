package net.masterthought.data;

public class Any<T> {

    private final T t;

    public Any(T t){
        if(t == null) throw new RuntimeException("You passed a null value");
        this.t = t;
    }

    public T get(){
        return t;
    }

    public boolean equals(Object o){
        if(o == null) return false;
        if(o instanceof Any){
            Any any = (Any) o;
            if(any.get() == t) return true;
            return any.get().equals(t);
        }
        else return false;
    }
}
