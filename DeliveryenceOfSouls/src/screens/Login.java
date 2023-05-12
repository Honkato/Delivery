package screens;

import components.Button;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Login extends JPanel {
    ArrayList<User> usuarios = new ArrayList<>();
    TextField nome = new TextField();
    TextField cpf = new TextField();


    public void setUsuarios(ArrayList<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<User> getUsuarios() {
        return usuarios;
    }

    public Login(ArrayList<User> usuarios){
        this.usuarios = usuarios;
        nome.setBounds(100,100, 100,50);
        cpf.setBounds(100,200, 100,50);
        add(nome);
        add(cpf);
        setBackground(Color.RED);
        setLayout(null);
        setVisible(true);
//        setSize(500,800);
    }
    public boolean logar(){
        for (User usuario: usuarios){
            if(Objects.equals(usuario.getCPF(), cpf.getText()) && Objects.equals(usuario.getName(), nome.getText())){
                return true;
            }
        }
        return false;
    }


}
