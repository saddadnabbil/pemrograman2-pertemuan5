/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugaspertemuan5saddadnabbil;

import java.sql.DriverManager;

/**
 *
 * @author saddadnabbil
 */
public class Main {
    private static java.sql.Connection connection;
    
   
    public static java.sql.Connection getKoneksi(){
        if (connection == null){
            try {
                String url = "jdbc:mysql://localhost:3306/pertemuan5_pemrograman2";
                String user = "root";
                String password = "root";
		Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected");
            } catch (Exception e) {
                System.out.println("Error: Disconnected");
            }
        }
        return connection;
    }
    
    public static void main(String args[]){
        getKoneksi();
        LatihanSoal tp5 = new LatihanSoal();
        tp5.setVisible(true);
    }   
    
}
