/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstSimulator;

import abstractsimulator.AbstractEvent;
import abstractsimulator.AbstractSimulator;
import java.util.LinkedList;

/**
 *
 * @author Yoe
 */
public class EnqueueEvent extends AbstractEvent {

    EnqueueEvent(double time) {
        super(time);
        this.setTag("Llegada al sistema");
    }

    public void execute(AbstractSimulator simulator) {
        int count = simulator.getSystemCount();
        if (count == 0) {
            simulator.insert(new DequeueEvent(this.getTime() + simulator.getRandomWithPoissonDistribution(simulator.getMiu())));
            ((FirstSimulator) simulator).timeSystemOffTotal += this.getTime() - ((FirstSimulator) simulator).timeSystemOffTemp;
            ((FirstSimulator) simulator).timeSystemOffTemp = 0;
        }
        simulator.setSystemCount(count + 1);
        simulator.insert(new EnqueueEvent(this.getTime() + simulator.getRandomWithPoissonDistribution(simulator.getLambda())));

        //Registrando el tiempo de llegada
        LinkedList<Double> aux = new LinkedList<>();
        aux.add(0, this.getTime());
        int eventIndex = simulator.getEventsHistogram().get(this.getTag());
        ((FirstSimulator) simulator).timeElementHistogram.put(eventIndex, aux);
    }

}
