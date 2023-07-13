package com.architecturelogicielle.webapp.actualite.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class Article {
    
    private Integer id;

    private String titre;

    private String contenu;

    private Date dateCreation;

    private LocalDateTime dateModification;

    private Category categorie;



}
