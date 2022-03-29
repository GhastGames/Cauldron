package net.ghastgames.cauldron.pluginkit;

import lombok.Data;

@Data
public class GlobalSettings {
    private int maxPlayers = 0;
    private String serverFullMessage = "";
    private String serverFullBypassPermission = "";
    private boolean buildRestricted = false;
    private String buildPermission = "";
    private String buildPermissionDeniedMessage = "";
}
