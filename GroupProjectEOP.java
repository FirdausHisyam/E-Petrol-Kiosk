/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package groupprojecteop;
import java.util.Scanner;
/**
 *
 * @author USER
 */
public class GroupProjectEOP {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        double price = 0;
        char c;
        String[] fuel = {"RON95","RON97","RON100","Diesel"};
                
        System.out.println("=========================");
        System.out.println("Welcome to E-Petrol Kiosk");
        System.out.println("=========================");
        
        
        do{
            c = 'n';
            System.out.println("0. RON95");
            System.out.println("1. RON97");
            System.out.println("2. RON100");
            System.out.println("3. Diesel");
            System.out.print("Please choose your fuel type(0-3):");
            int n = input.nextInt();


            switch(n)
            {
                case 0 : price = 2.05; break;
                case 1 : price = 3.35; break;
                case 2 : price = 4.15; break;
                case 3 : price = 2.15; break;
            }

            System.out.println("Type of fuel:" + fuel[n]);
            System.out.println("Price=RM" + price);
            System.out.print("Confrim your choice? (y/n)");
            c = input.nextLine().charAt(0);
        }while(c != 'y');
        
                
    }
    
    
    
}
