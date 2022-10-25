package practicumopdracht.Comparators;

import practicumopdracht.Models.Component;

import java.util.Comparator;

public class ComponentComparatorAZ implements Comparator<Component> {

    @Override
    public int compare(Component component1, Component component2) {
        return (component1.getNaam().compareTo(component2.getNaam()));
    }

}


