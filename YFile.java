/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

/**
 *
 * @author John-Robert
 */
public class YFile {
    private String fileName;
    private String fileCont;
    
    
    public YFile(String fileName,String fileCont){
        this.fileName = fileName;
        this.fileCont = fileCont;       
    }
    
    public String getFileName(){
        return fileName;
    }
    public String getFileCont(){
        return fileCont;
    }
    
}
