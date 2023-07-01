package configs;

import entities.Role;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BasicConfigs {
    String CONFIG_RESTS = "configsRests.txt";
//    BACKUP:
//    Firelink Shrimp,0,0-Shrimp Burger,5.99F;Ashes of Lords of Cinders,29.99F;Mainden's Eyeball Cereal,15.99F;
//    Solaire Soup,10,0-Estus Soup,20.99F;Solar Ray,23.99F;Praise The Sunday,30.99F;
//    John Gourmet,60,70-Umbilical Cord Noodles,99.99F;Blood Beast Soup,69.99F;Celestial Being's Part,105.99F;
//    Dadora,50,50-Esfirra Musica Popular Brasileira,11.99F;O melhor brownie do mundo,32.99F;Marmita da Semana,19.99F;
//    Cosmic Moon,0,0-
    String CONFIG_USERS = "configsUsers.txt";
//    BACKUP:
//

//    private ArrayList<Food> foods;
    public BasicConfigs(){

    }
    public ArrayList<String> getRestsConfigs(){
        try{
            ArrayList<String> rests = new ArrayList<>();
            FileReader fr = new FileReader(CONFIG_RESTS);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                rests.add(line);
            }
            br.close();
            fr.close();
            return rests;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public ArrayList<String> getUsers(){
        try{
            ArrayList<String> users = new ArrayList<>();
            FileReader fr = new FileReader(CONFIG_USERS);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                users.add(line);
            }
            br.close();
            fr.close();
            return users;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void addUser(String user, String cpf, int x, int y, Role role){
        try{
            FileWriter fw = new FileWriter(CONFIG_USERS, true);
            System.out.println(user+","+cpf+","+x+","+y+","+role+";\n");
            fw.write(user+","+cpf+","+x+","+y+","+role+";\n");
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void addRestaurant(String restaurant, int x, int y){
        try{
            FileWriter fw = new FileWriter(CONFIG_RESTS, true);
            fw.write(restaurant+","+x+","+y+"-\n");
            fw.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void addFood(String restaurant, String food, float price){
        try{
            ArrayList<String> rests = getRestsConfigs();
            ArrayList<String> updated = new ArrayList<>();

//            System.out.println("\n\n\n\n\n");
//            System.out.println(restaurant);
            for (String rest : rests){
                String r = rest.split("-")[0];
                String foods = "";
                try{
                    foods = rest.split("-")[1];
                }catch (ArrayIndexOutOfBoundsException ignored){

                }
//                System.out.println(r.split(",")[0]);
                if (Objects.equals(r.split(",")[0], restaurant)){
                    r = r + "-" + foods + food + "," + price + "F;";
                }else{
                    r = r + "-" + foods;
                }
                updated.add(r);
            }
            FileWriter clean = new FileWriter(CONFIG_RESTS);
            clean.write("");
            clean.flush();
            clean.close();
            FileWriter fw = new FileWriter(CONFIG_RESTS, true);
            for (String rest : updated) {
                fw.write(rest+"\n");
            }
            fw.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }

}
