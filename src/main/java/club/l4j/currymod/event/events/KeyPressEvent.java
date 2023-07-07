package club.l4j.currymod.event.events;

import club.l4j.currymod.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyPressEvent extends Event {

    private int key;
    private int action;

}
