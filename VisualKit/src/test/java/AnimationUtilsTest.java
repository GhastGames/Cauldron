import net.ghastgames.cauldron.visualkit.AnimationUtils;

public class AnimationUtilsTest {

    public static void main(String[] args) {
        String text = "Hello!";
        System.out.print(AnimationUtils.markCharacter("<a>", "</a>", text, 2));
    }
}
