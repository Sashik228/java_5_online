package ua.com.alevel.test;

import org.apache.commons.lang3.StringUtils;
public class Second {
    public void printMessage(String message){
        System.out.println("message = " + message);
        System.out.println("message = " + StringUtils.upperCase(message));
    }
}