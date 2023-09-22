package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

@HudElementInfo(name = "Direction")
public class Direction extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        mc.getCameraEntity().getHorizontalFacing();
        context.drawTextWithShadow(mc.textRenderer, "Direction:" + TextUtil.GRAY + mc.getCameraEntity().getHorizontalFacing(), 2, 22 + mc.textRenderer.fontHeight * 4, CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
