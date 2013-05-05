package net.masterthought.core;

public abstract class Objective<R> {

    public Objective(R expectedResult) {
        this.expectedResult = expectedResult;
    }

    protected R expectedResult;

    public abstract R complete(Agent agent);

    public abstract void completeAndConfirm(Agent agent);

    public abstract R getResult();

}
