/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.main.JLiner;

/**
 *
 * @author samm
 */
public class FileLocation {
    
    private final String file;
    private final String filePackage;

    public FileLocation(String file, String filePackage) {
        this.file = file;
        this.filePackage = filePackage;
    }

    public String getFile() {
        return file;
    }

    public String getFilePackage() {
        return filePackage;
    }
    
    
    
    
    
}
