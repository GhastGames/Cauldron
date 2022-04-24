package net.ghastgames.cauldron.uikit.ui;

public class UserInterface {
    String title;
    int slots;

    public UserInterface(String title, int rows) {
        this.title = title;
        if(rows > 6) {
            throw new IllegalArgumentException("Too many rows");
        }
        this.slots = rows * 9; // rows * columns
    }


}
