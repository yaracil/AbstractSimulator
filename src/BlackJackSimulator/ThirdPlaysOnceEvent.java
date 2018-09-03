/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJackSimulator;

import FirstSimulator.DequeueEvent;
import abstractsimulator.AbstractEvent;
import abstractsimulator.AbstractSimulator;

/**
 *
 * @author IT
 */
public class ThirdPlaysOnceEvent extends AbstractEvent {

    public ThirdPlaysOnceEvent(double time) {
        super(time);
        this.setTag("Jugador tres juega");
    }

    @Override
    public void execute(AbstractSimulator simulator) {
        boolean[] cards = ((BlackJackSimulator) simulator).getEscenario();
        do {
            int random = ((int) (Math.random() * 52));
            simulator.report += "Carta seleccionada   >>>> " + ((BlackJackSimulator) simulator).getCardDescription(random) + " \n";
            int thirdPlayer = ((BlackJackSimulator) simulator).thirdPlayer;
            if (cards[random]) {
                simulator.report += "Carta seleccionada incorrecta    >>>> Repitiendo..." + "\n";
                continue;
            } else {
                int count = simulator.getSystemCount();
                int dealer = ((BlackJackSimulator) simulator).dealer;
                int newCardValue = ((BlackJackSimulator) simulator).getValue(random);
                int newHand = thirdPlayer + newCardValue;
                if (newHand > 21) {
                    simulator.report += "Jugador 3 lost!!!" + " \n";
                } else if (newHand > dealer) {
                    simulator.setSystemCount(count + 1);
                    simulator.report += "Jugador 3 wins!!!" + " \n";
                } else if (thirdPlayer == 1 || newCardValue == 1) {
                    newHand += 10;
                    if (newHand <= 21 && newHand > dealer) {
                        simulator.setSystemCount(count + 1);
                        simulator.report += "Jugador 3 wins!!!" + " \n";
                    } else {
                        simulator.report += "Jugador 3 lost!!!" + " \n";
                    }
                } else {
                    simulator.report += "Jugador 3 lost!!!" + " \n";
                }
                simulator.report += "Puntos del dealer   >>>> " + ((BlackJackSimulator) simulator).getDealer() + " \n";
                simulator.report += "Puntos del thirdPlayer   >>>> " + newHand + " \n";
                // simulator.report +=((BlackJackSimulator) simulator).printEscenario();
                simulator.insert(new ThirdPlaysOnceEvent(this.getTime() + 1));
                break;
            }
        } while (true);
    }
}
