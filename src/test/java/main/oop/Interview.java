package main.oop;

import java.util.Scanner;

public class Interview {
    public static void main(String[] args) {
        int count = 0;
        System.out.println("Enter a sentence :");
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();

        for (int i=0 ; i<sentence.length(); i++){
            char ch = sentence.charAt(i);
            if(ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u'||ch == ' '){
                count ++;
            }
        }
        System.out.println("Number of vowels in the given sentence is "+count);
    }

//    this is the simplest answer
//    you can elaborate that you specify the input in scanner
//    and loop the String
//    get each char in the string
//    and if the char equals to vowels
//    count++ to add the count
}
