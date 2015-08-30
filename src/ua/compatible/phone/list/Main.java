package ua.compatible.phone.list;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<List<String>> phoneLists = inputData();

        long startTime = System.currentTimeMillis();

        for (List<String> phoneList : phoneLists) {
            System.out.println(isCompatible(phoneList));
        }

        System.out.println("TIME = " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static List<List<String>> inputData(){
        System.out.println("Enter something here : ");
        Scanner scanIn = new Scanner(System.in);

        int testNum = scanIn.nextInt();
        List<List<String>> phoneLists = new ArrayList<List<String>>(testNum);

        for (int i = 0; i < testNum; i++) {
            System.out.println("Enter number of phones : ");
            int numOfPhones = scanIn.nextInt();

            List<String> list = new ArrayList<String>(numOfPhones);
            for (int j = 0; j < numOfPhones; j++) {
                System.out.println("->");
                String number = scanIn.next();

                list.add(number);
            }

            phoneLists.add(list);
        }

        scanIn.close();

        System.out.println(phoneLists);

        return phoneLists;
    }

    private static String isCompatible(List<String> list) {
        ascSort(list);

        return checkCompatibility(list);
    }

    private static String checkCompatibility(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String head = list.get(i);
            List<String> tail = list.subList(i + 1, list.size());

            int k = Collections.binarySearch(tail, head, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.startsWith(o2)) return 0;
                    else return -1;
                }
            });

            if (k >= 0) {
                return "NO";
            }
        }

        return "YES";
    }

    private static void ascSort(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) return -1;
                else if (o1.length() > o2.length()) return 1;
                else return o1.compareTo(o2);
            }
        });
    }
}
