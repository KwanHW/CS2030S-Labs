import java.util.List;

public class Candle extends Thing {
    public Candle() {
        super(List.of("Candle flickers.","Candle is getting shorter.","Candle is about to burn out.","Candle has burned out."), 0);
    }

    public Candle(int state) {
        super(List.of("Candle flickers.","Candle is getting shorter.","Candle is about to burn out.","Candle has burned out."),state);
    }
}

