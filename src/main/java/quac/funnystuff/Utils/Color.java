package quac.funnystuff.Utils;

public enum Color {
    BLACK("&0"),
    DARK_BLUE("&1"),
    DARK_GREEN("&2"),
    DARK_AQUA("&3"),
    DARK_RED("&4"),
    PURPLE("&5"),
    GOLD("&6"),
    LIGHT_GRAY("&7"),
    GRAY("&8"),
    BLUE("&9"),
    LIME("&a"),
    AQUA("&b"),
    RED("&c"),
    MAGENTA("&d"),
    YELLOW("&e"),
    WHITE("&f");

    public final String code;
    Color(String a) {
        code=a;
    }

    public static String rainbowText(String a, boolean bold) {
        Color[] colors = {RED, GOLD, YELLOW, LIME, DARK_GREEN, BLUE, PURPLE};
        int index = 0;

        StringBuilder builder = new StringBuilder();

        for (char c : a.toCharArray()) {
            String append;
            if(c == ' ') {
                append = ""+c;
            } else {
                if(bold) {
                    append =  colors[index].code + "&l" + c;
                } else {
                    append = colors[index].code + c;
                }

                index++;
                if(index==colors.length) {
                    index = 0;
                }
            }

            builder.append(append);
        }

        return builder.toString();
    }
}
