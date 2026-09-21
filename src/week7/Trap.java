package week7;

/**
 * Trap implements only Defendable with no relationship to GameCharacter.
 */
public class Trap implements Defendable {
    private final String trapType;

    public Trap(String trapType) {
        this.trapType = (trapType != null) ? trapType.trim() : "Unknown Trap";
    }

    public String getTrapType() {
        return trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}
