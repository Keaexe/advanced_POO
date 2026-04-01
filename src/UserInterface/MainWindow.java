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
    private JMenuItem exit, referentMod, referentSearch;
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
        mainContainer = this.getContentPane();
        monkPanel = new MonkPanel();
        mainContainer.add(monkPanel);
        
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

        referentMod = new JMenuItem("Exit");
        referentMod.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        ModifyListener modifyListener = new ModifyListener();
        referentMod.addActionListener(modifyListener);
        systemMenu.add(referentMod);

        referentSearch = new JMenuItem("Exit");
        referentSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
        SearchListener SearchListener = new SearchListener();
        referentSearch.addActionListener(SearchListener);
        systemMenu.add(referentSearch);

        setVisible(true);
    }

    @Override
    public void displaySearch() {
        // to do
    }

    @Override
    public void displayModification() {
        // to do
    }
}
