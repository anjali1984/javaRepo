package com.optum.tops.mse.rest2.j5427ct2.cobol.utils;

public class CobolConversion {

	// Converts the numbers for pic S9 on the way out

	public static String cobolConversion(String conversion, int lengthOfWholeNum, int lengthOfDec) {

		String formatted = conversion;
		boolean positive = false;

		final char signed  = conversion.charAt(0);

		if (signed != '-'){
			positive = true;
		}

		final char decimalPoint = conversion.charAt(conversion.length() - (lengthOfDec + 1));

		if (decimalPoint != '.' && conversion.length() != (lengthOfWholeNum + lengthOfDec)) {
			formatted = String.format("%1$-" + (conversion.length() + (lengthOfDec - 1)) + "s", conversion).replace(' ', '0');
		}
		conversion = conversion.replace(".", "");

		//		System.out.println("This is formatted "  + formatted);

		formatted = String.format("%0" + (lengthOfWholeNum + lengthOfDec) + "d", Integer.parseInt(conversion));

		char lastByte = sendChar(positive, conversion.charAt(conversion.length() - 1));

		formatted = formatted.substring(0,formatted.length() - 1) + lastByte;


		return formatted;

	}

	// Converts the numbers for pic S9 on the way in
	public static String javaConversion(String conversion) {

		final char oldChar = conversion.charAt(conversion.length() - 1);

		final char newChar = getChar(oldChar);

		conversion = conversion.substring(0,conversion.length()-1) + newChar;

		return conversion;

	}

	/*
	 * converts the & in url request param to spaces
	 */
	public static String spaceConversion(String conversion)
	{

		for(int i=0;i<conversion.length();i++)
		{
			char character=conversion.charAt(i);	

			if (character=='&')
			{
				conversion=conversion.replaceAll(conversion,""); 
			}
		}
		return conversion;
	}


	public static char sendChar(boolean positive, char lastByte) {
		// Gets the last byte of java and converts it to Cobol
		if (positive) {
			switch (lastByte) {
			case '0':
				return '{';
			case '1':
				return 'A';
			case '2':
				return 'B';
			case '3':
				return 'C';
			case '4':
				return 'D';
			case '5':
				return 'E';
			case '6':
				return 'F';
			case '7':
				return 'G';
			case '8':
				return 'H';
			case '9':
				return 'I';
			}
		} else {
			switch (lastByte) {
			case '9':
				return 'R';
			case '8':
				return 'Q';
			case '7':
				return 'P';
			case '6':
				return 'O';
			case '5':
				return 'N';
			case '4':
				return 'M';
			case '3':
				return 'L';
			case '2':
				return 'K';
			case '1':
				return 'J';
			case '0':
				return '}';
			}
		}
		throw new IllegalArgumentException("invalid number: " + lastByte);
	}

	public static char getChar(char lastByte) {
		switch (lastByte) {
		case 'R':
		case 'I':
			return '9';
		case 'Q':
		case 'H':
			return '8';
		case 'P':
		case 'G':
			return '7';
		case 'O':
		case 'F':
			return '6';
		case 'N':
		case 'E':
			return '5';
		case 'M':
		case 'D':
			return '4';
		case 'L':
		case 'C':
			return '3';
		case 'K':
		case 'B':
			return '2';
		case 'J':
		case 'A':
			return '1';
		case '}':
		case '{':
			return '0';
		
		}
		return lastByte;
	}
}
