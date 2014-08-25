************************************* GALAXY CONVERTER *********************************************************
										

Design:
		The galaxy converter is basically divided into two parts.
		1. Processing the input - parsing input, processing and displaying result.
		2. RomanNumeral to normal number conversion.
			
	Parsing the input:
		The input data is parsed by DataProcessor.
		4 type of input is processed.
			2 info type to provide either keyword-symbol information OR item-price information.
			2 question type to query either for keyword value OR item price.
		
		The main thing to do here is to get the Roman numeral from the input and convert to normal value by using GalaxyConverter.
		Then create the output in required format and display it.
		
		Classes:
			InputParserHelper - utility to help parsing input.
			DataProcessor - processes data, uses a GalaxyConverter for conversion and displays the result.
		
	Roman to number conversion: (done by RomanConverter)
		The Roman numeral conversion is done by first running all the Validators on the input to verify that the input adheres to rules.
		If the input passed all the validation, the conversion is done by a Evaluator.
		The roman conversion logic is pretty simple.
			Read in reverse order.
			Add value of Roman symbol, if it is greater than or equal to previous symbol.
			Subtract the value of symbol, if it lesser than previous symbol.
			
		Classes:
			RomanChar - enum representing the roman numerals.
			Validators - MaxRepetitionValidator, NoSubtractionValidator etc. to validate all rules.
			RomanEvaluator - calculates the value of a valid roman numeral.
			RomanConverter - converts a roman string to RomanChar[], validates all rules using the list of validators
							 and evaluate string value and returns it.
			CharToEnumConverter - utility to convert a String input to RomanChar enum.

	Other Classes:
		GalaxyConverter - interface implemented by all converters.
		Validator - interface implemented by all validators.
		GalaxyConverterBuilder:
			factory to build a type of GalaxyConverter.
				ROMAN type - builds a RomanConverter  by assembling all the Roman validators, Evaluator and CharToEnumConverter.
			

	Exception Handling:
			Below exceptions are thrown at various stage of translation.
				ConversionException - invalid Roman input. (not following rules)
				UnknownInputException - invalid input query.
				UnknownConverter - invalid type of galaxy converter requested for.
			
	Unit tests:
		JUnit test are present for different classes like the Validators, Builders, RomanConverter, RomanEvaluator etc.
		
**************************************** STEPS TO RUN *******************************************************

 The project is a Maven project. Following are steps to compile, test and run.
	COMPILE: "mvn compile"
	
	INTEGRATION TEST: "mvn -Dtest=DataProcessorTest test"
						The class DataProcessorTest shows the main conversion. It processes the input from a file 'input.txt'.
						file location - test/java/com/twr/query/files/input.txt
						
	RUN TESTS: "mvn test"


