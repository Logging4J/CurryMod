package club.l4j.currymod.feature.impl.hackimpl.combat;

import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.util.Player.Inventory.FindItemResult;
import club.l4j.currymod.util.Player.Inventory.IventoryUtilHEEDI;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.item.Items;

@Hack.Construct(name = "AutoTotem", description = "Totem in offHand", category = Hack.Category.COMBAT)
public class AutoTotem extends Hack {

	OptionBoolean lock = new OptionBoolean("Lock", false);
	OptionBoolean smart = new OptionBoolean("Smart", true);

	public AutoTotem() {
		addOptions(lock, smart);
	}

	int totems;
	private boolean locked;

	@DemoListen
	public void onTick() {
		FindItemResult result = IventoryUtilHEEDI.find(Items.TOTEM_OF_UNDYING);
        totems = result.getCount();
		if (mc.player.getInventory().contains(Items.TOTEM_OF_UNDYING.getDefaultStack())) {
			if (this.lock.isEnabled()) this.locked = true;
			if (mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
				if (!smart.isEnabled()) {
					this.locked = true;
					IventoryUtilHEEDI.move().from(result.getSlot()).to(IventoryUtilHEEDI.OFFHAND);
				} else {
					if (mc.player.getHealth() < 15 || mc.player.isFallFlying() || mc.player.fallDistance > 6) {
						this.locked = true;
						IventoryUtilHEEDI.move().from(result.getSlot()).to(IventoryUtilHEEDI.OFFHAND);
					} else {
						this.locked = false;
					}
				}
			}
			if(smart.isEnabled()) {
				this.locked = true;
			} else {
				if (mc.player.getHealth() < 10 || mc.player.isFallFlying() || mc.player.fallDistance > 6) {
					this.locked = true;
				} else {
					this.locked = false;
				}
			}
		}
	}

	public boolean isLocked() {
		return locked;
	}
}