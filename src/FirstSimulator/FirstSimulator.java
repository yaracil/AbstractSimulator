/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstSimulator;

import abstractsimulator.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Yoe
 */
class FirstSimulator extends AbstractSimulator {

    public Map<Integer, LinkedList<Double>> timeElementHistogram;
    public double timeSystemOffTemp;
    public double timeSystemOffTotal;

    public FirstSimulator(double lambda, double miu) {
        super(lambda, miu);
        timeElementHistogram = new TreeMap<Integer, LinkedList<Double>>();
        timeSystemOffTemp = 0.0;
        timeSystemOffTotal = 0.0;
    }

    public static void main(String[] args) {
        // Valores para lambda y miu por parámetros

        //        Caso 1
        System.out.println("Iniciando simulación 1...");
        FirstSimulator simulation = new FirstSimulator(1, 2);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherVars());

        //        Caso 2
        System.out.println("Iniciando simulación 2...");
        simulation = new FirstSimulator(1, 4);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherVars());

        //        Caso 3
        System.out.println("Iniciando simulación 3...");
        simulation = new FirstSimulator(2, 3);
        simulation.setVerboseReport(false);
        simulation.start();
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherVars());
    }

    void start() {
        init();
        this.setTimeToEnd(1000000);
        this.insert(new EnqueueEvent(0.0));
        this.run();
    }

    String getOtherVars() {
        Collection<LinkedList<Double>> events = this.timeElementHistogram.values();
        Iterator<LinkedList<Double>> it = events.iterator();
        Double totalClientsTimeOn = 0.0;
        Double timeOnClient = 0.0;
        while (it.hasNext()) {
            LinkedList<Double> times = it.next();

            if (times.size() == 1) {
                timeOnClient = this.getTimeToEnd() - times.get(0);
            } else {
                timeOnClient = times.get(1) - times.get(0);
            }
            totalClientsTimeOn += timeOnClient;
        }
        return "Tiempo promedio en sistema  >>>> " + totalClientsTimeOn / events.size() + "\n"
                + "Tiempo promedio en cola  >>>> " + (totalClientsTimeOn / events.size() - (1 / this.getMiu())) + "\n"
                + "Tiempo total con servicio en off  >>>> " + this.timeSystemOffTotal + "\n"
                + "Porcentaje del tiempo que el servidor está ocupado >>>> " + 100 * (this.getTimeToEnd() - this.timeSystemOffTotal) / this.getTimeToEnd() + "\n";

    }
}
