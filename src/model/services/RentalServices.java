package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {

	private Double pricePerDay;
	private Double pricePerHour;
	private TaxServices taxServices;
	
	public RentalServices(Double pricePerDay, Double pricePerHour, TaxServices taxServices) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxServices = taxServices;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double basicPayment;
		if(hours <= 12.0) {
			 basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			 basicPayment = pricePerDay * Math.ceil(hours / 24);
		}
		
		double tax = taxServices.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
