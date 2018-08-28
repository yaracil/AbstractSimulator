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

    double time;
    String tag;

    public AbstractEvent(double time) {
        this.time = time;
    }
    
    

    public boolean lessThan(Comparable y) {
        AbstractEvent e = (AbstractEvent) y;  // Will throw an exception if y is not an Event
        return this.time < e.time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public abstract void execute(AbstractSimulator simulator);
}
