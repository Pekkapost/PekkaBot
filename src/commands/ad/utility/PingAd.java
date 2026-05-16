package commands.ad.utility;

import manager.SQLManager;

/**
 * Parses an ad-result shorthand string and persists it to the Ad table.
 *
 * The body is a stream of shorthand characters:
 * <ul>
 *   <li>{@code 5} → CS5 (5-Chronos-Stone ad)</li>
 *   <li>{@code 1} → CS10</li>
 *   <li>{@code 2} → CS20</li>
 *   <li>{@code g} → Green key</li>
 *   <li>{@code r} → Red key</li>
 * </ul>
 * Called from {@link discord.GuildMessageRespond} after regex-matching a
 * body that contains only those characters and whitespace.
 */
public class PingAd {
    public static String check(String id, String message) {
        String output = "";
        for (char c : message.toCharArray()) {
            if (c == '5') { SQLManager.updateAd(id, "CS5");    output += "5 ";  }
            if (c == '1') { SQLManager.updateAd(id, "CS10");   output += "10 "; }
            if (c == '2') { SQLManager.updateAd(id, "CS20");   output += "20 "; }
            if (c == 'g') { SQLManager.updateAd(id, "Green");  output += "G ";  }
            if (c == 'r') { SQLManager.updateAd(id, "Red");    output += "R ";  }
        }
        return output;
    }
}
