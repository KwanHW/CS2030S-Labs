import java.util.List;

public class Troll extends Thing {
    private static final List<String> EVENTS = List.of(
          "Troll lurks in the shadows.",
          "Troll is getting hungry.",
          "Troll is VERY hungry.",
          "Troll is SUPER HUNGRY and is about to ATTACK!",
          "Troll attacks!");
    public Troll() {
        super(List.of(
          "Troll lurks in the shadows.",
          "Troll is getting hungry.",
          "Troll is VERY hungry.",
          "Troll is SUPER HUNGRY and is about to ATTACK!",
          "Troll attacks!"),0);
    }

    public Troll(int state) {
        super(List.of(
          "Troll lurks in the shadows.",
          "Troll is getting hungry.",
          "Troll is VERY hungry.",
          "Troll is SUPER HUNGRY and is about to ATTACK!",
          "Troll attacks!"),state);
    }
}

