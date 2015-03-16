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
public class JLinerConfig {
    
    private Boolean returnNull;
    private Boolean showingLogMessages;
    private Boolean showingErrorMessages;
    private FileLocation[] customLocations;
    

    public JLinerConfig() {
        this.returnNull = false;
        this.showingLogMessages = false;
        this.showingErrorMessages = true;
        this.customLocations = new FileLocation[]{};
    }

    public Boolean isReturnNull() {
        return returnNull;
    }

    public void setReturnNull(Boolean returnNull) {
        this.returnNull = returnNull;
    }

    public Boolean isShowingLogMessages() {
        return showingLogMessages;
    }

    public void setShowingLogMessages(Boolean showingLogMessages) {
        this.showingLogMessages = showingLogMessages;
    }

    public Boolean isShowingErrorMessages() {
        return showingErrorMessages;
    }

    public void setShowingErrorMessages(Boolean showingErrorMessages) {
        this.showingErrorMessages = showingErrorMessages;
    }
    

    public FileLocation[] getCustomLocations() {
        return customLocations;
    }

    public void setCustomLocations(FileLocation[] customLocations) {
        this.customLocations = customLocations;
    }

    


    
    
    
    
    
    
    
}
