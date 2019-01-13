/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_generator;

import java.io.File;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Cameron
 */
public class FileGenerator {
    
    public static void newFolder(String location){
        new File(location).mkdirs();
    }
    
    public static void newImage(String location, String name, Mat image){
        Imgcodecs.imwrite(location + "/" + name + ".png", image);
    }
    
}
