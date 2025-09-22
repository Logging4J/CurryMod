package dev.l4j.currymod.util;

import lombok.experimental.UtilityClass;
import net.minecraft.util.Identifier;

@UtilityClass
public class ResourceBank {

    public final Identifier SPECIAL_SOY_GROUP_CAPE = of("textures/cape/soycape.png");
    public final Identifier HARBINGER_CAPE = of("textures/cape/harbinger.png");
    public final Identifier JEW_SKIN = of("textures/skins/jew.png");
    public final Identifier NAZI_CLOUDS = of("textures/enviorment/nazi_clouds.png");
    public final Identifier TUDOU_SUN = of("textures/enviorment/tudou_sun.png");
    public final Identifier TUDOU_MOON = of("textures/enviorment/tudou_moon.png");
    public final Identifier SPLASHES = of("texts/splashes.txt");
    public final Identifier TITLE = of("textures/gui/title.png");
    public final Identifier LOGO = of("icon.png");
    public final Identifier DESCRIPTION_POINT = of("textures/description_point.png");
    public final Identifier FRECKY_THE_NIGGER = of("textures/cape/freckythenigger.png");
    public final Identifier TOTENKOPH = of("textures/cape/totenkoph.png");
    public final Identifier LONG_NOSE = of("textures/cape/longnose.png");
    public final Identifier PROF_FLOYDE = of("textures/cape/proffloyde.png");
    public final Identifier NICKEL_PINCHER = of("textures/cape/nickelpincher.png");
    public final Identifier BASKETBALL_PERSON = of("textures/entity/enderman/enderman.png");
    public final Identifier BASKETBALL_PERSON_EYES = of("textures/entity/enderman/enderman_eyes.png");


    public Identifier of(String path) {
        return Identifier.of("currymod", path);
    }
}
