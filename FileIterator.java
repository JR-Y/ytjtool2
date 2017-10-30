/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToolV2_1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author John-Robert
 */
public class FileIterator {

    public ArrayList<YFile> iterator(String folderPath) throws IOException {
        ArrayList<YFile> yList = new ArrayList();
        File dir = new File(folderPath);
        StringFileHandler hand = new StringFileHandler();
        File[] directoryListing = dir.listFiles();        
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (!child.getName().equals("listausALL.txt")) {
                    if (!child.getName().equals("listaus.txt")) {
                        YFile uusi = new YFile(child.getName(),hand.read(child.toString()));
                        yList.add(uusi);
                    }
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
        return yList;
    }
}
