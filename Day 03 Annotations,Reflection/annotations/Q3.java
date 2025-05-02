package annotations;

import java.util.ArrayList;
import java.util.List;

public class Q3 {

    @SuppressWarnings("unchecked")
    public void addElements() {
        List rawList = new ArrayList();

        rawList.add("Apple");
        rawList.add("Banana");

        List<String> typedList = rawList;

        for (String fruit : typedList) {
            System.out.println(fruit);
        }
    }

    public static void main(String[] args) {
        Q3 suppressor = new Q3();
        suppressor.addElements();
    }
}
