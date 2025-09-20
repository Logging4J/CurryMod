package dev.l4j.currymod.listener;

import de.florianmichael.dietrichevents2.CancellableEvent;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public interface ItemStackTooltipListener {
    void onItemStackToolTip(ItemStackTooltipEvent event);

    @Getter
    class ItemStackTooltipEvent extends CancellableEvent<ItemStackTooltipListener> {

        public static final int ID = 6;

        private final ItemStack itemStack;
        private List<Text> list;

        public ItemStackTooltipEvent(ItemStack itemStack, List<Text> list) {
            this.itemStack = itemStack;
            this.list = list;
        }

        public void appendStart(Text text) {
            copyIfImmutable();
            int index = list.isEmpty() ? 0 : 1;
            list.add(index, text);
        }

        public void appendEnd(Text text) {
            copyIfImmutable();
            list.add(text);
        }

        public void append(int index, Text text) {
            copyIfImmutable();
            list.add(index, text);
        }

        public void set(int index, Text text) {
            copyIfImmutable();
            list.set(index, text);
        }

        private void copyIfImmutable() {
            if (List.of().getClass().getSuperclass().isInstance(list)) {
                list = new ObjectArrayList<>(list);
            }
        }

        @Override
        public void call(ItemStackTooltipListener listener) {
            listener.onItemStackToolTip(this);
        }
    }
}

