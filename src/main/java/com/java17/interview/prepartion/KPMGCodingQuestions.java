package com.java17.interview.prepartion;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KPMGCodingQuestions {

    // 1. Reverse words in a sentence
    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
        return result.toString().trim();
    }

    // 2. First non-repeating character
    public static char firstNonRepeating(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : map.keySet()) {
            if (map.get(c) == 1) return c;
        }
        return '_';
    }

    // 3. Check palindrome
    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 4. Move all zeros to end
    public static void moveZeros(int[] arr) {
        int index = 0;
        for (int num : arr) {
            if (num != 0) arr[index++] = num;
        }
        while (index < arr.length) arr[index++] = 0;
    }

    // 5. Second largest element
    public static int secondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }
        return second;
    }

    // 6. Remove duplicates from string
    public static String removeDuplicates(String str) {
        Set<Character> set = new LinkedHashSet<>();
        for (char c : str.toCharArray()) set.add(c);
        StringBuilder result = new StringBuilder();
        for (char c : set) result.append(c);
        return result.toString();
    }

    // 7. Count frequency of characters
    public static void charFrequency(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println("charFrequency" +map);

        /**
         * Example
         *
         * For string: "aab"
         *
         * Iteration 1: 'a'
         * getOrDefault('a', 0) → 0
         * 0 + 1 = 1
         * map → {a=1}
         * Iteration 2: 'a'
         * getOrDefault('a', 0) → 1
         * 1 + 1 = 2
         * map → {a=2}
         * Iteration 3: 'b'
         * getOrDefault('b', 0) → 0
         * 0 + 1 = 1
         * map → {a=2, b=1}
         */

        LinkedHashMap<String, Long> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("collect"+collect);

        LinkedHashMap<Character, Long> collect2 =
                str.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        ));

        System.out.println("collect2"+collect2);

        /**
         * Why this works
         * str.chars() → gives IntStream
         * .mapToObj(c -> (char) c) → converts each int to Character
         * groupingBy(...) → now groups Character instead of String
         * LinkedHashMap::new → preserves insertion order
         *
         *
         */

        LinkedHashMap<Character, Long> collect3 =
                str.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(
                                c -> c,
                                LinkedHashMap::new,
                                Collectors.counting()
                        ));

        System.out.println("collect3"+collect3);

    }

    // 8. Anagram check
    public static boolean isAnagram(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // 9. Fibonacci (iterative)
    public static void fibonacci(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println();
    }

    // 10. Missing number in array
    public static int missingNumber(int[] arr, int n) {
        int sum = n * (n + 1) / 2;
        int actual = 0;
        for (int num : arr) actual += num;
        return sum - actual;
    }

    // 11. Longest substring without repeating chars
    public static int longestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, max = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(right));
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    // 12. Sort 0s,1s,2s (Dutch National Flag)
    public static void sort012(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                int temp = arr[low]; arr[low] = arr[mid]; arr[mid] = temp;
                low++; mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                int temp = arr[mid]; arr[mid] = arr[high]; arr[high] = temp;
                high--;
            }
        }
    }

    // 13. Intersection of two arrays
    public static Set<Integer> intersection(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int num : a) set.add(num);
        for (int num : b) {
            if (set.contains(num)) result.add(num);
        }
        return result;
    }

    // 14. Valid parentheses
    public static boolean validParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) return false;
            }
        }
        return stack.isEmpty();
    }

    // 15. Rotate array by k
    public static void rotate(int[] arr, int k) {
        k = k % arr.length;
        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    // Main method to test
    public static void main(String[] args) {

        System.out.println(reverseWords("I love Java"));
        System.out.println(firstNonRepeating("swiss"));
        System.out.println(isPalindrome("madam"));

        int[] arr = {0,1,0,3,12};
        moveZeros(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {10,5,20,8};
        System.out.println(secondLargest(arr2));

        System.out.println(removeDuplicates("programming"));
        charFrequency("hellooo");

        System.out.println(isAnagram("listen", "silent"));

        fibonacci(5);

        int[] miss = {1,2,4,5};
        System.out.println("missingNumber"+missingNumber(miss, 5));

        System.out.println("longestSubstring"+longestSubstring("abcabcbb"));

        int[] dnf = {0,2,1,2,0};
        sort012(dnf);
        System.out.println(Arrays.toString(dnf));

        int[] a = {1,2,3};
        int[] b = {2,3,4};
        System.out.println(intersection(a,b));

        System.out.println(validParentheses("(){}[]"));

        int[] rot = {1,2,3,4,5};
        rotate(rot, 2);
        System.out.println(Arrays.toString(rot));
    }
}