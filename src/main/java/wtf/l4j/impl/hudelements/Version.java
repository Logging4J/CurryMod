package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "useless dogshit")
public class Version extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        String s = "Version " + TextUtil.GRAY + version;
        context.drawTextWithShadow(mc.textRenderer, s, mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(s), mc.getWindow().getScaledHeight() - mc.textRenderer.fontHeight, CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
