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
interface Comparable {

    public boolean lessThan(Comparable y);
}

public abstract class AbstractEvent implements Comparable {

    public double time;

    public boolean lessThan(Comparable y) {
        AbstractEvent e = (AbstractEvent) y;  // Will throw an exception if y is not an Event
        return this.time < e.time;
    }

    public abstract void execute(AbstractSimulator simulator);
}
