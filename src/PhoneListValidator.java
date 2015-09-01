import java.io.IOException;
import java.util.*;

public class PhoneListValidator {
    public static void main(String[] args) throws IOException {

        List<List<String>> phoneLists = inputData();

        for (List<String> phoneList : phoneLists) {
            System.out.println(isCompatible(phoneList));
        }
    }

    private static List<List<String>> inputData() throws IOException {
        Scanner in = new Scanner(System.in);

        int testNum = in.nextInt();
        List<List<String>> phoneLists = new ArrayList<List<String>>(testNum);

        for (int i = 0; i < testNum; i++) {
            int numOfPhones = in.nextInt();

            List<String> list = new ArrayList<String>(numOfPhones);
            for (int j = 0; j < numOfPhones; j++) {
                String number = in.next();
                list.add(number);
            }

            phoneLists.add(list);
        }

        in.close();

        return phoneLists;
    }

    private static String isCompatible(List<String> list) {
        Collections.sort(list);

        return checkCompatibility(list);
    }

    private static String checkCompatibility(List<String> list) {
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            String head = list.get(i);
            List<String> tail = list.subList(i + 1, list.size());

            int k = Collections.binarySearch(tail, head, new Comparator<String>() {
                @Override
                public int compare(String el, String key) {
                    if (el.startsWith(key)) return 0;
                    else return el.compareTo(key);
                }
            });

            if (k >= 0) {
                return "NO";
            }
        }

        return "YES";
    }
}