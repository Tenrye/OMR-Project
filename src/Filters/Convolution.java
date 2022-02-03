package Filters;
import Interfaces.PixelFilter;
import core.BoxS;
import core.DImage;
import processing.core.PApplet;

import javax.swing.*;
import java.util.ArrayList;

public class Convolution implements PixelFilter {
    private String response = JOptionPane.showInputDialog("How Big do you want the Kernel to be Odd numbers only");
    private int n = Integer.parseInt(response);
    private String responseX = JOptionPane.showInputDialog("Give me X");
    private String responseY = JOptionPane.showInputDialog("Give me Y");


    private double[][] outlineKernel =
            {
                    {-1, -1, -1},
                    {-1, 8, -1},
                    {-1, -1, -1}   };
    private double[][] preWittEdgeDetectionNxN = makeOutLineKernelnxn(n);

    @Override
    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] outputPixels = img.getBWPixelGrid();  // <-- overwrite these values
        double[][] inputKernel = preWittEdgeDetectionNxN;
        int kLength = inputKernel.length;
        int center = inputKernel.length/2;


        for (int r = center; r < pixels.length-kLength; r++) {
            for (int c = center; c < pixels[0].length-kLength; c++) {
                outputPixels[r][c] = loopThruPixel(r,c,pixels,n,inputKernel);
            }
        }

        img.setPixels(outputPixels);
         int x = Integer.parseInt(responseX);
         int y = Integer.parseInt(responseY);
        ArrayList<BoxS> boxList = makeNumBox(img,x,y);
        System.out.println(" you have " + boxList.size() + " box!");
        System.out.println("points are " + boxList.get(0).toString());
        return img;
    }
    public short loopThruPixel(int r, int c, short[][] pixels,int n, double[][] kernel){
        double weight = 0;
        double sum = 0;
        double val = 0;
        double tw = 0;
        for(int i = r; i < r+n; i++){
            for(int j = c; j < c+n; j++){
                val = pixels[i][j];

                weight = kernel[i-r][j-c];
                tw += weight;
                sum+=val*weight;


            }
        }
        if(tw != 0){
            sum = sum/tw;
        }

        if(sum  < 0){
            sum = 0;
        }
        if(sum > 255){
            sum = 255;
        }
        return (short)sum;
    }

    public double[][] makeOutLineKernelnxn(int n){
        double[][] outLineKernelNxN = new double[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                outLineKernelNxN[row][col] = -1.0;
            }
        }
        outLineKernelNxN[n/2][n/2] = (n * n) - 1;
        return outLineKernelNxN;
    }
    public ArrayList<BoxS> makeNumBox(DImage img, int getX, int getY){
        double[][] arr = new double[img.getHeight()][img.getWidth()];
        ArrayList<BoxS> boxList = new ArrayList<>();
        int startX = 45;
        int startY = 24;
        int dots = 0;
        int questions = 2;
        for (int z = 0; z < questions; z++) {
            for (int i = getY - startY; i < getY; i++) {
                for (int j = getX - startX; j < getX; j++) {
                    if (arr[i][j] == 0) {
                        dots++;
                    }
                }
            }
            if (dots > 10) {
                boxList.add(new BoxS(getX-startX,getY-startY,getX,getY));
            }
        }
        return boxList;
    }

    public void drawOverlay(PApplet window, DImage original, DImage filtered) {

    }
}

