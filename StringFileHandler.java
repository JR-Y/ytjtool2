/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.*;
import java.io.File;
import java.util.ArrayList;

public class StringFileHandler {

    public boolean write(String path, String text) throws IOException {
        BufferedWriter outputStream = null;
        try {            
            outputStream = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));                       
            outputStream.write(text);
        } catch (IOException e) {
            System.out.println(e);
            return false;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return true;
    }

    public String read(String path) throws IOException {
        String data = null;
        String line;
        BufferedReader inputstream = null;
        try {
            inputstream = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8);
            while ((line = inputstream.readLine()) != null) {
                if (data == null) {
                    data = new String(line);
                } else {
                    data = data + "\n" + new String(line);
                }
            }
        } catch (IOException e) {
        } finally {
            if (inputstream != null) {

                inputstream.close();
            }
        }
        return data;
    }
    public boolean writeCSV(String path, ArrayList<String> text) throws IOException {
        BufferedWriter outputStream = null;
        try {            
            outputStream = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8);                       
            for(int i = 0;i<text.size();i++){
            outputStream.write(text.get(i)); 
            outputStream.newLine();            
            }
            
        } catch (IOException e) {
            System.out.println(e);
            return false;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return true;
    }
}
//"ISO-8859-1" Charset.forName("UTF-8")