/**
 * @Author: Redouan Bouziza IS205
 * BinaryBehuizingDAO Class
 */
package practicumopdracht.Data;

import practicumopdracht.Models.Behuizing;

import java.io.*;

/**
 * Zorgt voor het opslaan en laden van behuizingen in een binary bestand
 */
public class BinaryBehuizingDAO extends BehuizingDAO {

//    static final String BINARY_BEHUIZING = "Behuizing.bn";

    /**
     * Saved the behuizingen to a binary file
     */
    @Override
    public boolean save() {
        File file = new File("Behuizing.bin");
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)
        ) {
            dataOutputStream.writeInt(behuizingen.size());

            for (Behuizing behuizing : behuizingen) {
                dataOutputStream.writeUTF(behuizing.getSoort());
                dataOutputStream.writeDouble(behuizing.getHoogte());
                dataOutputStream.writeInt(behuizing.getSerieNummer());
                dataOutputStream.writeBoolean(behuizing.heeftBehuizingTemperedGlass());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * Loads the behuizingen from a binary file
     */
    @Override
    public boolean load() {
        behuizingen.clear();
        File file = new File("Behuizing.bin");

        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream)
        ) {
            int aantalBehuizingen = dataInputStream.readInt();

            for (int i = 0; i < aantalBehuizingen; i++) {
                String soort = dataInputStream.readUTF();
                double hoogte = dataInputStream.readDouble();
                int serienummer = dataInputStream.readInt();
                boolean heeftGlass = dataInputStream.readBoolean();

                Behuizing behuizing = new Behuizing(soort, hoogte, serienummer, heeftGlass);
                behuizingen.add(behuizing);
            }

            return true;
        }
        catch (FileNotFoundException e) {
            System.err.println("bestand is niet gevonden!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

