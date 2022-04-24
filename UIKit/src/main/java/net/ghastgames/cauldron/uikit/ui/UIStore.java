package net.ghastgames.cauldron.uikit.ui;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class UIStore {
    @Getter
    private static HashMap<UUID, UserInterface> store = new HashMap<>();
}
