package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;

public class BehuizingDummyDAO extends BehuizingDAO {

    @Override
    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        behuizingen.clear();
        behuizingen.add(new Behuizing("be quiet!", 46.3,4423, true));
        behuizingen.add(new Behuizing("Cooler Master", 29.2,6634, true));
        behuizingen.add(new Behuizing("Corsair", 46.6,4467, false));
        behuizingen.add(new Behuizing("Fractal Design", 53,2145, false));
        return true;
    }
}

