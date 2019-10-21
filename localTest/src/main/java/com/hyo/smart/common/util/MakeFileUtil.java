package com.hyo.smart.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class MakeFileUtil {

	// java.io.File Class.
	private static void createFileUsingFileClass() throws IOException {
          File file = new File("E://Temp//testFile1.txt");
  
          // Create the file
          if (file.createNewFile()){
            System.out.println("File is created.");
          }else{
            System.out.println("File already exists.");
          }
           
          // Write Content
          FileWriter writer = new FileWriter(file);
          writer.write("Test data");
          writer.close();
    }
	
	// java.io.FileOutputStream Class.
    private static void createFileUsingFileOutputStreamClass() throws IOException {
        String data = "Test data";
        FileOutputStream out = new FileOutputStream("E://Temp//testFile2.txt");
        out.write(data.getBytes());
        out.close();
    }
 
    // java.nio.file.Files Class.
    private static void createFileIn_NIO()  throws IOException {
        String data = "1st line\n"
        		    + "2nd line";
        Files.write(Paths.get("E://Temp//testFile3.txt"), data.getBytes());  
        
        //or         
        
        List<String> lines = Arrays.asList("1st line", "2nd line");         
        Files.write(Paths.get("E://Temp//testFile4.txt"),
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
    }
		
    
    public static void main(String[] args) throws IOException {
    	
        createFileUsingFileClass();
        createFileUsingFileOutputStreamClass();
        createFileIn_NIO();
        
    }
}
