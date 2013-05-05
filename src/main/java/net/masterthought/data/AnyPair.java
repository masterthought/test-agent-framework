package net.masterthought.data;

public class AnyPair<KEY, VALUE> {

    public KEY key;
    public VALUE value;

    public AnyPair(KEY key, VALUE value) {
        this.key = key;
        this.value = value;
    }

}
