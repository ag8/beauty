package org.ag.ants;

import org.ag.ants_utils.Initializer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world!!");

        Initializer.init();
        new RunBAnt().run();
    }
}
