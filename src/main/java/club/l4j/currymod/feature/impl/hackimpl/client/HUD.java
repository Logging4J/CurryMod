package club.l4j.currymod.feature.impl.hackimpl.client;

import club.l4j.currymod.CurryMod;
import club.l4j.currymod.event.events.Render2DEvent;
import club.l4j.currymod.feature.core.Hack;
import club.l4j.currymod.feature.options.Option;
import club.l4j.currymod.feature.options.impl.OptionBoolean;
import club.l4j.currymod.util.render.RenderUtils;
import club.l4j.currymod.util.TextUtil;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Hack.Construct(name = "HUD", description = "heads up display", category = Hack.Category.CLIENT)
public class HUD extends Hack {

    public OptionBoolean logo = new OptionBoolean("Logo", false);
    public OptionBoolean watermark = new OptionBoolean("Watermark", true);
    public OptionBoolean welcome = new OptionBoolean("Welcomer", true);
    public OptionBoolean fps = new OptionBoolean("FPS", true);
    public OptionBoolean ping = new OptionBoolean("Ping", true);
    public OptionBoolean coords = new OptionBoolean("Coords", true);
    public OptionBoolean hackList = new OptionBoolean("HackList", true);

    public HUD(){
        addOptions(logo, welcome, fps, ping, coords, hackList, watermark);
    }

    public MinecraftClient mc = MinecraftClient.getInstance();


    @DemoListen
    public void onRender2D(Render2DEvent e){
        int width = mc.getWindow().getScaledWidth();
        int height = mc.getWindow().getScaledHeight();
        //ty SwavyMan69 fo logo
        if(watermark.isEnabled()){
            e.getContext().drawTextWithShadow(mc.textRenderer, TextUtil.AQUA + CurryMod.MOD_NAME + TextUtil.WHITE +"b" + CurryMod.VERSION,-1,1,-1);
        }
        if(logo.isEnabled()) {
            RenderUtils.drawImage(e.getContext(), 1, 0, 50, 50, null, "textures/curry.png");
        }
        if(welcome.isEnabled()) {
            String text = TextUtil.AQUA + "Welcome" + TextUtil.WHITE + mc.player.getGameProfile().getName();
            e.getContext().drawTextWithShadow(mc.textRenderer, text, width / 2 - (mc.textRenderer.getWidth(text) / 2), 1, -1);
        }
        if(fps.isEnabled()) {
            e.getContext().drawTextWithShadow(mc.textRenderer, TextUtil.AQUA + "FPS:" + TextUtil.WHITE + mc.getCurrentFps(), 0, height - (mc.textRenderer.fontHeight * 3), -1);
        }
        if(ping.isEnabled()) {
            e.getContext().drawTextWithShadow(mc.textRenderer, TextUtil.AQUA + "Ping:" + TextUtil.WHITE + (mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()) == null ? 0 : mc.player.networkHandler.getPlayerListEntry(mc.player.getGameProfile().getId()).getLatency()), 0, height - (mc.textRenderer.fontHeight * 2), -1);
        }
        if(coords.isEnabled()) {
            e.getContext().drawTextWithShadow(mc.textRenderer, TextUtil.AQUA + "XYZ:" + TextUtil.WHITE + Math.round(mc.player.getX() * 100.0) / 100.0 + " ," + Math.round(mc.player.getY() * 100.0) / 100.0 + " ," + Math.round(mc.player.getZ() * 100.0) / 100.0, 0, height - mc.textRenderer.fontHeight, -1);
        }
        if(hackList.isEnabled()) {
            ArrayList<String> list = new ArrayList<>();
            for (Hack hack : CurryMod.featureManager.getEnabledHackFeatures()) {
                list.add(TextUtil.AQUA + hack.getName() + (hack.getContent() != null ? TextUtil.WHITE + hack.getContent() : ""));
            }
            list.sort(Comparator.comparingInt(s -> mc.textRenderer.getWidth(s)));
            Collections.reverse(list);
            int y = 2;
            for (final String name : list) {
                e.getContext().drawTextWithShadow(mc.textRenderer, name, (width - mc.textRenderer.getWidth(name)) - 3, y + 2, -1);
                y += 10;
            }
        }
    }
}
