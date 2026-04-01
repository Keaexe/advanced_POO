package UserInterface;

import Interfaces.IUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements IUserInterface {
    private JMenuBar menuBar;
    private JMenu systemMenu, modifications, search;
    private JMenuItem exit, referentDel, referentUp, referentCr, referentSearch, itemSearch;
    private Container mainContainer;
    private MonkPanel monkPanel;

    public MainWindow(){
        super("Advanced_POO");
        setBounds(150, 150, 600, 1500);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        systemMenu = new JMenu("System");
        systemMenu.setMnemonic('S');
        menuBar.add(systemMenu);
        modifications = new JMenu("Modify");
        modifications.setMnemonic('M');
        modifications.add(modifications);
        search = new JMenu("Search");
        search.setMnemonic('F');
        menuBar.add(search);

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        ExitListener exitListener = new ExitListener();
        exit.addActionListener(exitListener);
        systemMenu.add(exit);

        referentCr = new JMenuItem("Encode");
        referentCr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        CreateListener createListener = new CreateListener();
        referentCr.addActionListener(createListener);
        modifications.add(referentCr);
        modifications.addSeparator();
        referentUp = new JMenuItem("Update");
        referentUp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        UpdateListener updateListener = new UpdateListener();
        referentUp.addActionListener(updateListener);
        modifications.add(referentUp);
        modifications.addSeparator();
        referentDel = new JMenuItem("Delete");
        referentDel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        DeleteListener DelifyListener = new DeleteListener();
        referentDel.addActionListener(DelifyListener);
        modifications.add(referentDel);

        referentSearch = new JMenuItem("Referent");
        referentSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        ReferentSearchListener referentSearchListener = new ReferentSearchListener();
        referentSearch.addActionListener(referentSearchListener);
        search.add(referentSearch);
        itemSearch = new JMenuItem("Item");
        itemSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        ItemSearchListener itemSearchListener = new ItemSearchListener();
        itemSearch.addActionListener(itemSearchListener);
        search.add(itemSearch);

        mainContainer = this.getContentPane();
        monkPanel = new MonkPanel();
        mainContainer.add(monkPanel);
        setVisible(true);
    }

    @Override
    public void displaySearch() {

    }

    @Override
    public void displayCreate() {

    }

    @Override
    public void displayUpdate() {

    }

    @Override
    public void displayDelete() {

    }

    @Override
    public void displayItemSearch() {

    }

    @Override
    public void displayReferentSearch() {

    }
}
