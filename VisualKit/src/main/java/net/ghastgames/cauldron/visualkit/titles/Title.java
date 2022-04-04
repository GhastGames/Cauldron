package net.ghastgames.cauldron.visualkit.titles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Title {
    String title;
    String subtitle;
    int fadeIn;
    int fadeOut;
    int stay;
}
