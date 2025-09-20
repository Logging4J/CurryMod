package dev.l4j.currymod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.listener.ItemStackTooltipListener;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @ModifyReturnValue(method = "getTooltip", at = @At("RETURN"))
    private List<Text> onGetTooltip(List<Text> original) {
        ItemStackTooltipListener.ItemStackTooltipEvent event = new ItemStackTooltipListener.ItemStackTooltipEvent((ItemStack)(Object)this, original);
        DietrichEvents2.global().call(ItemStackTooltipListener.ItemStackTooltipEvent.ID, event);

        return event.getList();
    }
}
