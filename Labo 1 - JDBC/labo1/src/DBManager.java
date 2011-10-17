/**
 *
 * @author Numa Trezzini
 * @author
 * @version 
 */



import java.sql.*;


/**
 * Classe DBManager
 * @see 
 */
public class DBManager {

    public static Connection connect(String database, String username, String password){
        try{Class.forName("com.mysql.jdbc.Driver");}
        catch(ClassNotFoundException e){System.out.println(e.getStackTrace());}

        String db = "jdbc:mysql://" + database;
        Connection connexion;
        try{
            connexion = DriverManager.getConnection(db, username, password);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return connexion;
    }

    public static ResultSet request(Connection connexion, String request){
        Statement st;
        ResultSet rs;
        try{
            st = connexion.createStatement();
            rs = st.executeQuery(request);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

        return rs;
    }

}
