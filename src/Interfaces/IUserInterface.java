package Interfaces;

import Models.Referent;

public interface IUserInterface {
    void displayCreateReferent();
    void displayUpdateReferent();
    void displayUpdateReferent(Referent referent);
    void displayDeleteReferent();
    void displayReferentSearch();

    void displayItemSearch();

    void displayOrderLinesSearch();
    void displayOrdersByCountrySearch();

    void displayHome();
}
