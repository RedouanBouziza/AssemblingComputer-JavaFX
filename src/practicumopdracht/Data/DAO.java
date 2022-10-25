package practicumopdracht.Data;

import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    void addOrUpdate(T object);
    void remove(T object);
    boolean save();
    boolean load();
}
