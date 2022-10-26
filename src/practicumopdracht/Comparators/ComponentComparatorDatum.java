/**
 * @Author: Redouan Bouziza IS205
 * Component Comparator Datum
 */
package practicumopdracht.Comparators;

import practicumopdracht.Models.Component;

import java.util.Comparator;

public class ComponentComparatorDatum implements Comparator<Component> {

    /**
     * Compare method
     * @return Compared datum value
     */
    @Override
    public int compare(Component component1, Component component2) {
        return component1.getDatum().compareTo(component2.getDatum());

    }
}

