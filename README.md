🚀 RedBus Search & Result Validation Automation
📌 Project Overview

This project automates the RedBus web application’s core search functionality using Selenium WebDriver with Java.

The automation script performs an end-to-end validation of:
Selecting source (From) location
Selecting destination (To) location
Searching for available buses
Applying "Primo Bus" filter
Handling lazy loading (infinite scroll)
Fetching and validating the total number of buses displayed
The entire automation logic is implemented inside a single Java class using Selenium WebDriver and explicit waits.

🛠️ Tech Stack

Language: Java
Automation Tool: Selenium WebDriver
Browser: ChromeDriver
Synchronization: WebDriverWait (Explicit Waits)
JavaScript Handling: JavascriptExecutor
Build Tool: Maven
Version Control: Git & GitHub

🧪 Functional Flow Automated

Launch RedBus website
Select From location (Mumbai)
Select To location (Pune)
Click Search
Apply Primo Bus filter

Validate:

Buses count text (e.g., "XX buses found")
Bus names displayed
Total number of buses loaded

⚙️ Key Automation Concepts Implemented
✅ Explicit Waits

Used WebDriverWait with ExpectedConditions to handle dynamic elements.

✅ Handling Dynamic Suggestions

Captured auto-suggestion list and selected location dynamically using loop and text comparison.

✅ Lazy Loading Handling

Implemented while loop with scroll using JavascriptExecutor until:

"End of list" element appears

This ensures all buses are loaded before validation.

✅ Chained WebElement Locators

Used relative XPath and chained findElement() calls to fetch bus names from each row.

✅ Dynamic Element Handling

Used numberOfElementsToBeMoreThan() to wait for search results to load properly.

📂 Project Structure
src/main/java → Contains main automation class
pom.xml       → Maven dependencies
▶️ How to Run

Clone the repository
Open in IntelliJ / Eclipse
Install Maven dependencies
Run the main class

🎯 What I Learned From This Project

Handling auto-suggestion dropdowns.
Implementing explicit waits effectively.
Managing lazy loading with JavaScript scroll.
Working with dynamic web elements.
Extracting and validating dynamic data from web pages
Writing end-to-end Selenium automation scripts
