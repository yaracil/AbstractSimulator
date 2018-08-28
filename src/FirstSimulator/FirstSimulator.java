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
    FirstSimulator(double time) {
        this.time = time;
    }
    public void execute(AbstractSimulator simulator) {
        System.out.println("The time is "+time);
    }
    
    public static void main(String[] args) {
//        new FirstSimulator().start();
    }
    
    void start() {
//        events = new ListQueue();
//        insert( new FirstSimulator(4.0));
//        insert( new FirstSimulator(1.0));
//        insert( new FirstSimulator(1.5));
//        insert( new FirstSimulator(2.0));
//
//        doAllEvents();
    }

}
