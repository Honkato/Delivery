package components;

import javax.swing.*;

public class ErrorPopUp extends JCheckBox {
    public ErrorPopUp(){
        String message = "\"The Comedy of Errors\"\n"
                + "is considered by many scholars to be\n"
                + "the first play Shakespeare wrote";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }
    public ErrorPopUp(String title, String message){
        JOptionPane.showMessageDialog(new JFrame(), message, title,
                JOptionPane.ERROR_MESSAGE);
    }
}
