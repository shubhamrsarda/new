package com.company;

import Streams.ExampleData;
import Streams.Person;
import StreamsAndLambda.Products;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaSnippet {

    public static void main(String[] args) {

//        personStream();
//
//        productStream();

        int num = 12345;

        String.valueOf(num).chars().map(Character::getNumericValue).sum();

        String str = "asjhdubdishowbjrierieubdb";

        Map<String,Long> map = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(s -> s,LinkedHashMap::new, Collectors.counting()));

        System.out.println(map);

       List<String > duplicate = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter( p -> p.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(duplicate);


         String s = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
                .entrySet()
                .stream()
                .filter( p -> p.getValue() == 1)
                 .skip(1)
                 .findFirst().get().getKey();


        System.out.println(s);

        int[] numbers = {12, 4, 5, 6,78 ,87, 11};

       Integer largest = Arrays.stream(numbers).boxed()
                .sorted(Comparator.reverseOrder())
               .skip(2)
                .findFirst().get();

        System.out.println(largest);


        String[] stringArray = {"gdshdhsdi","djdu","dudbiebnkwunxks"};

        String longString = Arrays.stream(stringArray)
                .reduce((s1, s2) -> s1.length()> s2.length() ? s1 : s2)
                .get();

        System.out.println(longString);

        Integer[] arr = {12, 4, 5, 6,78 ,87, 11};

       List<String> startsWith1 = Arrays.stream(arr)
                .map( n -> n + "")
                .filter(n -> n.startsWith("1"))
                .collect(Collectors.toList());

        System.out.println(startsWith1);


        // join method

       List<String> NUMBERS = Arrays.asList("1","2","5","7");

      String joinedString = String.join("-",NUMBERS);

        System.out.println(joinedString);

        IntStream.rangeClosed(1,10).skip(1).limit(8).forEach(System.out::print);



























    }

    private static void productStream() {

        List<Products> PRODUCT_LIST = ExampleData.getProductsList();

        PRODUCT_LIST.stream()
                .collect(Collectors.groupingBy(Products::getCategory,Collectors.counting()));
    }

    private static void personStream() {

        List<Person> personList = ExampleData.getPrrsonList();

        List<String> names = personList.stream()
                .filter(p -> p.getAge() > 30)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Persons with Age More than 30 : "+names);

        Double avgAge = personList.stream().mapToInt(Person::getAge).average().getAsDouble();

        System.out.println("Average Age of persons without Rounding : " + avgAge);
        System.out.println("Average Age of persons : " + Math.round(avgAge));


        List<Person> namesSortedByAge = personList.stream()
                .sorted(
                        Comparator.comparing(Person::getAge).reversed()
                ).collect(Collectors.toList());

        System.out.println("Person Sorted By Age : " + namesSortedByAge.subList(0,4));

        Map<String,Long> map =  personList.stream()
                .collect(
                        Collectors.groupingBy(Person::getCountry, Collectors.counting())
                );
        System.out.println(" persons by country : "+map);


       Map<String ,List<Person>> map1 = personList.stream()
                .collect(Collectors.groupingBy(Person::getCountry));

        System.out.println(map1);
    }
}
