package net.masterthought.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kostas Mamalis kostas@masterthought.net
 * Insired by KittyCache but doesn't restrict the memory to types of objects
 */

public class Memory {

    private Map<Any, List<AnyPair>> core = new ConcurrentHashMap<Any, List<AnyPair>>();


    public <CATEGORY, KEY, VALUE> void remember(CATEGORY category, final KEY key, final VALUE value) {
        Any anyCategory = new Any(category);
        AnyPair anyPair = new AnyPair(key, value);
        Any categoryFound = categoryIsFound(category);
        if (categoryFound != null) {
            List<AnyPair> keyValuePairs = core.get(categoryFound);
            keyValuePairs = updateKeyValuePairs(key, anyPair, keyValuePairs);
            core.put(anyCategory,keyValuePairs);
        } else {
            createCategoryInMemory(category, key, value);
        }
    }

    public <VALUE, CATEGORY, KEY> VALUE recall(CATEGORY category, KEY key, Class<VALUE> clazz) {
        List<AnyPair> pairs = getAllForCategory(category);
        if(pairs == null || pairs.isEmpty()) return null;
        for(AnyPair pair : pairs){
            if(pair.key == key || pair.equals(key)){
                if(pair.value.getClass() == clazz) {
                    return (VALUE) pair.value;
                }
                else throw new RuntimeException("Expected value isn't of type: " + clazz);
            }
        }
        return null;
    }

    public <CATEGORY> List<AnyPair> getAllForCategory(CATEGORY category){
        Set<Any> categories = getCategories();
        for(Any anyCategory : categories){
            if(anyCategory.equals(new Any(category))){
                return core.get(anyCategory);
            }
        }
        return null;
    }

    private Set<Any> getCategories() {
        return core.keySet();
    }

    private <CATEGORY, KEY, VALUE> void createCategoryInMemory(CATEGORY category, final KEY key, final VALUE value) {
        core.put(new Any(category), new ArrayList<AnyPair>() {
            {
                add(new AnyPair(key, value));
            }
        });
    }

    private <KEY> List<AnyPair> updateKeyValuePairs(KEY key, AnyPair anyPair, List<AnyPair> keyValuePairs) {
        if (keyValuePairs != null && !keyValuePairs.isEmpty()) {
            AnyPair pair = keyIsFound(key, keyValuePairs);
            if (pair != EMPTY) {
                keyValuePairs.remove(pair);
            }
            keyValuePairs.add(anyPair);
        }
        return keyValuePairs;
    }

    private <CATEGORY> Any categoryIsFound(CATEGORY category){
        Set<Any> categories = getCategories();
        for(Any anyCategory : categories){
            if(anyCategory.equals(new Any(category))) return anyCategory;
        }
        return null;
    }

    private <KEY> AnyPair keyIsFound(KEY key, List<AnyPair> keyValuePairs) {
        for (AnyPair keyValuePair : keyValuePairs) {
            if (keyValuePair.key.getClass().equals(key.getClass())) {
                if (keyValuePair.key == key || keyValuePair.equals(key)) {
                    return keyValuePair;
                }
            }
        }
        return EMPTY;
    }

    private final AnyPair EMPTY = new AnyPair(null, null);

}
