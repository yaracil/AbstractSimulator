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
public class DequeueEvent extends AbstractEvent {

    public DequeueEvent(double time) {
        super(time);
        this.setTag("Salida del sistema");
    }

    @Override
    public void execute(AbstractSimulator simulator) {
        int count = simulator.getSystemCount();
        if (count > 1) {
            simulator.insert(new DequeueEvent(this.getTime() + simulator.getRandomWithPoissonDistribution(simulator.getMiu())));
        } else {
            ((FirstSimulator) simulator).timeSystemOffTemp = this.getTime();
        }
        simulator.setSystemCount(count - 1);

        //Registrando el tiempo de salida
        int eventIndex = simulator.getEventsHistogram().get(this.getTag());
        LinkedList<Double> aux = ((FirstSimulator) simulator).timeElementHistogram.get(eventIndex);
        aux.add(1, this.getTime());
        ((FirstSimulator) simulator).timeElementHistogram.put(eventIndex, aux);
    }
}
