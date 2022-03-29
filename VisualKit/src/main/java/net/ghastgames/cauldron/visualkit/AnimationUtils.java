package net.ghastgames.cauldron.visualkit;

public class AnimationUtils {

    public static String markCharacter(String markWith, String continueWith, String text, int position) {
        return text.substring(0, position) + markWith + text.charAt(position) + continueWith + text.substring(position + 1);
    }
}
