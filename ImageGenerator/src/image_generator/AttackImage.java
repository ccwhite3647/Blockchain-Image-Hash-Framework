/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_generator;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Cameron
 */
public class AttackImage {
    
    public static Mat copy(Mat image, double angle){
        Mat result = image;
        
        return result;
    }
    
    public static Mat flip(Mat image, int value){
        Mat result = new Mat(image.rows(), image.cols(), image.type());
        
        Core.flip(image, result, value);
        return result;
    }
    
    public static Mat gaus(Mat image, double value){
        Mat result = new Mat(image.rows(), image.cols(), image.type());
        
        Imgproc.GaussianBlur(image, result, new Size(value,value), 0);
        
        return result;
    }
    
    //Zooms the image
    public static Mat crop(Mat image, int value){
        Mat result = new Mat(image.rows(), image.cols(), image.type());
        
        int temp = 10 - value;
        int offset = (image.width() * value/10 )/2;
        int cropSize = image.width() * temp/10;
        
        Imgproc.resize(image, result, result.size(), 2, 2, Imgproc.INTER_LINEAR);
        
        Rect crop = new Rect(
                offset, 
                offset,
                
                cropSize, 
                cropSize);
        
        result = result.submat(crop);
        
        return result;
    }
    
    //Rotates the image
    public static Mat rotate(Mat image, double angle){
        Mat result = image;
        
        Point centre = new Point(image.width() / 2, image.height() / 2);
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(centre, angle, 1.0);
        
        Imgproc.warpAffine(image, result, rotationMatrix, new Size(image.cols(), image.rows()));
        
        return result;
    }
    
}
