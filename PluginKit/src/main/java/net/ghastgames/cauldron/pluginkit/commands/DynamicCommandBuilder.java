package net.ghastgames.cauldron.pluginkit.commands;

import lombok.Getter;
import net.ghastgames.cauldron.pluginkit.PluginKit;
import org.bukkit.entity.Player;

public class DynamicCommandBuilder {
    @Getter
    private final String command;
    @Getter
    private String permission;
    @Getter
    private String noPermissionMessage;
    @Getter
    private String notPlayerMessage;
    @Getter
    private boolean playerOnly;
    @Getter
    private Condition condition;
    @Getter
    private CauldronCommandExecutor executor;

    public DynamicCommandBuilder(String command) {
        this.command = command;
    }

    public DynamicCommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public DynamicCommandBuilder setNoPermissionMessage(String noPermissionMessage) {
        this.noPermissionMessage = noPermissionMessage;
        return this;
    }

    public DynamicCommandBuilder setPlayerOnly(boolean playerOnly) {
        this.playerOnly = playerOnly;
        return this;
    }

    public DynamicCommandBuilder setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public DynamicCommandBuilder setNotPlayerMessage(String message) {
        this.notPlayerMessage = message;
        return this;
    }

    public DynamicCommandBuilder setExecutor(CauldronCommandExecutor commandExecutor) {
        this.executor = commandExecutor;
        return this;
    }

    public void register() {
        PluginKit.registerBukkitCommand(command, (commandSender, command, s, args) -> {
            if(playerOnly && !(commandSender instanceof Player)) {
                commandSender.sendMessage(notPlayerMessage);
                return false;
            }
            if(permission != null) {
                if(!commandSender.hasPermission(permission)) {
                    commandSender.sendMessage(noPermissionMessage);
                    return false;
                }
            }
            if(condition != null) {
                ConditionResponse response = condition.execute(commandSender, args);
                if(!response.isOk()) {
                    commandSender.sendMessage(response.getMessage() == null ? "??cThis command can't be executed right now." : response.getMessage());
                    return false;
                }
            }
            if(executor != null) {
                return executor.execute(new CommandExecution(commandSender, args));
            }
            return true;
        });
    }
}
