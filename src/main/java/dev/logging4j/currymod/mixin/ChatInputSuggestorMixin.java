package dev.logging4j.currymod.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import dev.logging4j.currymod.CurryMod;
import dev.logging4j.currymod.command.CommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.CompletableFuture;

@Mixin(ChatInputSuggestor.class)
public abstract class ChatInputSuggestorMixin {

    @Shadow @Nullable private ParseResults<FabricClientCommandSource> parse;

    @Shadow @Final TextFieldWidget textField;

    @Shadow @Nullable private CompletableFuture<Suggestions> pendingSuggestions;

    @Shadow @Nullable private ChatInputSuggestor.SuggestionWindow window;

    @Shadow public abstract void show(boolean narrateFirstSuggestion);

    @Shadow boolean completingSuggestions;

    @Inject(
            method = "refresh",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/brigadier/StringReader;canRead()Z",
                    remap = false
            ),
            cancellable = true
    )
    public void refreshINVOKE$canRead(CallbackInfo ci, @Local StringReader reader) {
        String prefix = CurryMod.getCommandManager().getPrefix();
        if (reader.canRead(prefix.length()) && reader.getString().startsWith(prefix, reader.getCursor())) {
            reader.setCursor(reader.getCursor() + prefix.length());

            CommandDispatcher<FabricClientCommandSource> commandDispatcher = CommandManager.DISPATCHER;
            if (this.parse == null) this.parse = commandDispatcher.parse(reader, CommandManager.COMMAND_SOURCE);

            int cursor = textField.getCursor();
            if (cursor >= 1 && (this.window == null || !this.completingSuggestions)) {
                this.pendingSuggestions = commandDispatcher.getCompletionSuggestions(this.parse, cursor);
                this.pendingSuggestions.thenRun(() -> {
                    if (this.pendingSuggestions.isDone()) {
                        this.show(false);
                    }
                });
            }
            ci.cancel();
        }
    }
}
