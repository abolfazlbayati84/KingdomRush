package Model.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {
    String URl = "jdbc:mysql://localhost/game";
    String userName = "root";
    String password = "123";
    public Boolean executeSQL(String sqlCmd) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URl,userName,password);

            Statement s = con.prepareStatement(sqlCmd);
            s.execute(sqlCmd);
            con.close();
            return true;
        } catch (Exception e) {
            //return false;
            throw new Exception(e.getMessage());
        }
    }
    public ResultSet executeQuery(String sqlCmd) throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URl,userName,password);

            Statement s = con.prepareStatement(sqlCmd);
            ResultSet rs = s.executeQuery(sqlCmd);
            return rs;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
            //System.out.println(e.getMessage());
        }
    }
}
