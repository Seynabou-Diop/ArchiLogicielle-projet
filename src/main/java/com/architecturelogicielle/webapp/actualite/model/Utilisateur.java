package com.architecturelogicielle.webapp.actualite.model;

import lombok.Data;


@Data
public class Utilisateur {

    private Long id;
    private String nom;

    private String email;
    private String motDePasse;

    private Role role;
}
