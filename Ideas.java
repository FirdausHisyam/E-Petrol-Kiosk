/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package groupprojecteop;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author FIRDAUS // TRIGGER
 */
public class GroupProjectEOP {
  
  public class PetrolStationManagementSystem {
    private static final String DATA_FILE = "petrol_data.txt";
    private static final int DISPENSER_UNITS = 4;
    private static final int FUEL_TYPES = 4;
    private static double[][] petrolLevels;
    
    public static void main(String[] args) {
        
        while (true) {
        System.out.println("=========================");
        System.out.println("Welcome to E-Petrol Kiosk");
        System.out.println("=========================");
        Scanner input = new Scanner(System.in);
        System.out.println("1. Display fuel prices");
        System.out.println("2. Purchase fuel");
        System.out.println("3. Display fuel level");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
      
      /*side note, we use 2D arrays to track dispenser unit and fuel type (0 for RON95, 1 for RON97, 2 for Diesel)
      ex. petrolLevels = new double[3][3] (assuming there are 3 dispenser units and 3 fuel types)*/

        int choice = scanner.nextInt();

          switch (choice) {
                case 1:
                    displayFuelPrices();
                    break;
                case 2:
                    System.out.print("Enter dispenser unit number: ");
                    int dispenserUnit = scanner.nextInt();
                    System.out.print("Enter fuel type number: ");
                    int fuelType = scanner.nextInt();
                    System.out.print("Enter amount of money: ");
                    double amount = scanner.nextDouble();
                    purchaseFuel(dispenserUnit, fuelType, amount);
                    break;
                case 3:
                    System.out.print("Enter dispenser unit number: ");
                    dispenserUnit = scanner.nextInt();
                    System.out.print("Enter fuel type number: ");
                    fuelType = scanner.nextInt();
                    displayFuelLevel(dispenserUnit, fuelType);
                    break;
                case 4:
                    saveDataToFile();
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
    }    
}
    
    private static void initializeDataFromFile() {
      petrolLevels = new double[DISPENSER_UNITS][FUEL_TYPES];
      
      try (Scanner input = new Scanner(new File(DATA_FILE))) {
        for (int i = 0; i < DISPENSER_UNITS; i++) {
          for (int j = 0; j < FUEL_TYPES; j++) {
            petrolLevels[i][j] = input.nextDouble();
          }
        }
      } catch (FileNotFoundException e) {
        System.out.println("File not found. Initializing with default values.");
        //initialize with default values if file not found
        for (int i = 0; i < DISPENSER_UNITS; i++) {
          for (int j = 0; j < FUEL_TYPES; j++) {
            petrolLevels[i][j] = 100.00;
          }
        }
      }
      
    private static void saveDataToFile() {
      try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
        for (int i = 0; i < DISPENSER_UNITS; i++) {
          for (int j = 0; j < FUEL_TYPES; j++) {
            writer.print(petrolLevels[i][j] + " ");
          }
          writer.println();
        } writer.close();
      } catch (IOException e) {
        System.out.println("An error occured while saving the data to the file.");
      }
    }
    
