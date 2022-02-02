package com.tollPlaza.main;

import com.tollPlaza.entities.TollPlaza;
import com.tollPlaza.utils.AppConstants;
import com.tollPlaza.utils.GeneralUtility;

import java.util.List;
import java.util.*;

public class TollPlazaApp {

    private static void printAsteriskLine() {
        for (int i = 0; i < 80; i++)
            System.out.print("*");
        System.out.println();
    }

    private static void printDashLine() {
        for (int i = 0; i < 80; i++)
            System.out.print("-");
        System.out.println();
    }

    public static void main(String[] args) {
        printAsteriskLine();
        System.out.println("TOLL PLAZA MANAGEMENT SYSTEM");
        Scanner sc = new Scanner(System.in);
        List<TollPlaza> tollPlazaList = GeneralUtility.getAllTollPlazaFromFiles();
        String option;
        boolean flagForParentLoop = true;
        boolean flagForChildLoop = true;
        while (flagForParentLoop) {
            printAsteriskLine();
            System.out.println("Application Menu");
            printAsteriskLine();
            System.out.println("Press 1 to see TollPlaza ID & NAME");
            System.out.println("Press 2 to search by Name");
            System.out.println("Press 3 to search by ID");
            System.out.println("Press 4 to exit");
            System.out.print("Enter your option: ");
            String num = sc.nextLine();
            switch (num) {
                case "1":
                    printAsteriskLine();
                    System.out.println("\t\t\t\t\t\t\t<----TOLL PLAZA LIST---->");
                    printDashLine();
                    System.out.printf("%7s %14s", "ID", "NAME");
                    System.out.println();
                    printDashLine();
                    for (TollPlaza tollplaza : tollPlazaList) {
                        System.out.format("%7s %14s", tollplaza.getId(), tollplaza.getName());
                        System.out.println();
                    }
                    printDashLine();
                    break;
                case "2":
                    System.out.print("Enter the Name: ");
                    String name = sc.nextLine();
                    boolean nameFound = false;
                    for (TollPlaza tollplaza : tollPlazaList) {
                        if (tollplaza.getName().equals(name)) {
                            nameFound = true;
                        }
                    }
                    if (nameFound) {
                        printAsteriskLine();
                        System.out.println("Toll Plaza '" + name + "' is selected...");
                        printAsteriskLine();
                        flagForChildLoop = true;
                        while (flagForChildLoop) {
                            System.out.println("Press P to add paying car");
                            System.out.println("Press N to add a non-paying car");
                            System.out.println("Press R to view report");
                            System.out.println("Press T to view complete report");
                            System.out.println("Press Z to Save to file and Exit");
                            System.out.print("Enter your option: ");
                            option = sc.nextLine();
                            switch (option) {
                                case "P":
                                    printAsteriskLine();
                                    int payedCars = 0;
                                    int amount = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getName().equals(name)) {
                                            payedCars = tollplaza.getPayingCarPassed();
                                            payedCars++;
                                            tollplaza.setPayingCarPassed(payedCars);
                                            amount = tollplaza.getAmtCollected();
                                            amount += 25;
                                            tollplaza.setAmtCollected(amount);
                                        }
                                    }
                                    System.out.println("Added paying car");
                                    printAsteriskLine();
                                    break;

                                case "N":
                                    printAsteriskLine();
                                    int notPayedCars = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getName().equals(name)) {
                                            notPayedCars = tollplaza.getNotPayingCar();
                                            notPayedCars++;
                                            tollplaza.setNotPayingCar(notPayedCars);
                                        }
                                    }
                                    System.out.println("Added a non-paying car");
                                    printAsteriskLine();
                                    break;
                                case "R":
                                    printAsteriskLine();
                                    System.out.println("\t\t\t\t\t\t\t<----View Report---->");
                                    printDashLine();
                                    System.out.printf("%7s %14s %16s %16s %20s", "ID", "NAME", "Cars Passed", "Not Paying", "Amount Collected");
                                    System.out.println();
                                    printDashLine();
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getName().equals(name)) {
                                            System.out.format("%7s %14s %14s %14s %20s", tollplaza.getId(), tollplaza.getName(), tollplaza.getPayingCarPassed(), tollplaza.getNotPayingCar(), tollplaza.getAmtCollected());
                                            System.out.println();
                                        }
                                    }
                                    printDashLine();
                                    printAsteriskLine();
                                    break;
                                case "T":
                                    printAsteriskLine();
                                    System.out.println("\t\t\t\t\t\t<----Complete Report---->");
                                    printDashLine();
                                    System.out.printf("%7s %14s %16s %16s %20s", "ID", "NAME", "Cars Passed", "Not Paying", "Amount Collected");
                                    System.out.println();
                                    printDashLine();
                                    int totalCarsPassed = 0;
                                    int totalNotPayedCars = 0;
                                    int totalAmountCollected = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        System.out.format("%7s %14s %14s %14s %20s", tollplaza.getId(), tollplaza.getName(), tollplaza.getPayingCarPassed(), tollplaza.getNotPayingCar(), tollplaza.getAmtCollected());
                                        System.out.println();
                                        totalCarsPassed += tollplaza.getPayingCarPassed();
                                        totalNotPayedCars += tollplaza.getNotPayingCar();
                                        totalAmountCollected += tollplaza.getAmtCollected();
                                    }
                                    printDashLine();
                                    System.out.printf("%21s %15s %14s %20s", "Total:", totalCarsPassed, totalNotPayedCars, totalAmountCollected);
                                    System.out.println();
                                    printDashLine();
                                    printAsteriskLine();
                                    break;
                                case "Z":
                                    String selectedTollPlazaName = "";
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getName().equals(name)) {
                                            selectedTollPlazaName = tollplaza.getName();
                                            GeneralUtility.writeObjectToFile(tollplaza, tollplaza.getName());
                                        }
                                    }
                                    printAsteriskLine();
                                    System.out.println("Save to file on following path " + AppConstants.appFolderPath + selectedTollPlazaName + ".txt");
                                    flagForChildLoop = false;
                                    break;
                                default:
                                    System.out.println("Invalid input!!");
                                    printAsteriskLine();
                            }
                        }
                    } else {
                        System.out.println("Toll Plaza '" + name + "' does not exist here!!!");
                    }
                case "3":

                    System.out.print("Enter the ID: ");
                    String id = sc.nextLine();
                    boolean idFound = false;
                    String selectedTollPlazaName = "";
                    for (TollPlaza tollplaza : tollPlazaList) {
                        if (tollplaza.getId().equals(id)) {
                            idFound = true;
                            selectedTollPlazaName = tollplaza.getName();
                        }
                    }

                    if (idFound) {
                        printAsteriskLine();
                        System.out.println("Toll Plaza '" + selectedTollPlazaName + "' is selected...");
                        printAsteriskLine();
                        flagForChildLoop = true;
                        while (flagForChildLoop) {
                            System.out.println("Press P to add paying car");
                            System.out.println("Press N to add a non-paying car");
                            System.out.println("Press R to view report");
                            System.out.println("Press T to view complete report");
                            System.out.println("Press Z to Save to file and Exit");
                            System.out.print("Enter your option: ");
                            option = sc.nextLine();
                            switch (option) {
                                case "P":
                                    printAsteriskLine();
                                    int payedCars = 0;
                                    int amount = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getId().equals(id)) {
                                            payedCars = tollplaza.getPayingCarPassed();
                                            payedCars++;
                                            tollplaza.setPayingCarPassed(payedCars);
                                            amount = tollplaza.getAmtCollected();
                                            amount += 25;
                                            tollplaza.setAmtCollected(amount);
                                        }
                                    }
                                    System.out.println("Added paying car");
                                    printAsteriskLine();
                                    break;
                                case "N":
                                    printAsteriskLine();
                                    int notPayedCars = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getId().equals(id)) {
                                            notPayedCars = tollplaza.getNotPayingCar();
                                            notPayedCars++;
                                            tollplaza.setNotPayingCar(notPayedCars);
                                        }
                                    }
                                    System.out.println("Added a non-paying car");
                                    printAsteriskLine();
                                    break;
                                case "R":
                                    printAsteriskLine();
                                    System.out.println("\t\t\t\t\t\t\t<----View Report---->");
                                    printDashLine();
                                    System.out.printf("%7s %14s %16s %16s %20s", "ID", "NAME", "Cars Passed", "Not Paying", "Amount Collected");
                                    System.out.println();
                                    printDashLine();
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getId().equals(id)) {
                                            System.out.format("%7s %14s %14s %14s %20s", tollplaza.getId(), tollplaza.getName(), tollplaza.getPayingCarPassed(), tollplaza.getNotPayingCar(), tollplaza.getAmtCollected());
                                            System.out.println();
                                        }
                                    }
                                    printDashLine();
                                    printAsteriskLine();
                                    break;
                                case "T":
                                    printAsteriskLine();
                                    System.out.println("\t\t\t\t\t\t<----Complete Report---->");
                                    printDashLine();
                                    System.out.printf("%7s %14s %16s %16s %20s", "ID", "NAME", "Cars Passed", "Not Paying", "Amount Collected");
                                    System.out.println();
                                    printDashLine();
                                    int totalCarsPassed = 0;
                                    int totalNotPayedCars = 0;
                                    int totalAmountCollected = 0;
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        System.out.format("%7s %14s %14s %14s %20s", tollplaza.getId(), tollplaza.getName(), tollplaza.getPayingCarPassed(), tollplaza.getNotPayingCar(), tollplaza.getAmtCollected());
                                        System.out.println();
                                        totalCarsPassed += tollplaza.getPayingCarPassed();
                                        totalNotPayedCars += tollplaza.getNotPayingCar();
                                        totalAmountCollected += tollplaza.getAmtCollected();
                                    }
                                    printDashLine();
                                    System.out.printf("%21s %15s %14s %20s", "Total:", totalCarsPassed, totalNotPayedCars, totalAmountCollected);
                                    System.out.println();
                                    printDashLine();
                                    printAsteriskLine();
                                    break;
                                case "Z":
                                    for (TollPlaza tollplaza : tollPlazaList) {
                                        if (tollplaza.getId().equals(id)) {
                                            selectedTollPlazaName = tollplaza.getName();
                                            GeneralUtility.writeObjectToFile(tollplaza, tollplaza.getName());
                                        }
                                    }
                                    printAsteriskLine();
                                    System.out.println("Save to file on following path " + AppConstants.appFolderPath + selectedTollPlazaName + ".txt");
                                    flagForChildLoop = false;
                                    break;
                                default:
                                    System.out.println("Invalid input!!");
                                    printAsteriskLine();
                            }
                        }
                    } else {
                        System.out.println("Toll Plaza '" + id + "' does not exist here!!!");
                    }
                case "4":
                    printAsteriskLine();
                    System.out.println("Thank you. Good Bye!");
                    printAsteriskLine();
                    flagForParentLoop = false;
                    break;
                default:
                    System.out.println("Invalid input!!");
            }
        }
    }
}