package dao;

import model.User;
import utils.Props;

import java.sql.*;

public class UserDaoImpl implements UserDao{

    public final static String DB_URL = Props.getValue("db.url");
    public final static String DB_LOGIN = Props.getValue("db.user");
    public final static String DB_PASSWORD = Props.getValue("db.password");

    public Connection connectionWithDBMySQL() throws SQLException {
        Connection con = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        return con;
    }

    @Override
    public User findByName(String name) {

        try(Connection con = connectionWithDBMySQL();
            //Connection con = DriverManager.getConnection(url, login, password);
            PreparedStatement stat = con.prepareStatement("SELECT id, login, password FROM my_schema_eeexo.users where login = ?")){
            stat.setString(1, name);
            con.setAutoCommit(true);
            ResultSet resultSet = stat.executeQuery();
                if(resultSet.next()){
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    System.out.println("Нашли пользователя: " + login + " " + password);
                    return new User(login, password);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createRow(String name, String password) {
//        try (Connection con = connectionWithDBMySQL()){
//            Statement statement = con.createStatement();
//            statement.executeUpdate("INSERT into my_schema_eeexo.users (login, password) values ('Vovan', '777')");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try (Connection con = connectionWithDBMySQL()){
            PreparedStatement stat = con.prepareStatement("INSERT into my_schema_eeexo.users (login, password) values (?, ?)");
            stat.setString(1, name);
            stat.setString(2, password);
            stat.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void allRowDB() {
        try(Connection con = connectionWithDBMySQL()){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, password FROM my_schema_eeexo.users");
            while(resultSet.next()){
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                System.out.println(login + " : " + password);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateDB(String loginOld, String loginNew) {
//        try(Connection con = connectionWithDBMySQL()){
//            Statement statement = con.createStatement();
//            statement.executeUpdate("UPDATE  my_schema_eeexo.users SET login = 'Vova' WHERE login = 'Vladimir'");
//        }catch (SQLException e){
//           e.printStackTrace();
//        }
        try(Connection con = connectionWithDBMySQL()){
            PreparedStatement stat = con.prepareStatement("UPDATE  my_schema_eeexo.users SET login = ? WHERE login = ?");
            stat.setString(1, loginNew);
            stat.setString(2, loginOld);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRowDB(int id) {
        try(Connection con = connectionWithDBMySQL()){
//            Statement statement = con.createStatement();
//            statement.executeUpdate("delete from my_schema_eeexo.users where id = 6");
            PreparedStatement stat = con.prepareStatement("delete from my_schema_eeexo.users where id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
