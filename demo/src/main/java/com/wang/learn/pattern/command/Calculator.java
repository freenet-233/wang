package com.wang.learn.pattern.command;

public class Calculator {
    public int calculate(Command command) {
        return command.execute();
    }
}
