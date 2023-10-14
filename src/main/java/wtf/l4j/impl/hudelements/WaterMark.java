package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.CurryMod;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.ClientInfoInterface;
import wtf.l4j.api.utils.text.TextUtil;


@HudElementInfo(name = "WaterMark")
public class WaterMark extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        context.drawTextWithShadow(mc.textRenderer,  clientName +" " + TextUtil.GRAY +"[" +TextUtil.WHITE + "v" + version + TextUtil.GRAY +"]" , 0, 1, CurryMod.getInstance().getManagers().getColorManager().getRGBA());
    }
}
