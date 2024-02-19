package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Client;

public class ClientRepository {
      
      
      public void insert(Client client){
        try  {
           // 1- Chargement du driver
           Class.forName("com.mysql.cj.jdbc.Driver");

          // 2- Connexion à la BD
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024","root", "");
          
               // 3- Exécuter une requête
          Statement statement = conn.createStatement();

          String sql=String.format("INSERT INTO `client` (`nom_client`, `prenom`, `tel_client`)"+
          "VALUES ('%s', '%s', '%s')", 
          client.getNom(), client.getPrenom(), client.getTelephone());

          int nbreLigne = statement.executeUpdate(sql);
          
        } 
           catch (ClassNotFoundException e) {
               System.out.println("Echec Driver");
           }
           catch(SQLException e){
               System.out.println("Echec Connexion");
           }
      }

      
      public List<Client> selectAll(){
        List<Client> clients =new ArrayList<>();
        
         try  {
           // 1- Chargement du driver
              Class.forName("com.mysql.cj.jdbc.Driver");

            // 2- Connexion à la BD
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024","root", "");
          
            // 3- Exécuter une requête
              Statement statement = conn.createStatement();
              String sql="select * from client";
              ResultSet rs = statement.executeQuery(sql); 
            
            while (rs.next()) {
              Client client =new Client();
              client.setNom(rs.getString("nom_client"));
              client.setPrenom(rs.getString("prenom"));
              client.setTelephone(rs.getString("tel_client"));
              clients.add(client);
            }
            statement.close();
            rs.close();
            conn.close(); 
          } 
            catch (ClassNotFoundException e) {
              System.out.println("Echec Driver ");
            }
            catch(SQLException e){
              System.out.println("Encore la Connexion qui chie "+ e);
            }
          return clients;
      }
      
      public Client selectClientByTel(String tel){
        Client client=null;

        try  {
          // 1- Chargement du driver
             Class.forName("com.mysql.cj.jdbc.Driver");

           // 2- Connexion à la BD
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024","root", "");
         
           // 3- Exécuter une requête
             Statement statement = conn.createStatement();
             String sql=String.format("SELECT * FROM client where tel_client like '%s'", tel);
             ResultSet rs = statement.executeQuery(sql);

             if (rs.next()) {
              client =new Client();
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom"));
                client.setTelephone(rs.getString("tel_client"));
            }
           rs.close();
           conn.close(); 
         } 
           catch (ClassNotFoundException e) {
             System.out.println("Echec Driver ");
           }
           catch(SQLException e){
             System.out.println("Encore la Connexion qui chie "+ e);
           }
      
      return client;        
      }
}
