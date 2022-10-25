package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextBehuizingDAO extends BehuizingDAO {

    static final String BEHUIZING = "Behuizingen.txt";

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

    @Override
    public boolean load() {
        behuizingen.clear();
        File file = new File(BEHUIZING);

        try(Scanner scanner = new Scanner(file)) {
            int aantalBehuizingen = scanner.nextInt();

            for(int i = 0; i < aantalBehuizingen; i++) {
                String soort = scanner.next();
                scanner.nextLine();
                double hoogte = scanner.nextDouble();
                scanner.nextLine();
                int serieNummer = scanner.nextInt();
                scanner.nextLine();
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
