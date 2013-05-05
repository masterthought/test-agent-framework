package net.masterthought;

public class Utils {

    public static void requires(Object... items){
        if(items == null || items.length == 0)
            throw new RuntimeException("Empty list");
        for(Object item : items){
            if(item == null) throw new RuntimeException("Null element");
        }
    }
}
