package com.architecturelogicielle.actualite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name="article")
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String titre;

    private String contenu;

    private Date dateCreation;

    private LocalDateTime dateModification;

    @ManyToOne
    @JoinColumn(name = "categorie")
    private Category categorie_id;



}
