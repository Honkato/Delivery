package screens;

import entities.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Login extends JPanel {
    ArrayList<User> usuarios = new ArrayList<>();
    User loggedUser;
    TextField nome = new TextField();
    TextField cpf = new TextField();
    JLabel nomeLabel = new JLabel("nome");
    JLabel cpfLabel = new JLabel("cpf");

    public User getLoggedUser(){
        return loggedUser;
    }
    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public Login(ArrayList<User> usuarios, Color color){
        this.usuarios = usuarios;

        nomeLabel.setForeground(Color.ORANGE);
        cpfLabel.setForeground(Color.ORANGE);
        nomeLabel.setBounds(100,80,40,20);
        cpfLabel.setBounds(100,180,40,20);
        nome.setBounds(100,100, 300,22);
        cpf.setBounds(100,200, 300,22);
        add(nomeLabel);
        add(cpfLabel);
        add(nome);
        add(cpf);
        setBackground(color);
        setLayout(null);
        setVisible(true);
//        setSize(500,800);
    }
    public boolean logar(){
        for (User usuario: usuarios){
            if(Objects.equals(usuario.getCPF(), cpf.getText()) && Objects.equals(usuario.getName(), nome.getText())){
                this.loggedUser = usuario;
                return true;
            }
        }
        return false;
    }


}
