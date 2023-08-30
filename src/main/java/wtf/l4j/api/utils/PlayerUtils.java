package wtf.l4j.api.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.experimental.UtilityClass;
import net.minecraft.entity.Entity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@UtilityClass
public class PlayerUtils implements IGlobals{

    public String getUUIDFromName(String name) {
        try{
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            String id = jsonObject.get("id").getAsString();
            reader.close();
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public float[] getRotationToEntity(Entity target) {
        double deltaX = target.getX() - mc.player.getX();
        double deltaZ = target.getZ() - mc.player.getZ();
        double deltaY = target.getY() - 3.5 + target.getEyeHeight(target.getPose()) - mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose());
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
        float yaw = (float) (Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90);
        float pitch = (float) -Math.toDegrees(Math.atan2(deltaY, distance));
        return new float[]{yaw, pitch};
    }


}
