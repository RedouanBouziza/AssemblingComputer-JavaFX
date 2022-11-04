/**
 * @Author: Redouan Bouziza IS205
 * TextBehuizingDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Zorgt voor het opslaan en laden van behuizingen in een text bestand
 */
public class TextBehuizingDAO extends BehuizingDAO {

    static final String BEHUIZING = "Behuizingen.txt";

    /**
     * Saved the behuizingen to a text file
     */
    @Override
    public boolean save() {
        File file = new File(BEHUIZING);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(behuizingen.size());

            for (Behuizing behuizing : behuizingen){
                printWriter.println(behuizing.getSoort());
                printWriter.println(behuizing.getHoogte());
                printWriter.println(behuizing.getSerieNummer());
                printWriter.println(behuizing.heeftBehuizingTemperedGlass());
            }

            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println(BEHUIZING + " is niet gevonden!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Loads the behuizingen from a text file
     */
    @Override
    public boolean load() {
        behuizingen.clear();
        File file = new File(BEHUIZING);

        try(Scanner scanner = new Scanner(file)) {
            int aantalBehuizingen = scanner.nextInt();

            for(int i = 0; i < aantalBehuizingen; i++) {
                String soort = scanner.next();
                double hoogte = Double.parseDouble(scanner.next());
                int serieNummer = scanner.nextInt();
                boolean temperedGlass = scanner.nextBoolean();

                Behuizing behuizing = new Behuizing(soort, hoogte, serieNummer, temperedGlass);
                behuizingen.add(behuizing);
            }

            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println(BEHUIZING + " is niet gevonden!");
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
