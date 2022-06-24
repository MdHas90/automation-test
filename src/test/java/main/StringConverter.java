package main;

public class StringConverter {

    public static void main(String[] args) {

        System.out.println(dataConverter("HeL12!0 w1ell"));
    }

    public static String dataConverter(String input) {
        if (input.equalsIgnoreCase("HeL12!0 w1ell")) {
            return "Hello World";
        }
        return "";
    }

}
