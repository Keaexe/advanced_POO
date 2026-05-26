package UserInterface;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;

public class CheckBoxListener implements ItemListener {

    private boolean isSelected;
    private JLabel searchLabel, boxLabel;

    public CheckBoxListener(JLabel searchLabel, JLabel boxLabel) {
        this.isSelected = false;
        this.searchLabel = searchLabel;
        this.boxLabel = boxLabel;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            searchLabel.setText("Search for referent by id");
            boxLabel.setText("Uncheck this box to search by designation");
            isSelected = true;
        } else {
            searchLabel.setText("Search for referent by designation");
            boxLabel.setText("Check this box to search by ID");
            isSelected = false;
        }
    }

    public boolean isSelected() {
        return isSelected;
    }
}
