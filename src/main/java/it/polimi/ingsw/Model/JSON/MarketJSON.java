package it.polimi.ingsw.Model.JSON;

import it.polimi.ingsw.Model.Enums.Marble;

/**This class is use to create the file "Persistence.json"*/
public class MarketJSON {
    Marble one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, extra;

    public MarketJSON(Marble one, Marble two, Marble three, Marble four, Marble five, Marble six, Marble seven, Marble eight, Marble nine, Marble ten, Marble eleven, Marble twelve, Marble extra) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.ten = ten;
        this.eleven = eleven;
        this.twelve = twelve;
        this.extra = extra;
    }
}
