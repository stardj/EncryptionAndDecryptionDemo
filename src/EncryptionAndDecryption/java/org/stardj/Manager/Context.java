package org.stardj.Manager;

import org.stardj.CodingStrategy.IStrategy;


/**
 * Created by stardj on 17/6/1.
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate(String str) {
        this.strategy.operate(str);
    }
}
