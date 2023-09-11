package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "Welcomer")
public class Welcomer extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        String text = "Welcome Dadi " + TextUtil.GRAY + "[" + TextUtil.WHITE +mc.player.getName().getString() + TextUtil.GRAY + "]";
        context.drawTextWithShadow(mc.textRenderer,text,mc.getWindow().getScaledWidth() / 2 - (mc.textRenderer.getWidth(text) / 2), 1, Managers.getColorManager().getRGBA());
    }
}
