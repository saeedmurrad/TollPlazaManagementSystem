package com.tollPlaza.utils;

import com.tollPlaza.entities.TollPlaza;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtility {

    public static void writeObjectToFile(Object serObj, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(AppConstants.appFolderPath + filePath + ".txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static TollPlaza readObjectFromFile(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(AppConstants.appFolderPath + filepath + ".txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            TollPlaza obj = (TollPlaza) objectIn.readObject();
            objectIn.close();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<TollPlaza> getAllTollPlazaFromFiles() {
        try {
            String[] namesList = {"BUREWALA", "HARAPPA", "SAHIWAL", "OKARA", "PATTOKI"};
            List<TollPlaza> tollPlazaList = new ArrayList<>();
            for (String name : namesList) {
                TollPlaza tollPlaza = readObjectFromFile(name);
                tollPlazaList.add(tollPlaza);
            }
            return tollPlazaList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createFilesForTollPlazas() {
        String[] namesList = {"BUREWALA", "HARAPPA", "SAHIWAL", "OKARA", "PATTOKI"};
        try {
            for (String name : namesList) {
                File myObj = new File(AppConstants.appFolderPath + name + ".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeDataToFilesForTollPlazas() {
        List<TollPlaza> tollPlazaList = new ArrayList<>();
        tollPlazaList.add(new TollPlaza("1", "BUREWALA", 7, 1, 175));
        tollPlazaList.add(new TollPlaza("2", "HARAPPA", 15, 0, 375));
        tollPlazaList.add(new TollPlaza("3", "SAHIWAL", 10, 2, 250));
        tollPlazaList.add(new TollPlaza("4", "OKARA", 20, 1, 500));
        tollPlazaList.add(new TollPlaza("5", "PATTOKI", 26, 3, 650));
        try {
            for (TollPlaza tollplaza : tollPlazaList) {
                writeObjectToFile(tollplaza, tollplaza.getName());
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
