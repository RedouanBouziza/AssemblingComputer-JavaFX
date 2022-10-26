/**
 * @Author: Redouan Bouziza IS205
 * Detail model
 */
package practicumopdracht.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class Component implements Serializable {

    private transient Behuizing hoortbij;
    private String naam;
    private LocalDate datum;

    /**
     * Constructor for Behuizing
     * @param hoortbij
     * @param naam
     * @param datum
     */
    public Component(Behuizing hoortbij, String naam, LocalDate datum) {
        this.hoortbij = hoortbij;
        this.naam = naam;
        this.datum = datum;
    }

    public Behuizing getHoortbij() {
        return hoortbij;
    }

    public String getNaam() {
        return naam;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setHoortbij(Behuizing hoortbij) {
        this.hoortbij = hoortbij;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     * To string methode
     * @return toString
     */
    public String toString() {
        return String.format("Naam: %s\nDatum: %s",
                naam,
                datum);
    }





}
