package com.example.nikita.androidhomework;

import java.util.Comparator;

public class Character {
    public String name;
    public String description;
    public int id;

    Character(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public static Comparator<Character> CharNameComparator = new Comparator<Character>() {

        public int compare(Character c1, Character c2) {
            String CharName1 = c1.name.toUpperCase();
            String CharName2 = c2.name.toUpperCase();
            return CharName1.compareTo(CharName2);
        }};

    public static Comparator<Character> CharIDComparator = new Comparator<Character>() {

        public int compare(Character c1, Character c2) {

            int CharID1 = c1.id;
            int CharID2 = c2.id;
            return CharID1-CharID2;
        }};
}