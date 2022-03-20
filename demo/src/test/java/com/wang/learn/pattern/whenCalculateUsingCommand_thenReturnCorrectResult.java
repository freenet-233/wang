package com.wang.learn.pattern;


import com.wang.learn.pattern.command.AddCommand;
import com.wang.learn.pattern.command.Calculator;

public class whenCalculateUsingCommand_thenReturnCorrectResult {


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(new AddCommand(3, 7));
        assert (result==8);
    }
}
