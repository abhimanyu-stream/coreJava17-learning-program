package  com.java17.interview.prepartion;


import java.util.*;
import java.util.stream.Collectors;

class PrintElementsOfArrayAccordingToItsFrequency {

    public static void main(String[] args) {


        //int[] arr = {2, 2, 5, 8, 8,8,3, 3, 3, 3, 3};

        Integer[] arr = {10, 20, 10, 10, 20, 30, 30, 30, 30, 0};
        List<Integer> list = Arrays.asList(arr);
        sortBasedOnFrequencyAndValue(list, arr);


    }

    public static void sortBasedOnFrequencyAndValue(List<Integer> list, Integer[] arr) {
        int n = arr.length;
        final Map<Integer, Integer> mapCount = new HashMap();
        final Map<Integer, Integer> mapIndex = new HashMap();

        for (int i = 0; i < n; i++) {
            if (mapCount.containsKey(arr[i])) {
                mapCount.put(arr[i], mapCount.get(arr[i]) + 1);
            } else {
                mapCount.put(arr[i], 1); // Map to capture Count of elements
                mapIndex.put(arr[i], i); // Map to capture 1st occurrence of elements
            }
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                int freq1 = mapCount.get(n1);
                int freq2 = mapCount.get(n2);
                if (freq1 != freq2) {
                    return freq2 - freq1;
                } else {
                    return mapIndex.get(n1) - mapIndex.get(n2);
                }
            }
        });
        System.out.println(list);
    }
}

    //Output [30, 30, 30, 30, 10, 10, 10, 20, 20, 0]


    //code that works for any array size and becoz your code is not working .not properly swaping ,when we add next number that has highest frequency …..if i am wrong please correct me..Thank you…..

/***
 * public class frequency {
 *         // Driver Code public static void main(String[] args) { int[] a = {10, 20, 10, 10, 20, 30, 30, 30, 30, 40,40,40,40,40,40};
 *         int n = a.length;
 *         int MAX = n;
 *         int[][] arr = new int[MAX][2];
 *         int[][] brr = new int[MAX][2]; int k = 0, temp, count; for (int i = 0; i < n; i++) {
 *             arr[i][0] = a[i];
 *             arr[i][1] = 0;
 *         } // Unique elements and its frequency are stored in another array
 * for (int i = 0; i < n; i++) {
 *             if (arr[i][1] == 1)
 *                 continue;
 *             count = 1;
 *             for (int j = i + 1; j < n; j++) {
 *                 if (arr[i][0] == arr[j][0]) {
 *                     arr[j][1] = 1;
 *                     count++;
 *                 }
 *             }
 *             brr[k][0] = arr[i][0];
 *             brr[k][1] = count;
 *             k++;
 *         }
 *         n = k; //Store the array and its frequency in sorted form
 * for (int i = 0; i < n – 1; i++) {
 *             temp = brr[i][1];
 *             for (int j = i + 1; j < n; j++) {
 *                 if (temp < brr[j][1]) {
 *                     temp = brr[j][1];
 *                     brr[j][1] = brr[i][1];
 *                     brr[i][1] = temp; temp = brr[j][0];
 *                     brr[j][0] = brr[i][0];
 *                     brr[i][0] = temp; temp=brr[i][1];
 *                 }
 *             }
 *         }
 * for (int i = 0; i < n; i++) {
 *             while (brr[i][1] != 0) {
 *                 System.out.print(brr[i][0] + " ");
 *                 brr[i][1]–;
 *             }
 *         }
 *     } }
 *
 *         }
 * }
 * */