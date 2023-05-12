package entities;

public class User {
    int id;
    String name;
    int x;
    int y;
    String CPF;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getEndereco(){
        return new int[] {x,y};
    }
    public void setEndereco(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public User(int id, String name, String CPF, int x, int y) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.x = x;
        this.y = y;
    }
}
