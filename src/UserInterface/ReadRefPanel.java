package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Model.Referent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ReadRefPanel extends JPanel {

    private JLabel title, searchLabel, boxLabel;
    private JTextField searchBar;
    private JCheckBox byIdBox;
    private CheckBoxListener checkBoxListener;
    private TextListener textListener;
    private JList<String> results;
    private DefaultListModel<String> listModel;

    public ReadRefPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());
        title = new JLabel("Here is the place to view referents");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        this.add(title, BorderLayout.NORTH);

        var wrapper = new JPanel(new GridBagLayout());
        var searchGrid = new JPanel(new GridLayout(2, 2));
        searchLabel = new JLabel("Search for referent by designation");
        searchGrid.add(searchLabel);
        boxLabel = new JLabel("Check this box to search by ID");
        searchGrid.add(boxLabel);
        searchBar = new JTextField();
        searchBar.addActionListener(textListener);
        searchGrid.add(searchBar);
        byIdBox = new JCheckBox("Search id");
        byIdBox.addItemListener(checkBoxListener);
        checkBoxListener = new CheckBoxListener(searchLabel, boxLabel);
        textListener = new TextListener(
            controller,
            listModel,
            searchBar,
            checkBoxListener
        );
        searchGrid.add(byIdBox);

        searchGrid.setPreferredSize(new Dimension(600, 50));
        wrapper.add(searchGrid);

        listModel = new DefaultListModel<>();
        var referents = controller.getAllReferent();
        for (Referent referent : referents) {
            listModel.addElement(
                referent.toString() +
                    " " +
                    controller
                        .getSchoolsByID(referent.getIdSchoolOfThought())
                        .getName()
            );
        }
        results = new JList<>(listModel);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        results.setPreferredSize(new Dimension(600, 100));
        wrapper.add(results);

        this.add(wrapper, BorderLayout.CENTER);
    }
}
