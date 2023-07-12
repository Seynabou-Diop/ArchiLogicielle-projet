package com.architecturelogicielle.actualite.service;

import com.architecturelogicielle.actualite.model.Administrateur;
import com.architecturelogicielle.actualite.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository adminRepo;
    public Iterable<Administrateur> getAdmins(){
            return adminRepo.findAll();
    }

    public Optional<Administrateur> getAdmin(final Long id){
        return adminRepo.findById(id);
    }

    public Administrateur saveAdmin(Administrateur admin){
        return adminRepo.save(admin);
    }

    public void deleteAdministrateur(final Long id){
        adminRepo.deleteById(id);
    }
}
