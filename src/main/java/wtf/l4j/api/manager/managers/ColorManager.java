package wtf.l4j.api.manager.managers;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter @Setter
public class ColorManager {

    private int red, green, blue;

    public int getRGBA(){
        return new Color(red, green, blue, 255).getRGB();
    }

    public Color getColor(){
        return new Color(red, green, blue, 255);
    }

}
