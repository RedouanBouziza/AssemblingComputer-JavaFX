/**
 * @Author: Redouan Bouziza IS205
 * ObjectComponentDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.MainApplication;
import practicumopdracht.Models.Behuizing;
import practicumopdracht.Models.Component;

import java.io.*;

/**
 * Zorgt voor het opslaan en laden van componenten in een binary bestand
 */
public class ObjectComponentDAO extends ComponentDAO {

    static final String COMPONENTEN_OBJECT = "Component.co";

    /**
     * Saved the behuizingen to a binary file
     */
    @Override
    public boolean save() {
        File file = new File(COMPONENTEN_OBJECT);

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeInt(componenten.size());

            for (Component component : componenten) {
                int componentId = MainApplication.getBehuizingDAO().getIdFor(component.getHoortbij());
                objectOutputStream.writeInt(componentId);
                objectOutputStream.writeObject(component);
            }

            return true;
        } catch (FileNotFoundException e) {
            System.err.println(COMPONENTEN_OBJECT + " is niet gevonden!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Loads the behuizingen from a binary file
     */
    @Override
    public boolean load() {
        componenten.clear();
        File file = new File(COMPONENTEN_OBJECT);

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            int aantalComponenten = objectInputStream.readInt();

            for (int i = 0; i < aantalComponenten; i++) {
                Behuizing behuizing = MainApplication.getBehuizingDAO().getById(objectInputStream.readInt());
                Component component = (Component) objectInputStream.readObject();
                component.setHoortbij(behuizing);
                componenten.add(component);
            }

            return true;
        }
        catch (FileNotFoundException e) {
            System.err.println(COMPONENTEN_OBJECT + " is niet gevonden!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}

