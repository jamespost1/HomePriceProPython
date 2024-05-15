import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private final JLabel budgetLabel;
    private final JLabel bedLabel;
    private final JLabel bathLabel;
    private final JLabel zipLabel;
    private final JLabel acreLabel;
    private final JTextField budgetField;
    private final JTextField bedField;
    private final JTextField bathField;
    private final JTextField zipField;
    private final JTextField acreField;
    private final JButton submitButton;

    public UI(){
        setTitle("Please Select the following: ");
        setSize(800,250);
        JPanel panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,5));

        //Set of questions that the user is going to see in order for them to start the process
        budgetLabel = new JLabel("Budget Size: ");
        budgetField = new JTextField(5);
        panel.add(budgetLabel);
        panel.add(budgetField);

        bedLabel = new JLabel("Number of Beds: ");
        bedField = new JTextField(5);
        panel.add(bedLabel);
        panel.add(bedField);

        bathLabel = new JLabel("Number of Baths: ");
        bathField = new JTextField(5);
        panel.add(bathLabel);
        panel.add(bathField);

        zipLabel = new JLabel("Zipcode: ");
        zipField = new JTextField(5);
        panel.add(zipLabel);
        panel.add(zipField);

        acreLabel = new JLabel("Acre Size: ");
        acreField = new JTextField(5);
        panel.add(acreLabel);
        panel.add(acreField);

        submitButton = new JButton("Submit");
        
        submitButton.addActionListener((ActionEvent e) -> {
            try {
                double budget = Double.parseDouble(budgetField.getText());
                double beds = Double.parseDouble(bedField.getText());
                double baths = Double.parseDouble(bathField.getText());
                int zip = Integer.parseInt(zipField.getText());
                double acre = Double.parseDouble(acreField.getText());
                double[] sample = {budget, beds, baths, acre, zip};
                RandomForest forest = new RandomForest(100, 7);
                int prediction = forest.predict(sample);
                //Suggestion sug = new Suggestion("/realtor-data.zip.csv/", zip, sample);
                if(prediction == 1){
                    JOptionPane.showMessageDialog(null,
                        "Profitable");
                } else {
                JOptionPane.showMessageDialog(null,
                        "Not Profitable");
                }
            }
            catch (NumberFormatException enter){
                JOptionPane.showMessageDialog(null, "Please enter correct values.");
            }
        });

        add(panel, BorderLayout.NORTH);
        add(submitButton, BorderLayout.SOUTH);
    }
}
