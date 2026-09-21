package week5;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Problems 1 & 2: Membership Field Reach Checker & Subclass Reach
 *
 * Implements exact Java visibility rules for access modifiers across different
 * accessor contexts, including the subtle cross-package protected rule
 * (accessible via subclass reference type, but denied via parent reference type).
 */
public class AccessChecker {

    /**
     * Determines whether field access is "ALLOWED" or "DENIED" according to Java language rules.
     *
     * @param fieldModifier "private", "default", "protected", or "public"
     * @param accessorContext "SAME_CLASS", "SAME_PACKAGE", "DIFFERENT_PACKAGE",
     *                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
     * @return "ALLOWED" or "DENIED"
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.trim().toLowerCase();
        String ctx = accessorContext.trim().toUpperCase();

        switch (mod) {
            case "public":
                return "ALLOWED";

            case "private":
                return ctx.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return (ctx.equals("SAME_CLASS") || ctx.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

            case "protected":
                if (ctx.equals("SAME_CLASS") || ctx.equals("SAME_PACKAGE") ||
                    ctx.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }
                // Protected access from a different package via a parent-type reference or non-subclass is denied
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    /**
     * Summarizes access attempts grouped by modifier in a fixed order.
     *
     * @param attempts 2D array of {fieldModifier, accessorContext}
     * @return formatted summary string
     */
    public static String summarizeByModifier(String[][] attempts) {
        if (attempts == null) {
            return "";
        }

        // Initialize counts for standard Java modifiers in order
        String[] modifiers = {"private", "default", "protected", "public"};
        Map<String, int[]> stats = new LinkedHashMap<>();
        for (String m : modifiers) {
            stats.put(m, new int[]{0, 0}); // index 0: allowed, index 1: denied
        }

        for (String[] attempt : attempts) {
            if (attempt == null || attempt.length < 2) continue;
            String mod = attempt[0].toLowerCase();
            String ctx = attempt[1];

            String result = classifyAccess(mod, ctx);
            if (stats.containsKey(mod)) {
                if ("ALLOWED".equals(result)) {
                    stats.get(mod)[0]++;
                } else {
                    stats.get(mod)[1]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modifiers.length; i++) {
            String m = modifiers[i];
            int[] counts = stats.get(m);
            sb.append(m).append(": ").append(counts[0]).append(" allowed / ")
              .append(counts[1]).append(" denied");
            if (i < modifiers.length - 1) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }

    /**
     * Converts an underscore-separated accessor context string into Title Case words.
     * Example: "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE" -> "Subclass Different Package Own Type"
     *
     * @param accessorContext raw code string
     * @return human-readable title-cased description
     */
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] parts = accessorContext.trim().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                sb.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    sb.append(part.substring(1).toLowerCase());
                }
            }
            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
