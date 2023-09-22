package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "Ping")
public class Ping extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        context.drawTextWithShadow(mc.textRenderer,"Ping "+TextUtil.GRAY + (mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()) == null ? 0 : mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()).getLatency()), 0, mc.getWindow().getScaledHeight() - (mc.textRenderer.fontHeight * 2), CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
