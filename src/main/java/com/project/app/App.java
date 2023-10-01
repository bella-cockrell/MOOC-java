package com.project.app;

import com.project.app.HelloRunnable;
import com.project.app.HelloThread;
import com.project.app.SimpleThreads;

import java.util.stream.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.lang.Math;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {   
        // (new Thread(new HelloRunnable())).start();   
        // new HelloThread().start();
        // SimpleThreads.execute();

        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //.stream() can only be done on Lists (Collection interface), not arrays.
        List<Integer> dataAsList = Arrays.asList(data);
        // System.out.println("dataAsList: " + dataAsList);
        
        //map
        dataAsList.stream()
        .map(Math::sqrt)
        .peek(number -> System.out.println("Map Sqrt: " + String.valueOf(number)));

        //collect
        Stream.of(data)
        .collect(Collectors.toList());
        // System.out.println("Collect toList: " + data);

        //filter
        dataAsList.stream()
        .filter(number -> number % 2 == 0)
        .peek(number -> System.out.println("Filter: " + String.valueOf(number)));

        //findFirst
        Integer result = dataAsList.stream().filter(number -> number == 11).findFirst().orElse(null);
        // System.out.println("findFirst: " + result); //null

        //toArray
        Integer[] result2 = dataAsList.stream().toArray(Integer[]::new);
        // System.out.println("toArray: " + result2);

        //flatMap
        //It takes objects from different collections in this stream A and puts all objects in a new Stream B.
        List<List<String>> namesNested = Arrays.asList( 
            Arrays.asList("Jeff", "Bezos"), 
            Arrays.asList("Bill", "Gates"), 
            Arrays.asList("Mark", "Zuckerberg"));

        List<String> employees = namesNested.stream().flatMap(list -> list.stream()).toList(); //don't need .collect here?
        // System.out.println("namesFlatStreamed: " + employees);
        
        //peek --doesn't return, mainly used for debugging
        //intermediate operation unlike forEach
        List<Integer> result3 = dataAsList.stream()
        .map(number -> number -= 2)
        // .peek(number -> System.out.println("peek: " + number))
        .collect(Collectors.toList());

        //skip, limit
        //good for infinite streams, as intermediate operations are lazily loaded
        //Short-circuiting operations allow computations on infinite streams to complete in finite time
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = infiniteStream
        .skip(3)
        .limit(5)
        // .peek(System.out::println)
        .collect(Collectors.toList()); //only performed when terminal operation is complete


        //sorted
        //Note that short-circuiting will not be applied for sorted().
        //this is because sorted needs to be applied before returning
        List<String> sortedEmployees = employees.stream()
            .sorted((employee1, employee2) -> employee1.compareTo(employee2))
            // .peek(System.out::println)
            .collect(Collectors.toList());


        //min and max
        //returns option so need to .OrElseThrow it

        Integer[] listOfNumbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        try {
            Integer result5 = Stream.of(listOfNumbers)
            .min((number1, number2) -> number1 - number2)
            .orElseThrow(Exception::new);

            Integer result6 = Stream.of(listOfNumbers)
            .max((number1, number2) -> number1 - number2)
            .orElseThrow(Exception::new);

            // System.out.println(result5);
            // System.out.println(result6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
        
        //distinct
        List<Integer> numbers = Arrays.asList(1, 4, 2, 6, 9, 4, 4, 3, 3, 1);
        List<Integer> distinctNumbers = numbers.stream().distinct().collect(Collectors.toList());
        // System.out.println(distinctNumbers);

        //anyMatch, allMatch, nonMatch
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        boolean allEven = intList.stream().allMatch(number -> number % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(number -> number % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(number -> number % 3 == 0);

        System.out.println(allEven); //returns false as soon as it comes across 5
        System.out.println(oneEven); // returns true once it comes across the first element
        System.out.println(noneMultipleOfThree); //returns false as soon as it finds 6
    }
}