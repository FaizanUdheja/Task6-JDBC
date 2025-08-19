package Task_6;
import java.sql.*;
import java.util.Scanner;

public class JDBCEx {
        public static void main(String args[]) throws SQLException {
            String url = "jdbc:mysql://localhost:3306/example";
            String username = "root";
            String passsword = "";

            Scanner s = new Scanner(System.in);

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                //Loading Jdbc Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Connection
                con = DriverManager.getConnection(url, username, passsword);
                //Input
                System.out.println("Enter User ID");
                int id = s.nextInt();
                s.nextLine();
                System.out.println("Enter User Name");
                String name = s.nextLine();
                System.out.println("Enter User Email");
                String email = s.nextLine();
                //SQL Query
                String query = "Insert INTO user values(?,?,?)";
                ps = con.prepareStatement(query);
                //Set Parameters
                ps.setInt(1,id);
                ps.setString(2, name);
                ps.setString(3, email);
                // Execute update
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student record updated successfully.");
                } else {
                    System.out.println("No record found");
                }
                //Displaying Data
                rs = ps.executeQuery("Select * from user");
                if (rs.next()) {
                    String uname = rs.getString("name");
                    String uemail = rs.getString("email");
                    System.out.println("UserName:- " + uname);
                    System.out.println("User Email:- " + uemail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
        }
    }
