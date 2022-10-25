/**
 * @Author: Redouan Bouziza IS205
 * ComponentDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;
import practicumopdracht.Models.Component;
import java.util.ArrayList;
import java.util.List;

public abstract class ComponentDAO implements DAO<Component> {
    protected ArrayList<Component> componenten = new ArrayList<>();

    public List<Component> getAllFor(Behuizing object){
        List<Component> juisteComponent = new ArrayList<>();

        componenten.forEach(component -> {
            if (component.getHoortbij() == object){
                juisteComponent.add(component);
            }
        });

        return juisteComponent;
    }

    @Override
    public List<Component> getAll() {
        return componenten;
    }

    @Override
    public void addOrUpdate(Component object) {
        if (componenten.contains(object)){
            return;
        }
        componenten.add(object);
    }

    @Override
    public void remove(Component object) {
        componenten.remove(object);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}

