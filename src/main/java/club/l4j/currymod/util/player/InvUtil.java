package club.l4j.currymod.util.player;

import club.l4j.currymod.util.IGlobals;
import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Set;

public class InvUtil implements IGlobals {

    public static final Set<Item> THROWABLES = Sets.newHashSet(
            Items.SNOWBALL, Items.EXPERIENCE_BOTTLE, Items.EGG, Items.SPLASH_POTION, Items.ENDER_PEARL
    );
}
