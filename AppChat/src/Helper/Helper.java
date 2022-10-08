/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.ImageIcon;

/**
 *
 * @author Trong Quynh
 */
public class Helper {

    public static ImageIcon resizeImage(ImageIcon icon, int w, int h) {
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);
        return icon;
    }

    public static boolean isFileAPicure(File file) {
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];
        return type.equals("image") ? true : false;
    }

    public static String getCurrentTime() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        String abbreviations = "PM";
        int hour = localTime.getHour() + 1;
        if (hour >= 1 && hour <= 12) {
            abbreviations = "AM";
        }
        String currentTime = localTime.format(formatter);
        return currentTime;
    }
    
    public static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        int date = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        String result = date + "/" + month + "/" + year;
        return result;
    }
}
