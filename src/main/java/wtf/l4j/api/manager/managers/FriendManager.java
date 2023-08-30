package wtf.l4j.api.manager.managers;

import lombok.Getter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
public class FriendManager {

    private ArrayList<UUID> friends;

    public void init(){
        friends = new ArrayList<>();
    }

    public void addFriend(UUID uuid){
        friends.add(uuid);
    }

    public void removeFriends(UUID uuid){
        friends.remove(uuid);
    }
}
