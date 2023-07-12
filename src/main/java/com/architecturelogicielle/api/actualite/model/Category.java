package com.architecturelogicielle.actualite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name="categorie")
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String libelle;

}