/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJackSimulator;

import abstractsimulator.AbstractEvent;
import abstractsimulator.AbstractSimulator;

/**
 *
 * @author IT
 */
public class DealerPlaysMultipleEvent extends AbstractEvent {

    public DealerPlaysMultipleEvent(double time) {
        super(time);
        this.setTag("Dealer juega múltiples veces");
    }

    @Override
    public void execute(AbstractSimulator simulator) {
        String[] cardsAux = ((BlackJackSimulator) simulator).getDealer().clone();
        boolean[] escenarioAux = ((BlackJackSimulator) simulator).getEscenario().clone();
        String[] newHand;
        boolean wins = false;
        boolean needReset = false;

        do {
            String newCard = ((BlackJackSimulator) simulator).getRandomCard();
            newHand = ((BlackJackSimulator) simulator).setsDealerNewCard(newCard);

            wins = ((BlackJackSimulator) simulator).doesDealerWin(newHand);

            if (wins) {
                simulator.report += "Jugador 3 lost!!!" + " \n";
                break;
            } else if (((BlackJackSimulator) simulator).getCardsBestValue(newHand) <= 16) {
                needReset = true;
                ((BlackJackSimulator) simulator).setDealerPlayer(newHand);
                simulator.report += "Dealer selecciona otra carta..." + " \n";
            } else {
                int winsCount = ((BlackJackSimulator) simulator).getThirdWins();
                simulator.report += "Jugador 3 wins!!!" + " \n";
                ((BlackJackSimulator) simulator).setThirdWins(winsCount + 1);
                break;
            }
        } while (true);
        if (needReset) {
            ((BlackJackSimulator) simulator).setEscenario(escenarioAux);
            ((BlackJackSimulator) simulator).setDealerPlayer(cardsAux);
        }
        simulator.setSystemCount(simulator.getSystemCount() + 1);
        simulator.insert(new DealerPlaysMultipleEvent(this.getTime() + 1));
        simulator.report += "Puntos del dealer   >>>> " + ((BlackJackSimulator) simulator).getCardsBestValue(newHand) + " \n";
        simulator.report += "Puntos del thirdPlayer   >>>> " +  ((BlackJackSimulator) simulator).getCardsBestValue(((BlackJackSimulator) simulator).getThirdPlayer())   + " \n";
        // simulator.report +=((BlackJackSimulator) simulator).printEscenario();

    }
}
