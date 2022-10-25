/**
 * @Author: Redouan Bouziza IS205
 * Master model
 */
package practicumopdracht.Models;

import java.io.Serializable;

public class Behuizing implements Serializable {

    //    Attributen
    private String soort;
    private double hoogte;
    private int serieNummer;
    private boolean behuizingHeeftTemperedGlass;

    /**
     * Constructor for Behuizing
     *
     * @param soort
     * @param hoogte
     * @param serieNummer
     * @param behuizingHeeftTemperedGlass
     */
    public Behuizing(String soort, double hoogte, int serieNummer, boolean behuizingHeeftTemperedGlass) {
        this.soort = soort;
        this.hoogte = hoogte;
        this.serieNummer = serieNummer;
        this.behuizingHeeftTemperedGlass = behuizingHeeftTemperedGlass;
    }

    //   Getters and Setters
    public String getSoort() {
        return soort;
    }

    public double getHoogte() {
        return hoogte;
    }

    public int getSerieNummer() {
        return serieNummer;
    }

    public boolean heeftBehuizingTemperedGlass() {
        return behuizingHeeftTemperedGlass;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public void setHoogte(Double hoogte) {
        this.hoogte = hoogte;
    }

    public void setSerieNummer(int serieNummer) {
        this.serieNummer = serieNummer;
    }

    public void setBehuizingHeeftTemperedGlass(boolean BehuizingHeeftTemperedGlass) {
        this.behuizingHeeftTemperedGlass = BehuizingHeeftTemperedGlass;
    }

    /**
     * To string methode
     *
     * @return toString
     */
    public String toString() {
        return String.format("Merk: %s\nHoogte: %3.2f\nSerienummer: %d\nBehuizing heeft tempered glass: %s",
                soort,
                hoogte,
                serieNummer,
                behuizingHeeftTemperedGlass ? "Ja" : "Nee");
    }
}
