package com.wileyedge.FlooringMastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class UserIO {

	private Scanner scanner = new Scanner(System.in);
	
	public int getInt() {
		while(true) {
			if(scanner.hasNextInt()) {
				int response = scanner.nextInt();
				scanner.nextLine();
					return response;
			}else {
				System.out.println("Please enter an integer:");
				scanner.next();
			}
		}
	}
	
	public int getInt(int min, int max) {
		System.out.println("Enter an int between " + min + " and " + max + " inclusive:");
		while(true) {
			if(scanner.hasNextInt()) {
				int response = scanner.nextInt();
				scanner.nextLine();
				if(response >= min & response <= max) {
					return response;
				}else {
					System.out.println("Please enter a value between " + min + " and " + max + " inclusive:");
				}
			}else {
				System.out.println("Please enter a value between " + min + " and " + max + " inclusive:");
				scanner.next();
			}
		}
	}
	
	public BigDecimal getBigDecimal(BigDecimal min) {
	    System.out.println("Enter a decimal number greater than or equal to " + min + ":");
	    while (true) {
	        if (scanner.hasNextBigDecimal()) {
	            BigDecimal response = scanner.nextBigDecimal();
	            scanner.nextLine();
	            if (response.compareTo(min) >= 0) {
	                return response;
	            } else {
	                System.out.println("Please enter a value greater than or equal to " + min + ":");
	            }
	        } else {
	            System.out.println("Please enter a valid decimal number:");
	            scanner.next();
	        }
	    }
	}
	
	public BigDecimal getBigDecimalWithDefault(BigDecimal min, BigDecimal defaultVal) {
	    System.out.println("Enter a decimal number greater than or equal to " + min + " or press Enter to use the default value (" + defaultVal + "):");
	    while (true) {
	        if (scanner.hasNextLine()) {
	            String responseStr = scanner.nextLine().trim();
	            if (responseStr.isEmpty()) {
	                return defaultVal;
	            }
	            try {
	                BigDecimal response = new BigDecimal(responseStr);
	                if (response.compareTo(min) >= 0) {
	                    return response;
	                } else {
	                    System.out.println("Please enter a value greater than or equal to " + min + " or press Enter to use the default value:");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Please enter a valid decimal number or press Enter to use the default value:");
	            }
	        }
	    }
	}

	
	public boolean getBoolean() {
        System.out.println("Enter 'Y' for yes or 'N' for no:");
        while (true) {
            if (scanner.hasNextLine()) {
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("Y")) {
                    return true;
                } else if (response.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    System.out.println("Please enter either 'Y' for yes or 'N' for no:");
                }
            } else {
                System.out.println("Please enter either 'Y' for yes or 'N' for no:");
                scanner.next();
            }
        }
    }

	
	public LocalDate getDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    System.out.println("Enter a date in MM/dd/yyyy format:");
	    while (true) {
	        String response = scanner.nextLine();
	        try {
	            LocalDate date = LocalDate.parse(response, formatter);
	            return date;
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid date. Please enter a date in the format MM/dd/yyyy:");
	        }
	    }
	}
	
	public LocalDate getFutureDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	    System.out.println("Enter a future date in MM/dd/yyyy format:");
	    while (true) {
	        String response = scanner.nextLine();
	        try {
	            LocalDate date = LocalDate.parse(response, formatter);
	            if (date.isAfter(LocalDate.now())) {
	                return date;
	            } else {
	                System.out.println("Invalid date. The date must be in the future. Please enter a date in the format MM/dd/yyyy:");
	            }
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid date format. Please enter a date in the format MM/dd/yyyy:");
	        }
	    }
	}
	
	public String getCustomerName() {
	    System.out.println("Enter the customer name:");
	    while (true) {
	        String customerName = scanner.nextLine().trim();
	        if (!customerName.isEmpty() && customerName.matches("[a-zA-Z0-9,.]+")) {
	            return customerName;
	        } else {
	            System.out.println("Invalid customer name. The name can't be empty and should only contain alphanumeric characters, commas, and periods. Please try again:");
	        }
	    }
	}



	public String getString(String string) {
		System.out.println(string);
		return scanner.nextLine();
	}
}




