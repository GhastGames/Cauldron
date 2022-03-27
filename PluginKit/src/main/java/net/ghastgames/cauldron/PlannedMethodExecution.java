package net.ghastgames.cauldron;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class PlannedMethodExecution {
    private PluginKit.HookEvent event;
    private Method method;
}
