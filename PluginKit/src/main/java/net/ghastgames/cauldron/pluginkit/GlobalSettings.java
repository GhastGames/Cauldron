package net.ghastgames.cauldron.pluginkit;

import lombok.Data;

@Data
public class GlobalSettings {
    private int maxPlayers;
    private String serverFullMessage;
    private String serverFullBypassPermission;
    private boolean buildRestricted;
    private String buildPermission;
    private String buildPermissionDeniedMessage;
}
