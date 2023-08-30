package wtf.l4j.api.module;

import lombok.Getter;

public enum Category {
    CRASH("Crash"),
    EXPLOIT("Exploit"),
    MISC("Misc"),
    VISUAL("Visual"),
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    CLIENT("Client");

    @Getter private String name;

    Category(String name){
        this.name = name;
    }
}
