package entities;

public class User {
    private final int id;
    private String name;
    private int x;
    private int y;
    private final String CPF;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

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

//    public void setCPF(String CPF) {
//        this.CPF = CPF;
//    }
    public User(int id, String name, String CPF, int x, int y) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.x = x;
        this.y = y;
        this.role = Role.USER;
    }
    public User(int id, String name, String CPF, int x, int y, Role role) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.x = x;
        this.y = y;
        this.role = role;
    }
}
