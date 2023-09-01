package wtf.l4j.mixin.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import wtf.l4j.api.utils.render.CurryIdentifier;

@Mixin(Screen.class)
public class MixinScreen {

    @Shadow @Final public static Identifier OPTIONS_BACKGROUND_TEXTURE = new CurryIdentifier("textures/gui/options_background.png");

}
