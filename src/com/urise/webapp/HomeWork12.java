package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork12 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 4, 2, 6, 2, 9, 4, 8, 1, 3, 9};
        System.out.println("\n" + minValue(numbers));
        System.out.println(oddOrEven(Arrays.stream(numbers).collect(ArrayList::new, ArrayList::add, ArrayList::addAll)));
    }

    private static int minValue(int[] values) {
        Arrays.stream(values).distinct().sorted().forEach(System.out::print);
        return Arrays.stream(values).distinct().sorted().reduce(Integer.MAX_VALUE, Integer::min);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::valueOf).sum();
        return integers.stream().filter((item) -> (sum == 0) == (item % 2 == 0)).collect(Collectors.toList());
    }
}
