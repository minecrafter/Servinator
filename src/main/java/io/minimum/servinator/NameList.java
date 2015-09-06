package io.minimum.servinator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Random;

public class NameList {
    private static NameList INSTANCE;

    public static NameList instance() {
        if (INSTANCE == null)
            INSTANCE = new NameList();
        return INSTANCE;
    }

    private final List<String> suffixes;
    private final ListMultimap<String, String> prefixes = ArrayListMultimap.create();
    private final Random random = new Random();

    private NameList() {
        suffixes = ImmutableList.copyOf(getLines("suffixes.txt"));
        prefixes.putAll("generic-english", getLines("lists/generic-english.txt"));
        prefixes.putAll("latin-derived", getLines("lists/latin-derived.txt"));
    }

    public String generateName(String kind) {
        Preconditions.checkNotNull(kind, "kind");
        Preconditions.checkArgument(prefixes.containsKey(kind), "no such kind found");

        String prefix = prefixes.get(kind).get(random.nextInt(prefixes.get(kind).size()));
        String suffix = suffixes.get(random.nextInt(suffixes.size()));

        return prefix + suffix;
    }

    private List<String> getLines(String file) {
        try (Reader reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(file))) {
            return CharStreams.readLines(reader);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}
