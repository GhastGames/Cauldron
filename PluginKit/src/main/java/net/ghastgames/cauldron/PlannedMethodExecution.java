package net.ghastgames.cauldron;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class PlannedMethodExecution {
    private PluginKit.HookEvent event;
    private Method method;
}
