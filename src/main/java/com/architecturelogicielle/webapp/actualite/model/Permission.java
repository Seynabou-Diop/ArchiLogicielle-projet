package com.architecturelogicielle.webapp.actualite.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    EDITEUR_READ("editeur:read"),
    EDITEUR_UPDATE("editeur:update"),
    EDITEUR_CREATE("editeur:create"),
    EDITEUR_DELETE("editeur:delete")

    ;

    @Getter
    private final String permission;
}