/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstSimulator;

import abstractsimulator.AbstractEvent;
import abstractsimulator.AbstractSimulator;

/**
 *
 * @author Yoe
 */
public class EnqueueEvent extends AbstractEvent {

    EnqueueEvent(double time) {
        super(time);
        this.setTag("Llegada al sistema");
    }

    @Override
    public void execute(AbstractSimulator simulator) {
        int count = simulator.getSystemCount();
        if (count == 0) {
            simulator.setSystemCount(1);
            simulator.insert(new DequeueEvent(this.getTime() + simulator.getRandomWithPoissonDistribution(simulator.getMiu())));
        } else {            
            simulator.setSystemCount(count++);
            simulator.insert(new EnqueueEvent(this.getTime() + simulator.getRandomWithPoissonDistribution(simulator.getLambda())));
        }
    }
}
