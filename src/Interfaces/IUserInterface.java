package Interfaces;

import Model.Referent;

public interface IUserInterface {
    void displayCreateReferent();
    void displayUpdateReferent();
    void displayUpdateReferent(Referent referent);
    void displayDeleteReferent();
    void displayItemSearch();
    void displayReferentSearch();
    void displayHome();
    void displayOrderLinesSearch();
    void displayOrdersByCountrySearch();
}
