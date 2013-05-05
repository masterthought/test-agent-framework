package net.masterthought.data;

import java.util.ArrayList;
import java.util.List;

public class CategoryKeys<CATEGORY,KEY> {

    private Any<CATEGORY> category;
    private List<Any> keys = new ArrayList<Any>();

    public CategoryKeys(CATEGORY category){
        this.category = new Any<CATEGORY>(category);
    }

    public CategoryKeys(CATEGORY category, KEY... keys){
        this.category = new Any<CATEGORY>(category);
        for(KEY key : keys){
            this.keys.add(new Any<KEY>(key));
        }
    }

    public void add(KEY key){
        this.keys.add(new Any<KEY>(key));
    }
}
