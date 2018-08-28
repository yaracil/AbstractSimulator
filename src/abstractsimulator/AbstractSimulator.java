/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractsimulator;

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

    public OrderedSet events;

    public double time;

    double now() {
        return time;
    }

    void doAllEvents() {
        AbstractEvent e;
        while ((e = (AbstractEvent) events.removeFirst()) != null) {
            time = e.time;
            e.execute(this);
        }
    }

    public void insert(AbstractEvent e) {
        events.insert(e);
    }

    public AbstractEvent cancel(AbstractEvent e) {
        throw new java.lang.RuntimeException("Method not implemented");
    }
}
