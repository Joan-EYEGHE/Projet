package services;

import java.util.List;

import entities.Compte;
import repositories.CompteRepository;

public class CompteService {
   
    //Dependances
    CompteRepository compteRepository=new CompteRepository();
    public void ajouterCompte(Compte compte){
        compteRepository.insert(compte);
    }

    public List<Compte> listerCompte(){
        return compteRepository.selectAll();
    }
}
