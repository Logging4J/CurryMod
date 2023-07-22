package club.l4j.currymod.impl.hacks.visual;

import club.l4j.currymod.core.hack.Hack;
import net.minecraft.client.option.Perspective;

@Hack.Construct(name = "FreeLook", description = "look freely", category = Hack.Category.VISUAL)
public class FreeLook extends Hack {

    public static float cameraPitch;
    public static float cameraYaw;

    @Override
    public void onDisable() {
        mc.options.setPerspective(Perspective.FIRST_PERSON);
        super.onDisable();
    }
}
