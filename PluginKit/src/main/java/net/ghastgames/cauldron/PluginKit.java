package net.ghastgames.cauldron;

import java.util.HashMap;
import java.util.List;

public class PluginKit {
    private static MinecraftPlugin minecraftPlugin;
    private List<PlannedMethodExecution> hooks;

    enum HookEvent {
        ENABLE, DISABLE
    }

    public static void initPlugin(Class<? extends MinecraftPlugin> plugin) {
        try {
            minecraftPlugin = plugin.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
