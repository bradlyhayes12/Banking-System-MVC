# Banking System using MVC
This project is a banking system application that allows users to 
* Select different peoples accounts and be able to edit the currency.
* Deposit a specified amount of money into an account.
* Withdraw a specified amount of money from an account.
* Also be able to save and exit the application
  
The project is implemented using the Java programming langauage and follows the Model-View-Controller (MVC) design pattern to ensure clean separation of logic and user interface. 
## Features
* **Account Selection**: User can choose between different customers accounts and edit in different currencies.
* **Deposit Functionality**: Allows users to securely deposit money into their selected account.
* **Withdraw Funcctionality**: Enables users to withdraw money while maintaining account valildity (e.g., ensuring sufficient balance).
* **State Persistence**: The system saves and updates the account state to reflect real-time changes.
* **Save/Exit**: Allows users to save the current state as well as exit the application.
* **File Input**: Users Can modify program arguments to read account data from an account.txt file.
## Prerequisites
Before running the project, ensure you have the following installed:
* [Java Development Kit (JDK) ](https://www.oracle.com/java/technologies/downloads/?er=221886): Version 8 or later.
* [Integrated Development Environment (IDE)](https://eclipseide.org/) (e.g., Eclipse, Microsoft Visual Studio Code, or IntelliJ IDEA).
## How to Run
1. **Download the Project**: Clone or download this repository to your computer.
2. **Open the Project**:
   * Import the project into your preferred IDE.
   * Ensure the project is configured with the correct JDK.
3. **Modify Arugments**: Ensure the program arguments include the path to the **accounts.txt** file. For Exanple:
   ```bash
    accounts.txt
    ```
4. **Compile the Code**: Build the project to compile the Java files.
5. **Run the Application**: Execute the ```AcctManagerMain.java``` class to start the banking system.
## File Structure
* **src/**: Contains the source code for the project, including:
  * **Model**: the model aspect of the MVC.
  * **Controller**: which allows the view to communicate wit hthe model.
  * **View**: consists of the ```AcctManagerMain.java```.
* **accounts.txt**: Contains inital account data for the program. 
