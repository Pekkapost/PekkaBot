package Commands.Ad.Utility;

import Manager.SQLManager;

public class PingAd {
    // Parse a shorthand ad result string: '5'=CS5, '1'=CS10, '2'=CS20, 'g'=Green key, 'r'=Red key
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
