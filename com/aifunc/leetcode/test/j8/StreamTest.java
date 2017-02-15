package com.aifunc.leetcode.test.j8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by alex on 1/11/17.
 */
public class StreamTest {
  public static void main(String[] args) {
    //limit, sorted
    Random random = new Random();
    random.ints().limit(10).forEach(System.out::println);
    //sorted
    random.ints().limit(10).sorted().forEach(System.out::println);

    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//get list of unique squares
    List<Integer> squaresList = numbers.stream().
      map(i -> i*i).distinct().collect(Collectors.toList()); //to set

    List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    //get count of empty string
    long count = strings.stream().filter(string -> string.isEmpty()).count();

    //parallel processing

    //get count of empty string
    long pcount = strings.parallelStream().filter(string -> string.isEmpty()).count();

    List<String> filtered = strings.stream().
      filter(string -> !string.isEmpty()).collect(Collectors.toList());
    String mergedString = strings.stream().filter(string -> !string.isEmpty()).
      collect(Collectors.joining(", "));

    //Statistics
    IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());


  }



}
