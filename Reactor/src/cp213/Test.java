package cp213;

import java.util.Scanner;

/**
 * Used to test generic Reactor class from command line.
 *
 * @author Harsh Joshi
 * @version 2017-12-08
 */
public class Test {

    public static void main(String args[]) {
	Reactor model = new Reactor(400, 50);
	Scanner keyboard = new Scanner(System.in);
	String input = null;

	while (model.getStatus() == Reactor.Status.OPERATING) {
	    System.out.println("Tick: " + model.getTicks());
	    System.out.println("Rod Heights: " + model.getRodsHeight());
	    System.out.println("Temperature: " + model.getTemperature());
	    System.out.println("Power: " + model.getPower());
	    System.out.println();
	    System.out.println("q: quit, d: drop, +: raise, -: lower");
	    System.out.print("Input: ");
	    input = keyboard.nextLine();

	    if (input.equals("q")) {
		model.quit();
	    } else if (input.equals("d")) {
		model.dropRods();
	    } else if (input.equals("+")) {
		model.raiseRods();
	    } else if (input.equals("-")) {
		model.lowerRods();
	    }
	    System.out.println();
	    model.tick();
	}
	System.out.println("Done");
	System.out.println("Status: " + model.getStatus());
	System.out.println("Rod Heights: " + model.getRodsHeight());
	System.out.println("Tick: " + model.getTicks());
	System.out.println(
		"Average Temperature: " + model.getAverageTemperature());
	System.out.println("Average Power: " + model.getAveragePower());
	System.out.println();

	keyboard.close();
    }

}
