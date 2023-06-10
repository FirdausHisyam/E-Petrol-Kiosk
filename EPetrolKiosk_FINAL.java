package csci1300project;
import java.util.*;
import java.io.*;
/**
 *
 * @author Firdaus | Trigger
 */
public class EPetrolKiosk {

    private static final String DATA_FILE = "petrol_data.txt";
    private static final int DISPENSER_UNITS = 4;
    private static final int FUEL_TYPES = 4;
    private static double[][] petrolLevels;

    public static void main(String[] args) {
        initializeDataFromFile();
        
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
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        displayFuelPrices();
                        break;
                    case 2:
                        System.out.print("Enter dispenser unit number: ");
                        int dispenserUnit = input.nextInt();
                        System.out.print("Enter fuel type number: ");
                        int fuelType = input.nextInt();
                        System.out.print("Enter amount of money: ");
                        double amount = input.nextDouble();
                        purchaseFuel(dispenserUnit, fuelType, amount);
                        break;
                    case 3:
                        System.out.print("Enter dispenser unit number: ");
                        dispenserUnit = input.nextInt();
                        System.out.print("Enter fuel type number: ");
                        fuelType = input.nextInt();
                        double fuelLevel = getFuelLevel(dispenserUnit, fuelType);
                        System.out.printf("Fuel level at dispenser unit %d for fuel type %d: %.2f litres\n", dispenserUnit, fuelType, fuelLevel);
                        break;
                    case 4:
                        saveDataToFile();
                        System.out.println("Exiting the program...");
                        input.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
                System.out.println("Operation completed. Do you want to continue using the system?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int continueChoice = input.nextInt();

                if (continueChoice == 2) {
                    saveDataToFile();
                    System.out.println("Exiting the program...");
                    input.close();
                    continueUsingSystem = false;
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
                        petrolLevels[i][j] = 500.00;
                    }
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
            System.out.println("An error occurred while saving the data to the file.");
        }
        }

        public static void displayFuelPrices() {
            System.out.println("Current fuel prices per Litre:");
            System.out.println("1. RON95: RM2.05");
            System.out.println("2. RON97: RM3.35");
            System.out.println("3. RON100: RM4.15");
            System.out.println("4. Diesel: RM2.15");
        }

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
            petrolLevels[dispenserUnit - 1][fuelType - 1] -= litres; ////minus one to ensure proper numbering starting from 1 instead of 0, ease of usage for users

            System.out.printf("Filled %.2f litres of fuel. Total price: RM%.2f\n", litres, amount);
            
        }

        public static double getFuelLevel(int dispenserUnit, int fuelType) {
            double fuelLevel = petrolLevels[dispenserUnit - 1][fuelType - 1];
            return fuelLevel;
        }
}
