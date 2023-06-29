package club.l4j.currymod.util;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class UniColor {

    @Getter @Setter
    private int red, green, blue, alpha;

    public Color getRGBAAsColor(){
        return new Color(red, green, blue, alpha);
    }

    public int getRGBA(){
        return new Color(red, green, blue, alpha).getRGB();
    }
}
