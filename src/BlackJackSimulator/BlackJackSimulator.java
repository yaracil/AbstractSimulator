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
public class BlackJackSimulator extends AbstractSimulator {

    boolean[] escenario;
    String[] thirdPlayer = {"", "", "", ""};
    String[] dealer = {"", "", "", ""};
    int thirdWins;

    public BlackJackSimulator(double lambda, double miu) {
        super(lambda, miu);
        escenario = new boolean[52];
        thirdWins = 0;
    }

    void setUpDeck() {
        for (int i = 0; i < 52; i++) {
            escenario[i] = false;
        }
    }

    void start(AbstractEvent eventoInicial) {
        init();
        this.setTimeToEnd(100);
        this.insert(eventoInicial);
        this.run();
    }

    public static void main(String[] args) {
        //        Caso 1
        System.out.println("Iniciando simulación 1: \"El jugador 3 juega\"...");
        BlackJackSimulator simulation = new BlackJackSimulator(1, 1);
        simulation.setUpDeck();
        //Setting first player
        simulation.setPlayerCards(new String[]{"10-E", "Q-C"});
        //Setting second player
        simulation.setPlayerCards(new String[]{"J-D", "3-T"});
        //Setting third player
        simulation.setThirdPlayer(new String[]{"A-T"});
        //Setting dealer
        simulation.setDealerPlayer(new String[]{"K-C", "5-T"});
        simulation.setVerboseReport(false);
        simulation.start(new ThirdPlaysOnceEvent(0));
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherReport());

        //      Caso 2
        System.out.println("Iniciando simulación 2: \"El dealer juega una carta más\"...");
        simulation = new BlackJackSimulator(1, 1);
        simulation.setUpDeck();
        //Setting first player
        simulation.setPlayerCards(new String[]{"10-E", "Q-C"});
        //Setting second player
        simulation.setPlayerCards(new String[]{"J-D", "3-T"});
        //Setting third player
        simulation.setThirdPlayer(new String[]{"10-T", "K-D"});
        //Setting dealer
        simulation.setDealerPlayer(new String[]{"K-C", "6-T"});
        simulation.setVerboseReport(false);
        simulation.start(new DealerPlaysOnceEvent(0));
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherReport());

        //      Caso 3
        System.out.println("Iniciando simulación 3: \"Dealer puede jugar múltiples veces\"...");
        simulation = new BlackJackSimulator(1, 1);
        simulation.setUpDeck();
        //Setting first player
        simulation.setPlayerCards(new String[]{"10-E", "Q-C"});
        //Setting second player
        simulation.setPlayerCards(new String[]{"J-D", "3-T"});
        //Setting third player
        simulation.setThirdPlayer(new String[]{"10-T", "9-D"});
        //Setting dealer
        simulation.setDealerPlayer(new String[]{"K-C", "4-T"});
        simulation.setVerboseReport(true);
        simulation.start(new DealerPlaysMultipleEvent(0));
        System.out.print(simulation.GenerateReport());
        System.out.println(simulation.getOtherReport());

    }

    public String getCardDescription(int card) {
        int denominationIndex = (int) (card) / 13;
        char[] denominations = {'T', 'D', 'E', 'C'};
        int numberIndex;
        if (card < 13) {
            numberIndex = card;
        } else {
            numberIndex = (int) (card) % 13;
        }
        String[] number = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return number[numberIndex] + "-" + denominations[denominationIndex];
    }

    public int getCardIndex(String card) {
        String[] numberes = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] denominations = {"T", "D", "E", "C"};

        String[] cardInfo = card.split("-");
        String number = cardInfo[0];
        String denomination = cardInfo[1];
        for (int i = 0; i < numberes.length; i++) {
            if (number.equals(numberes[i])) {
                for (int j = 0; j < denominations.length; j++) {
                    if (denomination.equals(denominations[j])) {
                        return (j * 13) + i;
                    }
                }
            }
        }
        return -1;
    }

    public boolean[] getEscenario() {
        return escenario;
    }

    public void setPlayerCards(String[] cards) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != "") {
                int cardIndex = this.getCardIndex(cards[i]);
                escenario[cardIndex] = true;
            }
        }
    }

    public void setThirdPlayer(String[] cards) {
        this.setPlayerCards(cards);
        thirdPlayer = cards.clone();
        System.out.println(thirdPlayer.length);
        System.out.println("No. "+this.getSystemCount());
    }

    public void setDealerPlayer(String[] cards) {
        this.setPlayerCards(cards);
        dealer = cards.clone();
    }

    public int getCardsBestValue(String[] cards) {
        int total = 0, cardValueAux = 0;
        boolean thereIsAnA = false;
        for (int i = 0; i < cards.length; i++) {
            cardValueAux = this.getValue(cards[i]);
            if (cardValueAux == 1) {
                thereIsAnA = true;
            }
            total += cardValueAux;
        }
        if (thereIsAnA && ((total + 10) <= 21)) {
            total += 10;
        }
        return total;
    }

    public String[] setsDealerNewCard(String newCard) {
        String[] cards = new String[(dealer.length + 1)];
        for (int i = 0; i < dealer.length; i++) {
            cards[i] = dealer[i];
        }
        cards[dealer.length] = newCard;
        return cards;
    }

    public String[] setsThirdPlayerNewCard(String newCard) {
        String[] cards = new String[(thirdPlayer.length + 1)];
        for (int i = 0; i < thirdPlayer.length; i++) {
            cards[i] = thirdPlayer[i];
        }
        cards[thirdPlayer.length] = newCard;
        return cards;
    }

    public String getRandomCard() {
        String newCard = "";

        do {
            int random = ((int) (Math.random() * 52));
            newCard = this.getCardDescription(random);
            report += "Carta seleccionada   >>>> " + newCard + "  ";
            if (escenario[random]) {
                report += "Carta seleccionada incorrecta    >>>> Repitiendo..." + "\n";
                continue;
            } else {
                break;
            }
        } while (true);
        return newCard;
    }

    public boolean doesThirdPlayerWin(String[] thirdPlayerCards) {

        int thirdPlayerValue = this.getCardsBestValue(thirdPlayerCards);
        int dealer = this.getCardsBestValue(this.getDealer());

        if (thirdPlayerValue > 21) {
            return false;
        } else if (thirdPlayerValue > dealer) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doesDealerWin(String[] dealerCards) {

        int thirdPlayerValue = this.getCardsBestValue(this.thirdPlayer);
        int dealer = this.getCardsBestValue(dealerCards);

        if (dealer > 21) {
            return false;
        } else if (dealer >= thirdPlayerValue) {
            return true;
        } else {
            return false;
        }
    }

    public int getValue(String card) {
        //52 cartas //
        //C --Corazon   //T --Trevol    //E --Espada     //B --Basto
        if (card.contains("A")) {
            return 1;
        } else if (card.contains("2")) {
            return 2;
        } else if (card.contains("3")) {
            return 3;
        } else if (card.contains("4")) {
            return 4;
        } else if (card.contains("5")) {
            return 5;
        } else if (card.contains("6")) {
            return 6;
        } else if (card.contains("7")) {
            return 7;
        } else if (card.contains("8")) {
            return 8;
        } else if (card.contains("9")) {
            return 9;
        } else if (card.contains("10")) {
            return 10;
        } else if (card.contains("J")) {
            return 10;
        } else if (card.contains("Q")) {
            return 10;
        } else if (card.contains("K")) {
            return 10;
        } else {
            return 0;
        }
    }

    public int getValue(int card) {
        return this.getValue(this.getCardDescription(card));
    }

    public String[] getDealer() {
        return dealer;
    }

    public String[] getThirdPlayer() {
        return thirdPlayer;
    }

    public int getThirdWins() {
        return thirdWins;
    }

    public void setThirdWins(int thirdWins) {
        this.thirdWins = thirdWins;
    }

    public void setEscenario(boolean[] escenario) {
        this.escenario = escenario;
    }

    public String printEscenario() {
        // String numb = "";
        String descrip = "";
        for (int i = 0; i < escenario.length; i++) {
            //    numb += "<>" + i;
            if (escenario[i]) {
                descrip += "<>" + this.getCardDescription(i);
            } else {
                descrip += "<>" + "X";
            }
        }
        return descrip + "\n";
    }

    public String getOtherReport() {
        String ret = "";
        double probabilidad = this.getThirdWins() / (1.0 * this.getSystemCount());
        ret += "El jugador 3 ganó " + this.getThirdWins() + " veces \n";
        ret += "La probabilidad de que gane el jugador 3 es de  >>>> " + probabilidad;
        return ret;
    }

}
