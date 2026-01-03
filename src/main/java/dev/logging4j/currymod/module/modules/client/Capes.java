package dev.logging4j.currymod.module.modules.client;

import dev.logging4j.currymod.module.option.options.OptionMode;
import dev.logging4j.currymod.util.ResourceBank;
import lombok.Getter;
import net.minecraft.util.Identifier;
import dev.logging4j.currymod.module.Module;

import java.util.Map;

@Getter
@Module.Info(name = "Capes", description = "Capes", category = Module.Category.CLIENT)
public class Capes extends Module {

    private final OptionMode mode = new OptionMode("Mode", "specialsoygroup", "specialsoygroup", "longnose", "freckythenigger", "proffloyde", "nickelpincher");

    private final Map<String, Identifier> capeMap = Map.of(
            "specialsoygroup", ResourceBank.SPECIAL_SOY_GROUP_CAPE,
            "longnose", ResourceBank.LONG_NOSE,
            "freckythenigger", ResourceBank.FRECKY_THE_NIGGER,
            "proffloyde", ResourceBank.PROF_FLOYDE,
            "nickelpincher", ResourceBank.NICKEL_PINCHER
    );

    public Capes() {
        addOptions(mode);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
