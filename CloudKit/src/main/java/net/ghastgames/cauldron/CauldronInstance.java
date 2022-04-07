package net.ghastgames.cauldron;

public abstract class CauldronInstance {
    abstract void start(ServerStartup startupDetails);
    abstract void stop();
}
