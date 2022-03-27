package net.ghastgames.cauldron;

import net.ghastgames.cauldron.annotations.DisableHook;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class PluginKit {
    private static MinecraftPlugin minecraftPlugin;
    private static List<PlannedMethodExecution> hooks;

    enum HookEvent {
        ENABLE, DISABLE
    }

    public static void initPlugin(Class<? extends MinecraftPlugin> plugin) {
        try {
            minecraftPlugin = plugin.newInstance();
            for(Method method : plugin.getMethods()) {
                if(method.isAnnotationPresent(DisableHook.class)) {
                    hooks.add(new PlannedMethodExecution(HookEvent.DISABLE, method));
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
