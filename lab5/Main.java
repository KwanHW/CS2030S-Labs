import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main (String[] args) {
        Roster roster = new Roster("AY2021");
        Scanner sc = new Scanner(System.in);
        int entries = sc.nextInt(); // Each entry has 4 parameters
        sc.nextLine(); // Clears the buffer
        ArrayList<String> line = new ArrayList<>();
    
        // Functions required to populate Roster
        Function<ArrayList<String>,Assessment> makeAs = x-> new Assessment(x.get(2),x.get(3));
        Function<ArrayList<String>,Module> makeMod = x-> new Module(x.get(1)).put(makeAs.apply(x));
        Function<ArrayList<String>,Student> makeStud = x-> new Student(x.get(0)).put(makeMod.apply(x));
        Runnable putStud = () -> {roster.put(makeStud.apply(line));};
        Runnable putMod = () -> {roster.get(line.get(0)).map(x -> x.put(makeMod.apply(line)));};
        Consumer<Module> putAs = x -> {x.put(makeAs.apply(line));};
        
        for (int i=0;i < entries ;i++ ) {
            line.clear();
            for (int j=0; j<4;j++) {
                line.add(sc.next());
            }
            roster.get(line.get(0))
                .ifPresentOrElse(x -> x.get(line.get(1))
                        .ifPresentOrElse(putAs,putMod), putStud);
        }
        
        while (sc.hasNext()) {
            line.clear();
            for (int i=0; i<3;i++) {
                line.add(sc.next());
            }
            Function<String[],String> getGrade = roster::getGrade;
            Consumer<String> queryGrade = System.out::println;
            queryGrade.accept(getGrade.apply(line.toArray(new String[0])));
        }
    }
}
