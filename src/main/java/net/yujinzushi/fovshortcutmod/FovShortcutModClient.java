package net.yujinzushi.fovshortcutmod;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class FovShortcutModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBinding fov_inc = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fov-shortcut-mod.fov_inc", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PAGE_UP, "key.category.first.test"));
        KeyBinding fov_dec = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fov-shortcut-mod.fov_dec", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PAGE_DOWN, "key.category.first.test"));
        KeyBinding fov_reset = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fov-shortcut-mod.fov_reset", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_HOME, "key.category.first.test"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (fov_inc.wasPressed()) {
                assert client.player != null;
                client.options.fov += 0.5F;
                client.player.sendMessage(new LiteralText("FOV: " + client.options.fov), true);
            }

            while (fov_dec.wasPressed()) {
                assert client.player != null;
                client.options.fov -= 0.5F;
                client.player.sendMessage(new LiteralText("FOV: " + client.options.fov), true);
            }

            if (fov_reset.wasPressed()) {
                assert client.player != null;
                client.options.fov = 70F;
                client.player.sendMessage(new LiteralText("FOV: " + client.options.fov), true);
            }
        });
    }
}
