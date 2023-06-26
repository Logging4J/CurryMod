package club.l4j.currymod.feature.impl.hackimpl.visual;

import club.l4j.currymod.feature.core.Hack;
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
