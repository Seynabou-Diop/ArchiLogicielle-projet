package com.architecturelogicielle.webapp.actualite.service;

import com.architecturelogicielle.webapp.actualite.model.Utilisateur;
import com.architecturelogicielle.webapp.actualite.repository.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserProxy userProxy;

    public List<Utilisateur> getAllUtilisateurs() {
        return userProxy.getAllUtilisateurs();
    }

    public Utilisateur getUtilisateurById(int id) {
        return userProxy.getUtilisateur(id);
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return userProxy.createUtilisateur(utilisateur);
    }

    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return userProxy.updateUtilisateur(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        userProxy.deleteUtilisateur(id);
    }
}
