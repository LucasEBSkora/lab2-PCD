package eu.telecomnancy.lab2e2455u.view;

import eu.telecomnancy.lab2e2455u.Main;
import eu.telecomnancy.lab2e2455u.model.Carnet;
import eu.telecomnancy.lab2e2455u.model.CarnetEntry;

public class EntryScreenView extends CarnetDeVoyageView {

    protected final CarnetEntry entry;

    protected final CarnetEntry tempEntry;

    public EntryScreenView(Main main, Carnet carnet, CarnetEntry entry, CarnetEntry tempEntry) {
        super(main, carnet);
        this.entry = entry;
        this.tempEntry = tempEntry;
    }
}
