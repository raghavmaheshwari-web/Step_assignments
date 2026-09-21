package week7;

/**
 * Abstract GameCharacter class assigning a unique immutable characterId.
 */
public abstract class GameCharacter {
    private static int characterCounter = 0;
    private final String characterId;

    public GameCharacter() {
        characterCounter++;
        this.characterId = "CHAR-" + characterCounter;
    }

    public String getCharacterId() {
        return characterId;
    }

    public static int getCharacterCounter() {
        return characterCounter;
    }

    public static void resetCharacterCounter() {
        characterCounter = 0;
    }

    public abstract String getSpecialMove();
}
