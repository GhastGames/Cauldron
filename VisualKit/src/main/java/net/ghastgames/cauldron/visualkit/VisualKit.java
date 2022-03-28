package net.ghastgames.cauldron.visualkit;

public class VisualKit {
    private VisualKit instance;

    private VisualKit() {}

    public VisualKit getInstance() {
        if(instance == null) {
            instance = new VisualKit();
        }
        return instance;
    }
}
