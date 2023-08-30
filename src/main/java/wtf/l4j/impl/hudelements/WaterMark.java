package wtf.l4j.impl.hudelements;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.hudelement.HudElement;
import wtf.l4j.api.hudelement.HudElementInfo;
import wtf.l4j.api.manager.Managers;
import wtf.l4j.api.utils.text.TextUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@HudElementInfo(name = "WaterMark")
public class WaterMark extends HudElement {

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        context.drawTextWithShadow(mc.textRenderer,"CurryMod " + TextUtil.GRAY +"["+TextUtil.WHITE + dtf.format(now) + TextUtil.GRAY +"]" , 0, 1, Managers.getColorManager().getRGBA());
    }
}
