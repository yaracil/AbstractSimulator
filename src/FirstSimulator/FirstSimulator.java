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
        
        //        Caso 1
        FirstSimulator simulation= new FirstSimulator(1,2);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.println(simulation.GenerateReport());
        
       //        Caso 2
        simulation=new FirstSimulator(1, 4);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.println(simulation.GenerateReport());        
        
        //        Caso 3
        simulation=new FirstSimulator(2, 3);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.println(simulation.GenerateReport());
        
    }

    void start() {
        init();        
        this.setTimeToEnd(1000000);
        this.insert(new EnqueueEvent(0.0));
        this.run();       
    }
}
