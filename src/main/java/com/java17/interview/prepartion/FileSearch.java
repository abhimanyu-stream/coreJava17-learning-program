package com.java17.interview.prepartion;

import java.io.File;
class FileSearchThread extends Thread {

    //File name and File Directory for every thread
    private final String fileName;
    private final File directory;
    //Constuctor
    public FileSearchThread(String fileName, File directory) {
        this.fileName = fileName;
        this.directory = directory;
    }

    //Run method that calls search method for searching file.
    @Override
    public void run() {
        boolean result = searchFile(fileName, directory);
        if(result) {
            System.out.println("File Found" + directory.toString());
        }
    }
    private boolean searchFile(String fileName, File directory) {
        //Searching in the directory
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursively search in nested directories
                    searchFile(fileName, file);
                } else if (file.getName().equalsIgnoreCase(fileName)) {
                    System.out.println("File Found" + file.getAbsolutePath());
                    return true;
                }
            }
        }
        return false;
    }
}
public class FileSearch {
    public static void main(String[] args) {
        String fileNameToSearch = "XYZ.txt";
        // Create a separate thread for each drive and folder
        for (char drive = 'C'; drive <= 'D'; drive++) {
            String rootPath = drive + ":\\";
            File[] directories = new File(rootPath).listFiles(File::isDirectory);
            if (directories != null) {
                for (File directory : directories) {
                    new FileSearchThread(fileNameToSearch, directory).start();
                }
            }
        }
    }
}