/**
 * @Author: Redouan Bouziza IS205
 * TextComponentDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.MainApplication;
import practicumopdracht.Models.Behuizing;
import practicumopdracht.Models.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Zorgt voor het opslaan en laden van behuizingen in een text bestand
 */
public class TextComponentDAO extends ComponentDAO {

    static final String COMPONENT = "Component.txt";

    /**
     * Saved the component to a text file
     */
    @Override
    public boolean save() {
        File file = new File(COMPONENT);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(componenten.size());

            for (Component component : componenten){
                int behuizingId = MainApplication.getBehuizingDAO().getIdFor(component.getHoortbij());
                printWriter.println(behuizingId);
                printWriter.println(component.getNaam());
                printWriter.println(component.getDatum());
            }
            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println(COMPONENT + " is niet gevonden!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Loads the component from a text file
     */
    @Override
    public boolean load() {
        componenten.clear();
        File file = new File(COMPONENT);

        try(Scanner scanner = new Scanner(file)) {
            int aantalComponenten = scanner.nextInt();

            for (int i = 0; i < aantalComponenten; i++) {
                Behuizing behuizing = MainApplication.getBehuizingDAO().getById(scanner.nextInt());
                scanner.nextLine();
                String componentNaam = scanner.next();
                scanner.nextLine();
                LocalDate garantieDatum = LocalDate.parse(scanner.next());

                Component component = new Component(behuizing, componentNaam, garantieDatum);
                componenten.add(component);
            }

            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println(COMPONENT + " is niet gevonden!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
}
