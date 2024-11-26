Prerequisites :
1] Java Development Kit (JDK): Install JDK 11 or higher.

2] Maven (Build Tool): Install Apache Maven if not already installed.

3] IDE: Use any IDE such as IntelliJ IDEA, Eclipse, or VS Code.

4] Google Chrome: Install the latest version of Google Chrome browser.

5] ChromeDriver: Ensure ChromeDriver matches your Chrome browser version. The script uses WebDriverManager to manage this automatically.

6] Dependencies: The script uses Selenium WebDriver, TestNG, and WebDriverManager. Add these dependencies in your pom.xml if using Maven.

**********************************************************************************************************************************************************************************

Project Setup
1] Create a New Maven Project: In your IDE, create a new Maven project.

2] Add Dependencies: Add the following dependencies to your pom.xml:
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.12.0</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.5.3</version>
    </dependency>
</dependencies>

3] Copy the Code: Save the provided code in a Java class file named FitpeoAssignment.java inside the src/main/java/org/example package.

4] Build the Project: Open a terminal in the project directory and run : mvn clean install

******************************************************************************************************************************************************************************

Running the Script
Execution: Run the main method of the FitpeoAssignment class from your IDE or build and execute the project using the following Maven command:
  mvn exec:java -Dexec.mainClass="org.example.FitpeoAssignment"

*********************************************************************************************************************************************************************************
Expected Output:
The script should:
  Navigate to the Fitpeo Revenue Calculator page.
  Adjust the slider value to the desired position and verify the change.
  Select the specified product checkboxes.
  Validate the total recurring reimbursement value.
  Print appropriate messages in the console for each step.
