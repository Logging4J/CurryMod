package wtf.l4j.mixin.mixins;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import wtf.l4j.api.utils.CurryIdentifier;

@Mixin(SplashTextResourceSupplier.class)
public class MixinSplashTextResourceSupplier {

    @Shadow @Final private static Identifier RESOURCE_ID = new CurryIdentifier("texts/splashes.txt");

}
