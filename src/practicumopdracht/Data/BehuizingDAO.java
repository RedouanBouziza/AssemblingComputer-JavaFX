/**
 * @Author: Redouan Bouziza IS205
 * BehuizingDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;
import java.util.ArrayList;
import java.util.List;

/**
 * Zorgt voor de opslag van behuizingen
 */
public abstract class BehuizingDAO implements DAO<Behuizing> {
    protected ArrayList<Behuizing> behuizingen = new ArrayList<>();

    @Override
    public List<Behuizing> getAll() {
        return behuizingen;
    }

    public Behuizing getById(int id) {
        try {
            return behuizingen.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public int getIdFor(Behuizing object){
        return behuizingen.indexOf(object);
    }

    @Override
    public void addOrUpdate(Behuizing object) {
        if (behuizingen.contains(object)){
            return;
        }

        behuizingen.add(object);
    }

    @Override
    public void remove(Behuizing object) {
        behuizingen.remove(object);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}

