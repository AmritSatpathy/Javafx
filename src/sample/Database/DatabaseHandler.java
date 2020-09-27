package sample.Database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection dbConnection;
     public Connection getDbConnection() throws ClassNotFoundException, SQLException{
         String connectionString = "jdbc:mysql://"+ dbHost + ":" + dbPort + "/" + dbName;

         Class.forName("com.mysql.jdbc.Driver");

         dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

         return dbConnection;
     }


     //create user
     public void signUpUser(User user){

         String insert = "INSERT INTO "+Const.USERS_TABLE +"("+Const.USERS_FIRSTNAME+","+Const.USERS_LASTNAME+","+Const.USERS_USERNAME+","+
                 Const.USERS_PASSWORD+","+Const.USERS_GENDER+")" + "VALUES(?,?,?,?,?)";

         try {
             PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

             preparedStatement.setString(1,user.getFirstName());
             preparedStatement.setString(2,user.getLastName());
             preparedStatement.setString(3,user.getUserName());
             preparedStatement.setString(4,user.getPassword());
             preparedStatement.setString(5,user.getGender());

             preparedStatement.executeUpdate();

         } catch (SQLException | ClassNotFoundException throwables) {
             throwables.printStackTrace();
         }
     }
     //read user

    public ResultSet getUsers(User user){

        ResultSet resultSet = null;

        if(!user.getUserName().equals("")||!user.getPassword().equals("")){

         String query = "SELECT * FROM "+Const.USERS_TABLE+" WHERE "+Const.USERS_USERNAME+"=?"+" AND "+Const.USERS_PASSWORD+"=?";

             try {
                 PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                 preparedStatement.setString(1,user.getUserName());
                 preparedStatement.setString(2,user.getPassword());

                 resultSet = preparedStatement.executeQuery();

             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         }else {
              System.out.println("Enter Valid Credentials");
         }

         return resultSet;
    }

    //create user
    public void storetasks(Task task,int user){

        String insert = "INSERT INTO "+Const.TASKS_TABLE +"("+Const.TASKS_DESCRIPTION+","+Const.TASKS_TASK +","+Const.TASKS_USERID + ")"
                + "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,task.getDescription());
            preparedStatement.setString(2,task.getTask());
            preparedStatement.setInt(3,user);

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    //read user

    public ResultSet getTask(int usersid){

        ResultSet resultSet = null;


            String query = "SELECT task,description FROM "+Const.TASKS_TABLE+" WHERE "+Const.TASKS_USERID+"=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setInt(1,usersid);

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        return resultSet;
    }


}
