package com.architecturelogicielle.webapp.actualite.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_READ,
                    Permission.EDITEUR_READ,
                    Permission.EDITEUR_UPDATE,
                    Permission.EDITEUR_DELETE,
                    Permission.EDITEUR_CREATE
            )
    ),
    EDITEUR(
            Set.of(
            		Permission.EDITEUR_READ,
            		Permission.EDITEUR_UPDATE,
            		Permission.EDITEUR_DELETE,
            		Permission.EDITEUR_CREATE
            )
    ),



    ;


    Role(Set<Permission> permissions) {
    }
}
