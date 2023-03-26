import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartyBehrouz {

    public static int goodSets(List<Integer> integers, int devs) {
        int indexBehrooz;
        int num = 0;
        if (integers.size() == 1) {
            return 0;
        } else if (integers.size() == 2) {
            List<Integer> indexes = findBehrouzIndex(integers, findMax(integers));
            /* we only have 2 elements in the array so worst case is both are max
             so we give the 1st max to behrouz and there will not be any changes
             becouse 2 maxes in an array of 2 elements means the array is all equall
             so if one of the elements is divisiable by devs the other also is and
             if one isnt the other is also not so we return 0
             and also if we have one max the number is stored inside index.get(0) so
             it wont make any diffrence afterall
             */
            indexBehrooz = indexes.get(0);
            if (integers.get(1) % devs == 0 && indexBehrooz == 0) {
                System.out.println(integers);
                return 1;
            } else if (integers.get(0) % devs == 0 && indexBehrooz == 1) {
                System.out.println(integers);
                return 1;
            }
            return 0;
        }

        num += goodSets(integers.subList(0, integers.size() / 2), devs);
        num += goodSets(integers.subList(integers.size() / 2, integers.size()), devs);

        //every segmentaition that l and r are on diffrent sides of the middle
        for (int r = integers.size() - 1; r >= (int) (integers.size() / 2); r--) {
            for (int l = 0; l < integers.size() / 2; l++) {
                if (checkSum(integers.subList(l, r + 1), devs)) {
                    System.out.println(integers.subList(l, r + 1));
//                    System.out.println(indexBehrooz-l);
                    num++;
                }
            }
        }
        return num;
    }

    private static boolean checkSum(List<Integer> subList, int devs) {
        int sum;
        boolean b = false;
        List<Integer> indexes = findBehrouzIndex(subList, findMax(subList));
        for (int j = 0; j < indexes.size(); j++) {
            sum = 0;
            int indexBehrouz = indexes.get(j);
            for (int i = 0; i < subList.size(); i++) {
                if (i == indexBehrouz) continue;
                sum += subList.get(i);
            }
            b = b || (sum % devs == 0);
        }
        return b;
    }

    private static List<Integer> findBehrouzIndex(List<Integer> integers, Integer max) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(max)) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static Integer findMax(List<Integer> integers) {
        Integer max = integers.get(0);
        for (int i = 1; i < integers.size(); i++) {
            if (integers.get(i) > max) {
                max = integers.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int devs = scanner.nextInt();
//        for (int i = 0; i < n; i++) {
//            integers.add(scanner.nextInt());
//        }
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(4);
        integers.add(4);
        integers.add(7);
        integers.add(4);
        System.out.println("The list:"+integers);
        System.out.println(goodSets(integers, 3));

    }
}