package wtf.l4j.mixin.mixins;

import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import wtf.l4j.api.utils.CurryIdentifier;

@Mixin(LogoDrawer.class)
public class MixinLogoDrawer {

    @Shadow @Final public static Identifier MINCERAFT_TEXTURE = new CurryIdentifier("textures/gui/minecraft.png");
    @Shadow @Final public static Identifier LOGO_TEXTURE = new CurryIdentifier("textures/gui/minecraft.png");;
    @Shadow @Final public static Identifier EDITION_TEXTURE = new CurryIdentifier("textures/gui/edition.png");;

}
