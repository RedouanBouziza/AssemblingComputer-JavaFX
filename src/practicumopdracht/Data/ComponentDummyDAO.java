package practicumopdracht.Data;

import practicumopdracht.MainApplication;
import practicumopdracht.Models.Component;

import java.time.LocalDate;

public class ComponentDummyDAO extends ComponentDAO {

    @Override
    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        componenten.clear();
        componenten.add(new Component(MainApplication.getBehuizingDAO().behuizingen.get(0), "Videokaart", LocalDate.now()));
        componenten.add(new Component(MainApplication.getBehuizingDAO().behuizingen.get(1), "Cpu", LocalDate.now()));
        componenten.add(new Component(MainApplication.getBehuizingDAO().behuizingen.get(2), "Moederbord", LocalDate.now()));
        componenten.add(new Component(MainApplication.getBehuizingDAO().behuizingen.get(3), "SSD", LocalDate.now()));
        return true;
    }
}
