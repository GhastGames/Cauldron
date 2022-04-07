package net.ghastgames.cauldron;

public abstract class CauldronInstance {
    public abstract void start(ServerStartup startupDetails);
    public abstract void stop();
}
