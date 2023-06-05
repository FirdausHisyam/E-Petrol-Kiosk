/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package groupprojecteop;
import java.util.*;
/**
 *
 * @author USER
 */
public class GroupProjectEOP {
  
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
      
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
                    petrolStation.saveDataToFile();
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
}
