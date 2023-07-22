package club.l4j.currymod.core.manager;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter @Setter
public class ColorManager {

    private int red, green, blue, alpha;

    public int getRGBA(){
        return new Color(red, green, blue, alpha).getRGB();
    }

    public Color getColor(){
        return new Color(red, green, blue, alpha);
    }
}
