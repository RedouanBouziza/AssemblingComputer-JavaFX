/**
 * @Author: Redouan Bouziza IS205
 * Behuizing Comparator Serienummer
 */
package practicumopdracht.Comparators;

import practicumopdracht.Models.Behuizing;

import java.util.Comparator;

public class BehuizingComparatoSerienummer implements Comparator<Behuizing> {

    /**
     * Compare method
     * @return Compared integer value
     */
    @Override
    public int compare(Behuizing behuizingSoort1, Behuizing behuizingSoort2) {
        return Integer.compare(behuizingSoort1.getSerieNummer(), behuizingSoort2.getSerieNummer());
    }
}
