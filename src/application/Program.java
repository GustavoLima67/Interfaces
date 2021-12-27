package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data:");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy HH:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy HH:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalServices rs = new RentalServices(pricePerHour, pricePerDay, new BrazilTaxServices());
		
		rs.processInvoice(cr);
		
		System.out.println("INVOICE:");
		System.out.printf("Basic Payments: %.2f%n",cr.getInvoice().getBasicPayment());
		System.out.printf("Tax: %.2f%n",cr.getInvoice().getTax());
		System.out.printf("Total Payments: %.2f%n",cr.getInvoice().getTotalPayment());
		
		sc.close();

	}

}
