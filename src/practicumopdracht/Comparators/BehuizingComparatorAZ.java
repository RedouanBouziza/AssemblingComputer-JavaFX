package practicumopdracht.Comparators;

import practicumopdracht.Models.Behuizing;

import java.util.Comparator;

public class BehuizingComparatorAZ implements Comparator<Behuizing> {

    @Override
    public int compare(Behuizing behuizingHoogte1, Behuizing behuizingHoogte2) {
        return (behuizingHoogte1.getSoort().compareTo(behuizingHoogte2.getSoort()));
    }
}
