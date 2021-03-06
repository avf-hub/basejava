package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork12 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 4, 2, 6, 2, 9, 4, 8, 1, 3, 9};
        System.out.println(minValue(numbers));
        System.out.println(oddOrEven(Arrays.stream(numbers).collect(ArrayList::new, ArrayList::add, ArrayList::addAll)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::valueOf).sum();
        return integers.stream().filter((item) -> (sum == 0) == (item % 2 == 0)).collect(Collectors.toList());
    }
}
