/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.main.JLiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author samm
 */
public class JLiner {

    private final JLinerConfig config;

    public JLiner() {
        this.config = new JLinerConfig();
    }

    public JLiner(JLinerConfig config) {
        this.config = config;
    }
    
    private void log(String msg) {
        if( config.isShowingLogMessages() ) {
            System.out.println(msg);
        }
    }
    
    private void error(String msg) {
        if( config.isShowingErrorMessages() ) {
            System.err.println(msg);
        }
    }
    
    
    
    

    public final String fetch(String key) {

        log("Fetching: " + key);

        String contents = "";
        
        FileLocation[] customLocations;
        
        if (config.getCustomLocations().length>0) {
            customLocations = config.getCustomLocations();
        }
        else {
            customLocations = new FileLocation[]{
                new FileLocation("JLinerContent.xml", "")
            };
        }
            
        for (FileLocation location : customLocations) {
            
            contents = readInFile(location.getFile(), location.getFilePackage());
            
            if( contents!=null ) {
            
                String openingTag = makeOpeningTag(key);
                String closingTag = makeClosingTag(key);


                log("Looking for tag " + openingTag + " in " + location.getFilePackage() + location.getFile());

                Integer startPos = contents.indexOf(openingTag) + openingTag.length();
                Integer endPos = contents.indexOf(closingTag);

                if( (startPos > 0) && (endPos > 0) ) {
                    log("Value found in file: " + location.getFilePackage() + location.getFile());

                    String gg = contents.substring( startPos , endPos ).trim();

                    gg = gg.replaceAll("\\n|\\r"," ");
                    gg = gg.replaceAll("(\\s)\\s*","$1");

                    return gg;
                }
            }
            
            
        }
        
        error( "Key '" + key + "' not found in any file");


        return config.isReturnNull() ? null : "";
    }
    
    private static String makeOpeningTag(String key) {
        StringBuilder openingTagBuilder = new StringBuilder("<");
        openingTagBuilder.append( key );
        openingTagBuilder.append( ">" );
        
        return openingTagBuilder.toString();
    }
    
    
    private static String makeClosingTag(String key) {
        StringBuilder closingTagBuilder = new StringBuilder("</");
        closingTagBuilder.append( key );
        closingTagBuilder.append( ">" );
        
        return closingTagBuilder.toString();
    }
    
    
    
    private String readInFile(String file, String filePackage) {

        String originalFilePackage = filePackage;

        String contents = null;

        try {

            filePackage = filePackage.replaceAll("[.]", "/");

            if (!filePackage.startsWith("/")) {
                filePackage = "/" + filePackage;
            }

            if (!filePackage.endsWith("/")) {
                filePackage = filePackage + "/";
            }

            InputStream in = JLiner.class.getResourceAsStream(filePackage + file);

            contents = getStringFromInputStream(in);

            in.close();

        } catch (IOException e) {
            contents = "";
            error("Could not close input stream");
        } catch (NullPointerException e) {
            error("Could not find custom location xml file " + filePackage + file + " on classpath");
        }

        return contents;
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
