import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static <T> Predicate<T> removeDuplicate(final Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static int findDuplicate(final ArrayList<Boy> boys, final Boy boy) {
        int count = -1;
        for (Boy person : boys) {
            if (person.getName().equals(boy.getName())) {
                count++;
            }
        }
        return count;
    }


    public static void main(final String[] args) {
        final ArrayList<Boy> boys = new ArrayList<>() {{
            add(new Boy("Николай", 68));
            add(new Boy("Пётр", 53));
            add(new Boy("Василий", 25));
            add(new Boy("Михаил", 19));
            add(new Boy("Алексей", 6));
            add(new Boy("Николай", 86));
            add(new Boy("Пётр", 35));
            add(new Boy("Михаил", 111));
            add(new Boy("Алексей", 22));
            add(new Boy("Михаил", 1));
            add(new Boy("Яков", 30));
        }};

        List<Boy> adultBoys = boys.stream()
                .filter(x -> (x.getAge() > 17))
                .collect(Collectors.toList());

        List<Boy> filteredBoys = adultBoys.stream()
                .filter(removeDuplicate(Boy::getName))
                .sorted(Comparator.comparing(Boy::getName))
                .limit(4)
                .collect(Collectors.toList());

        Map<String, Integer> result = new HashMap<>();
        for (Boy boy : filteredBoys) {
            result.put(boy.getName(), findDuplicate(boys, boy));
        }

        System.out.println(result);
    }
}
