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

    public FirstSimulator(double lambda, double miu) {        
        super(lambda, miu);
    }

    public static void main(String[] args) {
        // Valores para lambda y miu por parámetros
        FirstSimulator simulation= new FirstSimulator(1,2);
        simulation.start();
        System.out.println(simulation.GenerateReport(false));
    }

    void start() {
        init();        
        this.setTimeToEnd(1000);
        this.insert(new EnqueueEvent(0.001));
        this.run();       
    }
}
