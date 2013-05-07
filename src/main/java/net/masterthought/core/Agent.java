package net.masterthought.core;

import net.masterthought.data.Any;
import net.masterthought.data.AnyPair;
import net.masterthought.data.Memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static net.masterthought.Utils.requires;

public class Agent {

    public Memory getMemory() {
        return memory;
    }

    public List<Any> getSkills() {
        return skills;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public void setSkills(List<Any> skills) {
        this.skills = skills;
    }

    private Memory memory = new Memory();

    private List<Any> skills = new ArrayList<Any>();

    public Agent() {
    }

    public <TOOL> Agent(TOOL... tools) {
        obtain(tools);
    }

    public <CATEGORY, KEY, VALUE> void bearInMind(CATEGORY category, KEY key, VALUE value) {
        requires(category, key);
        memory.remember(category, key, value);
    }

    public <TOOL> void obtain(TOOL... tools) {
        requires(tools);
        for (TOOL tool : tools) {
            this.skills.add(new Any(tool));
        }
    }

    public <TOOL> TOOL use(Class<TOOL> clazz) {
        requires(clazz);
        for (Any any : skills) {
            Class<?> classOfAny = any.get().getClass();
            if (isClassOrSubclass(clazz, classOfAny)) {
                return (TOOL) any.get();
            }
        }
        throw new RuntimeException("I don't know this skill: " + clazz);
    }

    private <OBJECT> boolean isClassOrSubclass(Class<OBJECT> clazz, Class<?> classOfAny) {
        return classOfAny.equals(clazz) ||
                classOfAny.getSuperclass().equals(clazz) ||
                clazz.isAssignableFrom(classOfAny);
    }

    public <CATEGORY, KEY, VALUE> VALUE recallFromMemory(CATEGORY category, KEY key, Class<VALUE> clazz) {
        requires(category, key, clazz);
        return memory.recall(category, key, clazz);
    }

    public <CATEGORY> List<AnyPair> recallAllForCategory(CATEGORY category) {
        requires(category);
        return memory.getAllForCategory(category);
    }


    public void doThe(Mission... missions) {
        requires(missions);
        for (Mission mission : missions) {
            mission.accomplish(this);
        }
    }

    public void doThe(Objective... objectives) {
        requires(objectives);
        for (Objective objective : objectives) {
            objective.complete(this);
        }
    }

    public void doAndConfirm(Objective... objectives) {
        requires(objectives);
        for (Objective objective : objectives) {
            objective.completeAndConfirm(this);
        }
    }

}
