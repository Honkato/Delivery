package components;

import javax.swing.*;

public class SuccessPopUp extends JCheckBox {
    public SuccessPopUp(){
        String message = "Success";
        JOptionPane.showMessageDialog(new JFrame(), message, "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
    public SuccessPopUp(String title, String message){
        JOptionPane.showMessageDialog(new JFrame(), message, title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
