/* @author  Mohamad Jamal Hammoud, Qazi Ali, Mirza Hassan Baig, Muneeb Ali
* @version 3.0
* @since   2023-03-25 
*/

package edu.ucalgary.oop;
import java.awt.EventQueue;

//to test the all classes only this main method is used refer to uml diagram for more details
//now to run the code make sure you are in JAVA folder and run the command "javac -cp .;mysql-connector-java-8.0.23.jar edu/ucalgary/oop/main.java" to compile all the classes
//then run the command "java -cp .;mysql-connector-java-8.0.23.jar edu.ucalgary.oop.main" to run the main method

public class main {
    
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
          new Scheduling().setVisible(true);
    
        });
      }
    }