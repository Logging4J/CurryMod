package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "XYZ")
public class XYZ extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        context.drawTextWithShadow(mc.textRenderer ,"XYZ " + TextUtil.GRAY + Math.round(mc.player.getX() * 100.0) / 100.0 + " ," + Math.round(mc.player.getY() * 100.0) / 100.0 + " ," + Math.round(mc.player.getZ() * 100.0) / 100.0, 0, mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight, CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
