/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstSimulator;

import abstractsimulator.*;

/**
 *
 * @author Yoe
 */
class FirstSimulator extends AbstractSimulator {

    public FirstSimulator() {
        // Valores para lambda y miu
        super(1, 2);
    }

    public static void main(String[] args) {
        new FirstSimulator().start();
    }

    void start() {
        init();
        this.setTimeToEnd(100);

        this.insert(new EnqueueEvent(0.1));

        this.run();

        System.out.println(this.getReport());
    }

}
