package dev.l4j.currymod.client.cape;

import dev.l4j.currymod.util.ResourceBank;
import lombok.Getter;
import net.minecraft.util.Identifier;

import java.util.*;

public class CapeManager {

    @Getter
    private final List<Cape> capes = new ArrayList<>();
    private final Map<String, Cape> capeMap = new HashMap<>();

    public Cape currentCape;
    public boolean customCape = false;

    public CapeManager() {
        add(new Cape("SpecialSoyGroup", ResourceBank.SPECIAL_SOY_GROUP_CAPE));
        add(new Cape("Harbinger", ResourceBank.HARBINGER_CAPE));
        add(new Cape("longnose", ResourceBank.LONG_NOSE));
        add(new Cape("freckythenigger", ResourceBank.FRECKY_THE_NIGGER));
        add(new Cape("totenkoph", ResourceBank.TOTENKOPH));
        add(new Cape("proffloyde", ResourceBank.PROF_FLOYDE));
        add(new Cape("nickelpincher", ResourceBank.NICKEL_PINCHER));

        currentCape = capes.getFirst();
    }

    private void add(Cape cape) {
        capes.add(cape);
        capeMap.put(cape.name().toLowerCase(), cape);
    }

    public Optional<Cape> getCapeByName(String name) {
        return Optional.ofNullable(capeMap.get(name.toLowerCase()));
    }

    public record Cape(String name, Identifier identifier) {}
}
