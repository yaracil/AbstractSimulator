/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractsimulator;

import java.util.Random;

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
    double lambda;
    double miu;
    int systemCount;

    public AbstractSimulator(long lambda, long miu) {
        this.lambda = lambda;
        this.miu = miu;
        systemCount = 0;
        ran = new Random();
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

    public String getReport() {
        return report;
    }

    public void run() {
        AbstractEvent e;
        while ((e = (AbstractEvent) events.removeFirst()) != null) {
            if (e.getTime() >= timeToEnd) {
                break;
            } else {
                time = e.getTime();
                e.execute(this);
                report += "Ha ocurrido el evento " + e.getTag() + "  Tiempo: " + e.getTime() + '\n';
            }
        }
        report += "La simulación a finalizado!    Tiempo del último evento: " + time + '\n';
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
}
