package week7;

/**
 * ArenaBattleSimulator demonstrates polymorphic invocation over mixed Defendable combatants.
 */
public class ArenaBattleSimulator {

    /**
     * Resolves defensive actions polymorphically across any Defendable implementers.
     *
     * @param combatants array of Defendable combatants
     */
    public static void resolveDefense(Defendable[] combatants) {
        if (combatants == null) {
            return;
        }

        for (Defendable combatant : combatants) {
            if (combatant != null) {
                System.out.println(combatant.defend());
            }
        }
    }
}
