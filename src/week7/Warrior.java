package week7;

/**
 * Warrior extends GameCharacter and implements both Attackable (overloaded) and Defendable.
 */
public class Warrior extends GameCharacter implements Attackable, Defendable {
    private final String name;

    public Warrior(String name) {
        super();
        this.name = (name != null) ? name.trim() : "Warrior";
    }

    public String getName() {
        return name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        if (weaponName == null || weaponName.trim().isEmpty()) {
            return attack();
        }
        String cleanWeapon = weaponName.trim();
        char first = Character.toLowerCase(cleanWeapon.charAt(0));
        String article = (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') ? "an " : "a ";
        return name + " strikes with " + article + cleanWeapon;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}
