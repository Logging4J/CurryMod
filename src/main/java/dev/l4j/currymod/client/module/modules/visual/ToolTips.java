package dev.l4j.currymod.client.module.modules.visual;

import de.florianmichael.dietrichevents2.DietrichEvents2;
import dev.l4j.currymod.client.module.Module;
import dev.l4j.currymod.listener.ItemStackTooltipListener;
import dev.l4j.currymod.util.InventoryUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Arrays;
import java.util.stream.Collectors;

@Module.Info(name = "ToolTips", description = "Better Tool Tips", category = Module.Category.VISUAL)
public class ToolTips extends Module implements ItemStackTooltipListener {

    private static final int[] AXOLOTL_COLORS = new int[] {
            0xFFC7EC, 0x8C6C50, 0xFAD41B, 0xE8F7Fb, 0xB6B5FE
    };

    @Override
    protected void onEnable() {
        DietrichEvents2.global().subscribe(ItemStackTooltipEvent.ID, this);
    }

    @Override
    protected void onDisable() {
        DietrichEvents2.global().unsubscribe(ItemStackTooltipEvent.ID, this);
    }

    @Override
    public void onItemStackToolTip(ItemStackTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if (stack.isDamageable()) {
            int max = stack.getMaxDamage();
            int dmg = stack.getDamage();
            int remaining = max - dmg;
            int pct = Math.round(remaining * 100f / max);

            int unbreaking = InventoryUtils.getEnchantmentLevel(stack, Enchantments.UNBREAKING);
            int effectiveUses = remaining * (unbreaking + 1);

            Text durabilityText = Text.literal("")
                    .append(Text.literal("Durability: ").formatted(Formatting.GRAY))
                    .append(Text.literal(pct + "%")
                            .formatted(pct < 10 ? Formatting.RED : pct < 30 ? Formatting.YELLOW : Formatting.GREEN))
                    .append(Text.literal(" (").formatted(Formatting.GRAY))
                    .append(Text.literal(unbreaking > 0 ? "~" + effectiveUses : String.valueOf(remaining))
                            .formatted(unbreaking > 0 ? Formatting.AQUA: Formatting.GRAY))
                    .append(Text.literal(" left)").formatted(Formatting.GRAY));

            event.appendEnd(durabilityText);
        }

        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (foodComponent != null) {
            Text hungerText = Text.literal("Restores: ").formatted(Formatting.GRAY)
                    .append(Text.literal(String.valueOf(foodComponent.nutrition())).formatted(Formatting.GREEN));

            Text satText = Text.literal("Saturation: ").formatted(Formatting.GRAY)
                    .append(Text.literal(String.format("%.1f", foodComponent.saturation())).formatted(Formatting.GOLD));

            event.appendEnd(hungerText);
            event.appendEnd(satText);
        }

        AxolotlEntity.Variant axolotlVariant = stack.get(DataComponentTypes.AXOLOTL_VARIANT);
        if (axolotlVariant != null) {
            String axovarst = axolotlVariant.getId().replace("-", " ");
            Arrays.stream(axovarst.split("[^A-Za-z0-9]+"))
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        String lower = s.toLowerCase();
                        return Character.toUpperCase(lower.charAt(0))
                                + lower.substring(1);
                    })
                    .collect(Collectors.joining(" "));

            Text variantText =  Text.literal("Variant: ").formatted(Formatting.GRAY)
                    .append(axovarst)
                    .setStyle(Style.EMPTY.withColor(AXOLOTL_COLORS[axolotlVariant.getIndex()]));

            event.appendEnd(variantText);
        }
    }
}
