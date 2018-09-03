/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJackSimulator;

import abstractsimulator.AbstractSimulator;

/**
 *
 * @author IT
 */
public class BlackJackSimulator extends AbstractSimulator {

    boolean[] escenario;
    int thirdPlayer;
    int dealer;
    int thirdWins;

    public BlackJackSimulator(double lambda, double miu) {
        super(lambda, miu);
        thirdPlayer = 0;
        dealer = 0;
        thirdWins = 0;
        escenario = new boolean[52];
    }

    void setUpDeck() {
        for (int i = 0; i < 52; i++) {
            escenario[i] = false;
        }
    }

    void start() {
        init();
        this.setTimeToEnd(100);
        this.insert(new ThirdPlaysOnceEvent(0));
        this.run();
    }

    public static void main(String[] args) {
        //        Caso 1
        System.out.println("Iniciando simulación 1: \"El jugador 3 gana\"...");
        BlackJackSimulator simulation = new BlackJackSimulator(1, 1);

        simulation.setUpDeck();
        //Setting first player
        simulation.setPlayerCards("10-E", "Q-C");
        //Setting second player
        simulation.setPlayerCards("J-D", "3-T");
        //Setting third player
        simulation.setThirdPlayer("A-T");
        //Setting dealer
        simulation.setDealerPlayer("5-C", "5-T");

        simulation.setVerboseReport(true);

        simulation.start();

        System.out.print(simulation.GenerateReport());

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

    public void setPlayerCards(int card1, int card2) {
        escenario[card1] = true;
        escenario[card2] = true;
    }

    public void setPlayerCards(String card1, String card2) {

        int card1Index = this.getCardIndex(card1);
        int card2Index = this.getCardIndex(card2);
        setPlayerCards(card1Index, card2Index);
    }

    public void setThirdPlayer(String card1) {
        int card1Index = this.getCardIndex(card1);
        // int card2Index = this.getValue(card2);
        escenario[card1Index] = true;
        thirdPlayer = this.getValue(card1);

    }

    public void setDealerPlayer(String card1, String card2) {
        int card1Index = this.getCardIndex(card1);
        int card2Index = this.getCardIndex(card2);
        this.setPlayerCards(card1Index, card2Index);
        dealer = this.getValue(card1) + this.getValue(card2);
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

    public int getDealer() {
        return dealer;
    }

    public int getThirdPlayer() {
        return thirdPlayer;
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

}
