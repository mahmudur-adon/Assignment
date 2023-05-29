# Assignment
## Introduction
This is a test automation framework based on **Page Object Model** design pattern for **Selenium WebDriver** with *Java*, *maven* and *TestNG*.
Note :

> This framework uses **properties file** to store the credentials.


	
## Dependencies

**Maven Dependencies** - This framework has been designed with following maven dependencies as defined further in the *pom.xml*.


	| Selenium WebDriver|3.141| It automates the testing of web application.|
	| TestNG|7.3.0|To run the tests as a suite.|
	| APACHE Commons IO|2.8.0|To perform file opeartions.|
	| webdrivermanager|4.3.1| WebDriverManager is a library which allows to automate the management of the drivers (e.g. _chromedriver_, _geckodriver_, etc.) required by Selenium WebDriver. [bonigarcia/webdrivermanager: Automated driver management for Selenium WebDriver (github.com)](https://github.com/bonigarcia/webdrivermanager#basic-usage)|



**pom.xml**
```xml
<dependencies>

	<dependency>
		<groupId>org.testng</groupId>
		<artifactId>testng</artifactId>
		<version>7.3.0</version>
		<scope>test</scope>
	</dependency>

	<dependency>
		<groupId>com.aventstack</groupId>
		<artifactId>extentreports</artifactId>
		<version>5.0.6</version>
	</dependency>

	<dependency>
		<groupId>org.seleniumhq.selenium</groupId>
		<artifactId>selenium-java</artifactId>
		<version>3.141.59</version>
	</dependency>

	<dependency>
		<groupId>commons-io</groupId>
		<artifactId>commons-io</artifactId>
		<version>2.8.0</version>
	</dependency>

	<dependency>
		<groupId>io.github.bonigarcia</groupId>
		<artifactId>webdrivermanager</artifactId>
		<version>4.3.1</version>
	</dependency>

	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi</artifactId>
		<version>5.0.0</version>
	</dependency>

	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi-ooxml</artifactId>
		<version>5.0.0</version>
	</dependency>

</dependencies>
```
## How to use


 1. ### Download Dependencies

	-Java JDK 20 must be installed- https://www.oracle.com/java/technologies/downloads/
	-IDE(intellij idea preferable)
    - Open the project with your IDE, open pom.xml and download from maven 
 

 2. ### Project structure
	The automation framework is built to address following 4 major requirements through it's components.

	1. **Setup the WebDriver** to automate actions on browser.
	2. **Read test data** from test data file(formats such as .jpg etc.)
	3. **Page Classes** for each page containing methods to operate on the webelements present on the page.		 
	4. **Properties files** for storing the credentials.
	5. **Test Classes** containing Test cases in the form of methods with annotation @Test.
	6. **Execution control**- We are executing our tests through *testng.xml*. It can be other options such as through mvn test or command line.
	7. **Create a readable html report** after test execution.
		 
3. ### Test Classes		
	We have a class *Macrombox.java* under *src/test/java/Tests*. 
	Every Test method has annotation *@test*. This is the place where we have all the checkpoints.	This class utilizes TestNG annotations to handle the flow of test execution in the given order.
	
	1. **@BeforeSuite**- 
		 - Setup the chromedriver binaries using below: 
			`WebDriverManager.chromedriver().setup();`			
		 - Initialize the WebDriver *driver*
		 - Initialize the Logger *logger*
		 - loads the test data file and initialize it in *testdata*
		 
	2. **@BeforeMethod**- 			
		 - Create a test in Extent Report before each test.
		 - Navigate a urland maximize the window.

	3. **@Test**- 
		
		 - Initialize the page objects.
		 - Validation steps by calling the page objects.

	4. **@AfterMethod**	

		 - End the logging for the test.

	5. **@AfterSuite**	

		 - End the test suite in logging
		 - Quit the WebDriver instance


3. ### Page classes
	I created page classes for each of the pages of our application. 
	`src/main/java>pages> LoginPage.java`

	Each page class has three components as below.
	1. Locators for test steps
	2. Page class methods for tests steps.
	3. Page class constructor sets the driver and loads the respective properties file to read the locator strings.



 5. ### Run tests through testng.xml
	 If you want to run your tests through *testng.xml* 
6. ### Get the emailable report
 add emailablereport as listener on testng and a report will be generated on *test-output* folder 

7. ### Limitations ###
- If the Error message for same jpg file upload appears, the tests dont work.
- Works on Chrome, need modifications to run on other browsers

