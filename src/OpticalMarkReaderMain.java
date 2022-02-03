import FileIO.PDFHelper;
import Filters.DisplayInfoFilter;
import core.DImage;
import processing.core.PImage;

import javax.swing.*;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

public class OpticalMarkReaderMain {
    ArrayList <String> answers;

    public static void main(String[] args) {
        String pathToPdf = fileChooser();
        System.out.println("Loading pdf at " + pathToPdf);

        ArrayList<PImage> pages = PDFHelper.getPImagesFromPdf("assets/omrtest.pdf");
        System.out.println(pages.size());

        for (PImage page: pages) {
            DImage in = new DImage(page);
            DisplayInfoFilter filter = new DisplayInfoFilter();
            filter.processImage(in);
        }

       /*
       Your code here to...
       (1).  Load the pdf
       (2).  Loop over its pages
       (3).  Create a DImage from each page and process its pixels
       (4).  Output 2 csv files
        */



    }

    private static String fileChooser() {
        String userDirLocation = System.getProperty("user.dir");
        File userDir = new File(userDirLocation);
        JFileChooser fc = new JFileChooser(userDir);
        int returnVal = fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        return file.getAbsolutePath();
    }

    public void saveAnswer(){
        try {
            PrintWriter out = new PrintWriter(new FileWriter("saveAnswers.txt"));
            for (String answer : answers) {
                out.println(answer);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

