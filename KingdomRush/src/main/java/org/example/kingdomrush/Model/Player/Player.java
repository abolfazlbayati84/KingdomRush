package org.example.kingdomrush.Model.Player;

import org.example.kingdomrush.Exceptions.UserNotFound;
import org.example.kingdomrush.Model.MySQL.MySQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Player {
    static private Player player;
    private int id;
    private String username;
    private String password;
    private int level;
    private int diamond;
    private ArrayList<String> backpack;
    private boolean isLoggedIn=false;
    private Player(){
        backpack=new ArrayList<>();
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
        }
        else
            throw new UserNotFound();
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

    public ArrayList<String> getBackpack() {
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
        this.diamond = diamond;
    }

    public void setBackpack(ArrayList<String> backpack) {
        this.backpack = backpack;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
