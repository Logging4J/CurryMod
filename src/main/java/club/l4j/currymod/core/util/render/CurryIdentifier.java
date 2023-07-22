package club.l4j.currymod.core.util.render;

import club.l4j.currymod.CurryMod;
import net.minecraft.util.Identifier;

public class CurryIdentifier extends Identifier {
    public CurryIdentifier(String path) {
        super(CurryMod.MOD_ID, path);
    }
}
