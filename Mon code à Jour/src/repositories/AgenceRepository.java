package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Agence;
//SOLID
//Single Responsability
public class AgenceRepository {
    //Table  
     
    //select
    public  List<Agence> selectAll(){
          List<Agence> agences=new ArrayList<>();

         try {    
          // 1- Chargement du driver
          Class.forName("com.mysql.cj.jdbc.Driver");
          // System.out.println("Chargement du Driver OK");

          // 2- Connexion à la BD
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024",
          "root", "");
          // System.out.println("connexion à la BD OK"); 

          // 3- Exécuter une requête
          Statement statement= conn.createStatement();
          ResultSet rs =statement.executeQuery("Select * from agence");
          while (rs.next()) {
               // Une ligne de ResultSet => Agence : conversion
               Agence ag=new Agence();
               ag.setId(rs.getInt("id_ag")); 
               ag.setNumero(rs.getString("numero_ag")); 
               ag.setAdresse(rs.getString("adresse_ag")); 
               ag.setTelephone(rs.getString("tel_ag")); 
               agences.add(ag);
          }
          rs.close();
          conn.close();
          
          
          } catch (ClassNotFoundException e) {
               // En cas d'echec de connexion au driver 
               System.out.println("Echec du chargement du Driver");
          } 
          catch (SQLException e) {
               // En cas d'erreur de connexion à la BD
               System.out.println("Echec de la connexion à la BD"); 
          }
          return agences;
    }
    public  Agence selectByNumero(String numero){
     Agence ag =null;

     try {    
      // 1- Chargement du driver
      Class.forName("com.mysql.cj.jdbc.Driver");
     //  System.out.println("Driver OK");

      // 2- Connexion à la BD
     //  System.out.println("Connexion OK"); 
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024", "root", "");

      // 3- Exécuter une requête
      ag =new Agence();
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("select * from Agence where numero_ag like '"+ numero +"'");
          if (rs.next()) {
               ag.setId(rs.getInt("id_ag"));
               ag.setNumero(rs.getString("numero_ag"));
               ag.setAdresse(rs.getString("adresse_ag"));
               ag.setTelephone(rs.getString("tel_ag"));
          }
          rs.close();
          conn.close();
     }
      catch (ClassNotFoundException e) {
           // En cas d'echec de connexion au driver 
           System.out.println("Echec Driver");
      } 
      catch(SQLException e){
          System.out.println("Erreur Connexion");
      }
     
     return ag;
     }
 
    public  void insert(Agence agence){
          try  {
           // 1- Chargement du driver
               Class.forName("com.mysql.cj.jdbc.Driver");

          // 2- Connexion à la BD
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iage_ism_2024","root", "");
          
               // 3- Exécuter une requête
          Statement statement = conn.createStatement();
          int nbreLigne = statement.executeUpdate("INSERT INTO `agence` (`numero_ag`, `adresse_ag`, `tel_ag`) VALUES ('"+agence.getNumero()+"', '"+agence.getAdresse()+"', '"+agence.getTelephone()+"')");
          
          } 
           catch (ClassNotFoundException e) {
               System.out.println("Echec Driver");
           }
           catch(SQLException e){
               System.out.println("Echec Connexion");
           }
     }
}
