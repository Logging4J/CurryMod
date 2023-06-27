package club.l4j.currymod.feature.impl.hackimpl.movement;

import club.l4j.currymod.event.events.TickEvent;
import club.l4j.currymod.feature.core.Hack;
import com.google.common.collect.Streams;
import demo.knight.demobus.event.DemoListen;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;

import java.util.stream.Stream;

@Hack.Construct(name = "Parkour", description = "Jumps on edge of block", category = Hack.Category.MOVEMENT)
public class Parkour extends Hack {

    @DemoListen
    public void onTick(TickEvent e){
        if(nullCheck() || mc.player.isSneaking() || !mc.player.isOnGround()){return;}
        Box box = mc.player.getBoundingBox();
        Box loc =  box.offset(0, -0.5, 0).expand(-0.1, 0 , -0.1);
        Stream<VoxelShape> collisions = Streams.stream(mc.world.getCollisions(mc.player, loc));
        if(collisions.findAny().isPresent()){return;}
        mc.player.jump();
    }

}
