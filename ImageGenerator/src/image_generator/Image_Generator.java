 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_generator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Cameron
 */
public class Image_Generator {

    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    
    static final String imageToEdit = "peppers_color"; //Image name
    static final String imageType = ".tif";   //Image's file extension (eg. .png, .jpg)
    static final String rootFolder = "image_edits/" + imageToEdit;
    
    public static void main(String[] args) {
        
        
        
        FileGenerator.newFolder(rootFolder); //Creates a folder for the new images
        Mat image = Imgcodecs.imread("images/" + imageToEdit + imageType);
        
        FileGenerator.newImage(rootFolder, "original", image);
        System.out.println("Starting to generate images for " + imageToEdit);
        
        copyDirectory("images", rootFolder + "/other");
        makeFlip();
        makeRotate(9, 10);
        makeCrop(9, 1);
        makeGaus(9, 5);
        
        System.out.println("Completed Generating Images");
    }
    
    static void copyDirectory(String location, String destination){
        System.out.println("Copying Image Directory");
        File directory = new File(location);
        File copy = new File(destination);
        
        try {
            FileUtils.copyDirectory(directory, copy);
        } catch (IOException ex) {
            Logger.getLogger(Image_Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void makeFlip(){
        System.out.println("Flipping Image");
        
        String folder = rootFolder + "/flip";
        FileGenerator.newFolder(folder);
        
        Mat image = Imgcodecs.imread("images/" + imageToEdit + imageType);
        
        FileGenerator.newImage(folder, "flip_horizontal", AttackImage.flip(image, 1));
        FileGenerator.newImage(folder, "flip_vertical", AttackImage.flip(image, 0));
        FileGenerator.newImage(folder, "flip_both", AttackImage.flip(image, -1));
        
    }
    
    static void makeGaus(int cycles, int startValue){
        System.out.println("Blurring Image");
        
        String folder = rootFolder + "/gaus";
        FileGenerator.newFolder(folder);
        
        Mat image = Imgcodecs.imread("images/" + imageToEdit + imageType);
        for(int i = 0; i < cycles; i++){
            
            Mat imgEdit;
            imgEdit = AttackImage.gaus(image, startValue);
            
            FileGenerator.newImage(folder, "gaus_" + (i + 1), imgEdit);
            
            startValue += 10;
        }
    }
    
    static void makeCrop(int cycles, int startValue){
        System.out.println("Cropping Image");
        
        String folder = rootFolder + "/crop";
        FileGenerator.newFolder(folder);
        
        for(int i = 0; i < cycles; i++){
            Mat image = Imgcodecs.imread("images/" + imageToEdit + imageType);
            Mat imgEdit;
            imgEdit = AttackImage.crop(image, startValue);
            
            FileGenerator.newImage(folder, "crop_" + (i + 1), imgEdit);
            
            startValue += 1;
        }
    }
    
    static void makeRotate(int cycles, double startValue){
        System.out.println("Rotating Image");
        
        String folder = rootFolder + "/rotate";
        FileGenerator.newFolder(folder);
        
        for(int i = 0; i < cycles; i++){
            Mat image = Imgcodecs.imread("images/" + imageToEdit + imageType);
            Mat imgEdit = new Mat();
            imgEdit = AttackImage.rotate(image, startValue);
            
            FileGenerator.newImage(folder, "rotate_" + (i + 1), image);
            
            startValue += 10;
        }
    }
}
