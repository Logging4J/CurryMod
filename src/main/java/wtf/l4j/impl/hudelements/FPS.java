package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "FPS")
public class FPS extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        context.drawTextWithShadow(mc.textRenderer,"FPS " + TextUtil.GRAY + mc.getCurrentFps(), 0, mc.getWindow().getScaledHeight() - (mc.textRenderer.fontHeight * 3), CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
