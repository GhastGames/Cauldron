package net.ghastgames.cauldron.pluginkit.events;

public interface CauldronEventHandler<T> {
    void execute(T event);
}
