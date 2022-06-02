package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork12 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 4, 2, 6, 2, 9, 4, 8, 1, 3, 1, 9};
        System.out.println(minValue(numbers));
        System.out.println(oddOrEven(Arrays.stream(numbers).collect(ArrayList::new, ArrayList::add, ArrayList::addAll)));
    }

    private static int minValue(int[] values) {
        List<Integer> list = Arrays.stream(values).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return list.stream().min(Integer::compare).get();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(item -> Integer.valueOf(item)).sum();
        System.out.println(sum);
        if (sum % 2 == 0) {
            return integers.stream().filter(item -> item % 2 == 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(item -> item % 2 != 0).collect(Collectors.toList());
        }
    }
}
