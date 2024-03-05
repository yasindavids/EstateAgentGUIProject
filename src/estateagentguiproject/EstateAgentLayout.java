package estateagentguiproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Setting the GUI elements
public class EstateAgentLayout extends JFrame {

    // Public Declarations
    public static JTextField nameField;
    public static JTextField priceField;
    public static JTextField commissionField;
    public static JTextArea reportField;

    // Array for agency locations
    static String[] locations = {"Cape Town", "Durban", "Pretoria"};
    // Creates a combobox
    static JComboBox<String> locationChoice = new JComboBox<>(locations);

    //constants for height and width of frame
    final int WIDTH = 400;
    final int HEIGHT = 450;

    public EstateAgentLayout() {
        // Creates frame
        JFrame frame = new JFrame();

        // Calls menu bar method
        createMenuBar(frame);
        //Set title of frame
        frame.setTitle("Estate Agent Report");

        //Exit program when you click close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set size of frame
        frame.setSize(WIDTH, HEIGHT);

        // Creating panel
        JPanel panel = new JPanel();

        // Setting the layout manager to panel
        panel.setLayout(null);

        //Setting labels       
        JLabel label1 = new JLabel("AGENT LOCATION: ");
        JLabel label2 = new JLabel("ESTATE AGENT NAME: ");
        JLabel label3 = new JLabel("PROPERTY PRICE: ");
        JLabel label4 = new JLabel("COMMISSION PERCENTAGE: ");
        JLabel label5 = new JLabel("ESTATE AGENT REPORT: ");

        //Setting labels' location
        label1.setBounds(10, 10, 500, 50);
        label2.setBounds(10, 50, 500, 50);
        label3.setBounds(10, 90, 500, 50);
        label4.setBounds(10, 130, 500, 50);
        label5.setBounds(10, 170, 500, 50);

        // Adding labels to panel
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);

        // Sets combobox location
        locationChoice.setBounds(200, 21, 200, 30);

        //Adds combobox to panel
        panel.add(locationChoice);

        // Creating textfields
        nameField = new JTextField(50);
        priceField = new JTextField(20);
        commissionField = new JTextField(20);
        reportField = new JTextArea();

        // Setting locations for textfields
        nameField.setBounds(210, 58, 170, 30);
        priceField.setBounds(210, 98, 170, 30);
        commissionField.setBounds(210, 138, 170, 30);
        reportField.setBounds(10, 210, 370, 200);

        //Adding textfields to panel
        panel.add(nameField);
        panel.add(priceField);
        panel.add(commissionField);
        panel.add(reportField);

        //-----------------------------
        // Adding panel to frame
        frame.add(panel);
        //Centers the frame
        frame.setLocationRelativeTo(null);

        //Make frame visible
        frame.setVisible(true);

    }

    public static void report(JFrame frame) {
        
        //Call EstateAgent method
        EstateAgent agent = new EstateAgent();
        
        //Some String declarations
        String selectedOption = (String) locationChoice.getSelectedItem();
        String enteredName = nameField.getText();
        String enteredPrice = priceField.getText();
        String enteredCommission = commissionField.getText();
        
        //turning string text inputs into doubles
        double price = Double.parseDouble(enteredPrice);
        double comm = Double.parseDouble(enteredCommission);
        
        //Calling calculatecommission method
        double finalCommission = agent.CalculateCommission(price, comm);
        
        //Printing/setting report text
        reportField.setText("AGENT LOCATION: " + selectedOption + "\n"
                + "AGENT NAME: " + enteredName + "\n"
                + "PROPERTY PRICE: " + price + "\n"
                + "COMMISSION PERCENTAGE: " + comm + "%\n"
                + "CALCULATED COMMISSION: " + finalCommission);

    }
    
    public static void saveToFile(String reportField){
        // Create filechooser
        JFileChooser file = new JFileChooser();
        int result = file.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = file.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(reportField);
                JOptionPane.showMessageDialog(null, "File saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void createMenuBar(JFrame frame) {

        //Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create File menu
        JMenu fileMenu = new JMenu("File");

        //Add exit item to file menu
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        // Add action listeners to menu items
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Create Tools Menu
        JMenu toolsMenu = new JMenu("Tools");

        // Add items to tools menu
        JMenuItem tool1Item = new JMenuItem("Process Report");
        JMenuItem tool2Item = new JMenuItem("Clear");
        JMenuItem tool3Item = new JMenuItem("Save Report");
        toolsMenu.add(tool1Item);
        toolsMenu.add(tool2Item);
        toolsMenu.add(tool3Item);

        //Calls report method if pressed
        tool1Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report(frame);
            }
        });
        
        //Calls save method if pressed
        tool3Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile(reportField.getText());
            }
        });

        //Clears all textfields/areas when pressed
        tool2Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                priceField.setText("");
                commissionField.setText("");
                reportField.setText("");
            }
        });
        
        

        // Adding menues to main frame
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        frame.setJMenuBar(menuBar);
    }

}
