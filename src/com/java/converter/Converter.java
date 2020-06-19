package com.java.converter;
import java.util.Scanner;

public class Converter {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        boolean valid = false;
	        if(!scanner.hasNextInt()) {
	        	System.out.println("error");
	        } else {
		        int sourceBase = scanner.nextInt();
		        if (sourceBase < 1 || sourceBase > 36) {
		        	System.out.println("error");
		        } else {
			        String number = scanner.next();
			        String[] separateNumber = number.split("\\.");
			        for (int i = 0; i < separateNumber.length; i++) {
			        	for (int j = 0; j < separateNumber[i].length(); j++) {
				        	if ((separateNumber[i].charAt(j) >= 48 && separateNumber[i].charAt(j) <= 57) || (sourceBase > 10 && separateNumber[i].charAt(j) >= 97  && separateNumber[i].charAt(j) <= 86 + sourceBase)) {
				        		valid = true;
				        	} else {
				        		valid = false;
				        		break;
				        	}
			        	}
			        }
			        if (!valid) {
			        	System.out.println("error");
			        } else {
			        	if(!scanner.hasNextInt()) {
			            	System.out.println("error");
			            } else {
				        int newBase = scanner.nextInt();
					        if (newBase < 1 || newBase > 36) {
					        	System.out.println("error");
					        } else {
						        if (separateNumber.length == 1) {
						//      Converting source number to decimal number...
							        int decimalNumber = sourceToDecimal(sourceBase, number);
						//      Converting decimal number to the desired base number...
							        String newNumber = decimatToNewBase(decimalNumber, newBase);
							        System.out.println(newNumber);
						        } else {
						        	int decimalNumber = sourceToDecimal(sourceBase, separateNumber[0]);
						    	    String newNumber = decimatToNewBase(decimalNumber, newBase);
						    	    if (sourceBase == 10) {
						    	    	String newFractionalNumber = decimatToNewBaseFractional(separateNumber[1], newBase);
						        	    System.out.println(newNumber + "." + newFractionalNumber);
						    	    } else {
							    	    String decimalFractionalNumber = sourceToDecimalFractional(sourceBase, separateNumber[1]);
							    	    String newFractionalNumber = decimatToNewBaseFractional(decimalFractionalNumber, newBase);
							    	    System.out.println(newNumber + "." + newFractionalNumber);
						    	    }
						        }
					        }
				        } 
			        }
		        }
		    }
	    }
	    
	    public static int sourceToDecimal(int sourceBase, String number) {
	        int decimalNumber = 0;
	        if (sourceBase == 10) {
	            decimalNumber = Integer.parseInt(number);
	        } else if (sourceBase == 1) {
	        	int num = Integer.parseInt(number.trim());
	            while (num > 0) {
	                decimalNumber += num % 10;
	                num /= 10;
	            }
	        } else {
	            decimalNumber = Integer.parseInt(number, sourceBase);
	        }
	        return decimalNumber;
	    }
	    
	    public static String decimatToNewBase(int decimalNumber, int newBase) {
	        StringBuilder newNumber = new StringBuilder("");
	        if (newBase == 10) {
	            newNumber = newNumber.append(decimalNumber);
	        } else if (newBase == 1) {
	            for (int i = 1; i <= decimalNumber; i++) {
	                newNumber = newNumber.append("1");
	            }
	        } else {
	            newNumber = newNumber.append(Integer.toString(decimalNumber, newBase));
	        }
	        return newNumber.toString();
	    }
	    public static String sourceToDecimalFractional(int sourceBase, String sb) {
	    	StringBuilder decimalFractionalNumber = new StringBuilder("");
	    	String[] num = sb.split("") ;
	    	double temp = 0;
	    	for (int i = 0; i < 5 && i < sb.length(); i++) {
	    		temp += (double) Integer.parseInt(num[i], sourceBase) / (double) Math.pow(sourceBase, i+1);
	    	}
	    	decimalFractionalNumber = decimalFractionalNumber.append(temp);
	        return decimalFractionalNumber.toString().split("\\.")[1];
	    }
	    
	    public static String decimatToNewBaseFractional(String decimalFractionalNumber, int newBase) {
	    	StringBuilder result = new StringBuilder("");
	    	String[] integral = new String[2];
	    	double dec = Double.parseDouble(decimalFractionalNumber);
	    	double temp = 0.0;
	    	for (int i = 0; i < 5; i++) {
	    		temp = (double) dec / Math.pow(10, decimalFractionalNumber.length()) * newBase;
	    		integral = String.valueOf(temp).split("\\.");
	    		result = result.append(Integer.toString(Integer.parseInt(integral[0].toString()), newBase));
	    		dec =  Double.parseDouble(integral[1]);
	            decimalFractionalNumber = integral[1];
	    	}
	        return result.toString();
	    }
	}
