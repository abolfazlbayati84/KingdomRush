package Model.Player;

import Controller.PlayerController;
import Exceptions.UserNotFound;
import Model.MySQL.MySQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    static private Player player;
    private int id;
    private String username;
    private String password;
    private int level;
    private int diamond;
    private boolean isLoggedIn=false;
    private Map<Spells,Integer> backpack;
    private Player(){
        backpack = new HashMap<Spells, Integer>();
    }

    static public Player getPlayer() {
        if(player == null){
            player = new Player();
        }
        return player;
    }
    public void deleteSpells(String spell){
        String sqlCom = String.format("Update users SET "+spell+" = '"+ backpack.get(Spells.valueOf(spell)) +"' WHERE iD = '"+this.id+"'");
        MySQL sql = new MySQL();
        try {
            sql.executeSQL(sqlCom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void login(String username, String password) throws Exception {
        String sqlCom = String.format("SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"'");
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlCom);
        if(rs.next())
        {
            this.id=rs.getInt(1);
            this.username=rs.getString(2);
            this.password=rs.getString(3);
            this.level=rs.getInt(4);
            this.diamond=rs.getInt(5);
            this.isLoggedIn=true;
            getSpellsFromDB();
        }
        else
            throw new UserNotFound();
    }
    public void getSpellsFromDB() throws Exception {
        String sqlCmd = "SELECT * from users where iD = '"+this.id+"'";
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlCmd);
        String spell;
        backpack.put(Spells.HealthKit,0);
        backpack.put(Spells.Freeze,0);
        backpack.put(Spells.Coin,0);
        backpack.put(Spells.LittleChild,0);
//
        if(rs.next()){
            backpack.put(Spells.HealthKit,rs.getInt(6));
            backpack.put(Spells.Freeze, rs.getInt(7));
            backpack.put(Spells.Coin, rs.getInt(8));
            backpack.put(Spells.LittleChild, rs.getInt(9));
        }
    }
    public void logout(){
        this.isLoggedIn = false;
    }

    public void signUpPlayer(String username, String password, int level, int diamond) throws Exception {
        String sqlCom =String.format("INSERT INTO `users` (`iD`,`username`,`password`,`level`,`diamond`,`HealthKit`,`Freeze`,`Coin`,`LittleChild`) VALUES ('%d', '%s','%s','%d','%d','%d','%d','%d','%d')",getMaxID()+1,username,password,level,diamond,0,0,0,0);
        MySQL sql = new MySQL();
        try {
            sql.executeSQL(sqlCom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addNewInformation(String newUsername,String newPassword){
        String sqlCom = String.format("Update users SET username = '"+newUsername+"', password = '"+newPassword+"' WHERE iD = '"+this.id+"'");
        MySQL sql = new MySQL();
        try {
            sql.executeSQL(sqlCom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.username = newUsername;
        this.password = newPassword;
    }
    public void addToBackpack(String spell,int number){
        String sqlCom = String.format("Update users SET "+spell+" = '"+number+"' WHERE iD = '"+this.id+"'");
        MySQL sql = new MySQL();
        try {
            sql.executeSQL(sqlCom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static int getMaxID() throws Exception {
        String sqlCmd = "SELECT MAX(iD) from users";
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlCmd);
        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public int getDiamond() {
        return diamond;
    }

    public Map<Spells, Integer> getBackpack() {
        return backpack;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDiamond(int diamond) {
        String sqlCom =String.format("Update users SET diamond = '"+diamond+"' WHERE iD = '"+this.id+"'");
        MySQL sql = new MySQL();
        try {
            sql.executeSQL(sqlCom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.diamond = diamond;
    }

    public void setBackpack(Map<Spells, Integer> backpack) {
        this.backpack = backpack;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
