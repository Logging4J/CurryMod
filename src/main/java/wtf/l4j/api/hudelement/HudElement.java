package wtf.l4j.api.hudelement;

import lombok.Getter;
import lombok.Setter;

import net.minecraft.client.gui.DrawContext;
import wtf.l4j.api.utils.ClientInfoInterface;
import wtf.l4j.api.utils.MinecraftInterface;

public abstract class HudElement implements MinecraftInterface, ClientInfoInterface {

    private final HudElementInfo hudElementInfo = getClass().getAnnotation(HudElementInfo.class);
    @Getter @Setter private String name = hudElementInfo.name();
    @Getter @Setter private boolean enabled = false;
    @Getter @Setter private int width;
    @Getter @Setter private int height;
    @Getter @Setter private int posX;
    @Getter @Setter private int posZ;

    public void toggle(){
        enabled = !enabled;
    }

    public abstract void onRender(DrawContext context, float tickDelta);

}