/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

import java.io.*;
import javax.json.*;
import javax.json.JsonReader;
/**
 *
 * @author John-Robert
 */
public class StringToJson {
    
    public StringToJson(){               
    }
    
    public JsonObject convert(String s){
        JsonObject j;        
        try (JsonReader jsonReader = Json.createReader(new StringReader(s))) {
            j = jsonReader.readObject();
            System.out.println(j.toString());
        }catch(Exception e){
          System.out.println("Virhe: " + e);
            return null;
        }
      return j;
    }  
}