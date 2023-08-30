package wtf.l4j.mixin.mixins;

import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameOptions.class)
public class MixinGameOptions {

    @Shadow @Final private SimpleOption<Integer> fov = new SimpleOption<Integer>("options.fov", SimpleOption.emptyTooltip(), (optionText, value) -> switch (value) {
        case 70 -> GameOptions.getGenericValueText(optionText, Text.translatable("options.fov.min"));
        case 110 -> GameOptions.getGenericValueText(optionText, Text.translatable("options.fov.max"));
        default -> GameOptions.getGenericValueText(optionText, value);
    }, new SimpleOption.ValidatingIntSliderCallbacks(30, 200), Codec.DOUBLE.xmap(value -> (int)(value * 40.0 + 70.0), value -> ((double)value.intValue() - 70.0) / 40.0), 70, value -> MinecraftClient.getInstance().worldRenderer.scheduleTerrainUpdate());


}
