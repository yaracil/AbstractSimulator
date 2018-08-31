/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractsimulator;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Yoe
 */
abstract class OrderedSet {

    public abstract void insert(Comparable x);

    public abstract Comparable removeFirst();

    public abstract int size();

    public abstract Comparable remove(Comparable x);
}

public class AbstractSimulator {

    Random ran;
    OrderedSet events;
    double time;
    double timeToEnd;
    String report;
    boolean verboseReport;
    double lambda;
    double miu;
    int systemCount;
    Map<String, Integer> eventsHistogram;

    public AbstractSimulator(double lambda, double miu) {
        this.lambda = lambda;
        this.miu = miu;
        this.verboseReport = false;
        systemCount = 0;
        ran = new Random();
        eventsHistogram = new TreeMap<String, Integer>();
    }

    public void init() {
        events = new ListQueue();
        time = 0.0;
        timeToEnd = 0.0;
        report = "Abstract Simulator 1.0 Version" + '\n';
    }

    double now() {
        return time;
    }

    public String GenerateReport() {

        String histogram = "";
        Set<String> keys = eventsHistogram.keySet();

        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            histogram += "Evento: " + key + " se procesó " + eventsHistogram.get(key) + " veces." + '\n';
        }
        if (verboseReport) {
            histogram = this.report + histogram;
        }
        return histogram;
    }

    public void run() {
        AbstractEvent e;
        while ((e = (AbstractEvent) events.removeFirst()) != null) {
            if (e.getTime() >= timeToEnd) {
                break;
            } else {
                time = e.getTime();
                // Events Histogram Log
                int count = 0;
                if (eventsHistogram.containsKey(e.getTag())) {
                    count = eventsHistogram.get(e.getTag());
                }
                eventsHistogram.put(e.getTag(), count + 1);
                //Executing
                e.execute(this);
                if (verboseReport) {
                    report += "Ha ocurrido el evento >>>>    " + e.getTag() + "  >>>> Tiempo: " + e.getTime() + "   >>>> Elementos en el sistema: " + this.getSystemCount() + '\n';
                }
            }
        }
        if (verboseReport) {
            report += "\n" + "La simulación a finalizado!    >>>>    Tiempo del último evento: " + time + '\n';
        }
    }

    public void insert(AbstractEvent e) {
        events.insert(e);
    }

    public AbstractEvent cancel(AbstractEvent e) {
        throw new java.lang.RuntimeException("Method not implemented");
    }

    public void setTimeToEnd(double timeToEnd) {
        this.timeToEnd = timeToEnd;
    }

    public double getTimeToEnd() {
        return timeToEnd;
    }

    public int getSystemCount() {
        return systemCount;
    }

    public void setSystemCount(int systemCount) {
        this.systemCount = systemCount;
    }

    public Double getRandomWithPoissonDistribution(Double media) {

        return (Double) (-(1 / media) * Math.log(1 - ran.nextFloat()));
    }

    public Double getLambda() {
        return lambda;
    }

    public Double getMiu() {
        return miu;
    }

    public void setVerboseReport(boolean verboseReport) {
        this.verboseReport = verboseReport;
    }

    public Map<String, Integer> getEventsHistogram() {
        return eventsHistogram;
    }
}
