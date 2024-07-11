package Model.Player;

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
        String sqlCmd = "SELECT * from backpack where playerID = '"+this.id+"'";
        MySQL sql = new MySQL();
        ResultSet rs = sql.executeQuery(sqlCmd);
        String spell;
        backpack.put(Spells.HealthKit,0);
        backpack.put(Spells.Freeze,0);
        backpack.put(Spells.Coin,0);
        backpack.put(Spells.LittleChild,0);
        while(rs.next()){
            spell = rs.getString(2);
            if(spell.equals("HealthKit")){
                backpack.replace(Spells.HealthKit,backpack.get(Spells.HealthKit)+1);
            }
            if(spell.equals("Freeze")){
                backpack.replace(Spells.Freeze,backpack.get(Spells.Freeze)+1);
            }
            if(spell.equals("Coin")){
                backpack.replace(Spells.Coin,backpack.get(Spells.Coin)+1);
            }
            if(spell.equals("LittleChild")){
                backpack.replace(Spells.LittleChild, backpack.get(Spells.LittleChild)+1);
            }
        }
    }
    public void logout(){
        this.isLoggedIn = false;
    }

    public void signUpPlayer(String username, String password, int level, int diamond) throws Exception {
        String sqlCom =String.format("INSERT INTO `users` (`iD`,`username`,`password`,`level`,`diamond`) VALUES ('%d', '%s','%s','%d','%d')",getMaxID()+1,username,password,level,diamond);
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
    public void addToBackpack(String spell){
        String sqlCom = String.format("INSERT INTO `backpack`(`playerID`,`content`) VALUES('%d','%s')",id,spell);
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
