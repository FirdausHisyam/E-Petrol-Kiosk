package csci1300project;
import java.util.*;
import java.io.*;
/**
 *
 * @author Muhammad Firdaus bin Badrul Hisyam (2222041) Nurdanish Effendi bin Roestam Effendi (2224875)

 */
public class EPetrolKiosk {

    private static final String DATA_FILE = "petrol_data.txt"; //finalizing the txt document name that will be used in this program
    private static final int DISPENSER_UNITS = 4; //only 4 dispenser units will be used in the program
    private static final int FUEL_TYPES = 4; //only 4 types of fuel will be used in the program
    private static double[][] petrolLevels; //this parameter will be used in methods that will read the dispenser unit and fuel type
    private static final double MIN_FUEL_LEVEL = 50.0; //this is to set the minimum fuel amount needed before needing to notify management for a refuel
    
    //main method
    public static void main(String[] args) {
        initializeDataFromFile();
        
        //boolean is used to control the looping of the program, depending whether the user wants to continue using the system or not after each process
        boolean continueUsingSystem = true;
        
            while (continueUsingSystem) {
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
                
                
                //input from user determine what they want to do base on the option given
                int choice = input.nextInt();

                switch (choice) {
                    case 1:  //calls the method to display fuel prices
                        displayFuelPrices();
                        break;
                    case 2: //calls the method to purchase fuel
                        System.out.print("Enter dispenser unit number: ");
                        int dispenserUnit = input.nextInt();
                        if (dispenserUnit < 1 || dispenserUnit > DISPENSER_UNITS) {
                            System.out.println("Invalid dispenser unit. Please enter a valid value.");
                            continue; // Skip to the next iteration of the loop
                        }
                        System.out.print("Enter fuel type number: ");
                        int fuelType = input.nextInt();
                        if (fuelType < 1 || fuelType > FUEL_TYPES) {
                            System.out.println("Invalid fuel type. Please enter a valid value.");
                            continue; // Skip to the next iteration of the loop
                        }
                        System.out.print("Enter amount of money: ");
                        double amount = input.nextDouble();
                        if (amount <= 0) {
                            System.out.println("Invalid amount of money. Please enter a positive value.");
                            continue; // Skip to the next iteration of the loop
                        }
                        purchaseFuel(dispenserUnit, fuelType, amount);
                        break;
                    case 3: //calls the method to display fuel level at a dispenser unit
                        System.out.print("Enter dispenser unit number: ");
                        dispenserUnit = input.nextInt();
                        if (dispenserUnit < 1 || dispenserUnit > DISPENSER_UNITS) {
                            System.out.println("Invalid dispenser unit. Please enter a valid value.");
                            continue; // Skip to the next iteration of the loop
                        }
                        System.out.print("Enter fuel type number: ");
                        fuelType = input.nextInt();
                        if (fuelType < 1 || fuelType > FUEL_TYPES) {
                            System.out.println("Invalid fuel type. Please enter a valid value.");
                            continue; // Skip to the next iteration of the loop
                        }
                        double fuelLevel = getFuelLevel(dispenserUnit, fuelType);
                        System.out.printf("Fuel level at dispenser unit %d for fuel type %d: %.2f litres\n", dispenserUnit, fuelType, fuelLevel);
                        break;
                    case 4: //this case will call the method to save the data and terminates the system
                        saveDataToFile();
                        System.out.println("Exiting the program...");
                        input.close();
                        System.exit(0);
                        break;
                    default: //any inputs other than the above will display an error message
                        System.out.println("Invalid choice. Please try again.");
                }
                
                //Program will ask the user to either continue using the system or not
                System.out.println("Operation completed. Do you want to continue using the system?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int continueChoice = input.nextInt();
                
                //If user chooses to stop using the system, system will terminate
                if (continueChoice == 2) {
                    saveDataToFile();
                    System.out.println("Exiting the program...");
                    input.close();
                    continueUsingSystem = false;
                }
            }
        }
    
        //method that reads the data from the txt file
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
                        petrolLevels[i][j] = 500.00;
                    }
                }
            }
        }
        
        //method that saves the current data into the txt file
        private static void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (int i = 0; i < DISPENSER_UNITS; i++) {
                for (int j = 0; j < FUEL_TYPES; j++) {
                    writer.print(petrolLevels[i][j] + " ");
                }
                writer.println();
            } writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data to the file.");
        }
        }
        
        //method that shows the fuel price according the the fuel type
        public static void displayFuelPrices() {
            System.out.println("Current fuel prices per Litre:");
            System.out.println("1. RON95: RM2.05");
            System.out.println("2. RON97: RM3.35");
            System.out.println("3. RON100: RM4.15");
            System.out.println("4. Diesel: RM2.15");
        }
        
        //method that enables user to purchase fuel based on the amount of money
        public static void purchaseFuel(int dispenserUnit, int fuelType, double amount) {
            double price = 0.0;

            switch (fuelType) {
                case 1:
                    price = 2.05;
                    break;
                case 2:
                    price = 3.35;
                    break;
                case 3:
                    price = 4.15;
                    break;
                case 4:
                    price = 2.15;
                    break;
                default:
                    System.out.println("Invalid fuel type.");
          return;
            }

            double litres = amount / price;
            petrolLevels[dispenserUnit - 1][fuelType - 1] -= litres; //minus one to ensure proper numbering starting from 1 instead of 0, ease of usage for users
            // Check if the fuel level is below the threshold
            if (petrolLevels[dispenserUnit - 1][fuelType - 1] < MIN_FUEL_LEVEL) {
            System.out.println("Warning: Fuel level is low. Please refill the dispenser unit.");
            }
            System.out.printf("Filled %.2f litres of fuel. Total price: RM%.2f\n", litres, amount);
            
        }
        
        //method to check the fuel level for a fuel type at a dispenser unit
    public static double getFuelLevel(int dispenserUnit, int fuelType) {
        if (dispenserUnit < 1 || dispenserUnit > DISPENSER_UNITS || fuelType < 1 || fuelType > FUEL_TYPES) {
        System.out.println("Invalid dispenser unit or fuel type. Please enter a valid value.");
        return 0.0; // or any default value you want to return
        }
    
        double fuelLevel = petrolLevels[dispenserUnit - 1][fuelType - 1];
        return fuelLevel;
    }
}
