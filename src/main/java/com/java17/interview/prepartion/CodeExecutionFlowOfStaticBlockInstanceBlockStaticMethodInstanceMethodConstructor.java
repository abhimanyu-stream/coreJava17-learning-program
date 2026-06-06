package com.java17.interview.prepartion;

public class CodeExecutionFlowOfStaticBlockInstanceBlockStaticMethodInstanceMethodConstructor {

    static int staticVar = 1;
    int instanceVar = 2;

    static {
        System.out.println("Static block is executed first.");
        staticVar = 10;
        System.out.println("Static variable value: " + staticVar);
    }

    {
        System.out.println("Instance block is executed before constructor.");
        instanceVar = 20;
        System.out.println("Instance variable value: " + instanceVar);
    }

    public CodeExecutionFlowOfStaticBlockInstanceBlockStaticMethodInstanceMethodConstructor() {
        System.out.println("Constructor is executed after instance block.");
    }

    public static void staticMethod() {
        System.out.println("Static method is executed without creating an instance.");
        System.out.println("Static variable value: " + staticVar);
    }


    public void instanceMethod() {
        System.out.println("Instance method is executed After creating an instance.");
        System.out.println("Instance variable value: " + instanceVar);
        System.out.println("Instance method can access static variable(s) and instance variable(s) too "+ staticVar + " " + instanceVar);


    }
    public static void main(String[] args) {
        CodeExecutionFlowOfStaticBlockInstanceBlockStaticMethodInstanceMethodConstructor example = new CodeExecutionFlowOfStaticBlockInstanceBlockStaticMethodInstanceMethodConstructor();
        staticMethod();
        example.instanceMethod();
    }
}
