package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

import static com.google.common.base.Preconditions.checkNotNull;

public class Main {
    public static void main(String[] args) {

        Optional guavaOptional = Optional.of("test");
        System.out.println(guavaOptional.get());

        Object object = new Object();
        checkNotNull(object);

        try {
            throw new IOException();
        } catch (Throwable t) {

            Throwable rootCause = Throwables.getRootCause(t);
            List<Throwable> causalChain = Throwables.getCausalChain(t);
            String stackTraceAsString = Throwables.getStackTraceAsString(t);
        }

        Lists.newArrayList();
        List<Student> students = new ArrayList<>();

        // concatenated包括元素 1, 2, 3, 4, 5, 6
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));


        List<String> languages = Lists.newArrayList("java", "python", "javascript", "java");
        HashMultiset<String> multiset = HashMultiset.create(languages);

        multiset.add("python");
        multiset.addAll(Lists.newArrayList("go", "java", "c"));

        multiset.elementSet().forEach(x -> {
            System.out.println(x + " 的出现次数： " + multiset.count(x));
        });
    }

    public static class Student implements Comparable<Student> {
        private String name;
        private int age;

        @Override
        public int compareTo(Student o) {
            return ComparisonChain.start()
                    .compare(this.name, o.name)
                    .compare(this.age, o.age)
                    .result();
        }
    }

}