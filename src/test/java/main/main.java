package main;

import java.io.*;

public class main {

    public static void main(String[] args) {
        StringBuffer sBuffer = new StringBuffer("test");
        sBuffer.append(" String Buffer");
        System.out.println(sBuffer);
        System.out.println(sBuffer.length());
        System.out.println(sBuffer.append(new StringBuffer(" Hassan")));
        sBuffer.reverse();
        System.out.println(sBuffer);

        System.out.println("==============================");

        StringBuilder sBuilder = new StringBuilder("example");
        sBuilder.append(" String Builder");
        System.out.println(sBuilder);
        System.out.println(sBuilder.length());
        System.out.println(sBuilder.append(new StringBuilder(" Hassan")));
        sBuilder.reverse();
        System.out.println(sBuilder);

        System.out.println("==============================");

        try
        {
            String fileName = "file-modification.txt";
            String projectPath = System.getProperty("user.dir");;
            String fullPath = projectPath + "/assets/txt-files/" + fileName;

            //Sample 01: Open the FileWriter, Buffered Writer
            FileWriter fw = new
                    FileWriter(fullPath);
            BufferedWriter WriteFileBuffer = new
                    BufferedWriter(fw);
            //Sample 02: Write Some Text to File
            // Using Buffered Writer)
            WriteFileBuffer.write("Hellow World!!");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("My Name is:");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Hassan");
            WriteFileBuffer.newLine();
            WriteFileBuffer.write("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            //Sample 03: Close both the Writers
            WriteFileBuffer.close();
            //Sample 04: Open the Readers Now
            FileReader fr = new
                    FileReader(fullPath);
            BufferedReader ReadFileBuffer = new
                    BufferedReader(fr);
            //Sample 05: Read the text Written
            // using BufferedWriter
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());
            System.out.println(ReadFileBuffer.readLine());
            //Sample 06: Close the Readers
            ReadFileBuffer.close();
        } catch (IOException Ex)
        {
            System.out.println(Ex.getMessage());
        }

        System.out.println("==============================");
    }
}
