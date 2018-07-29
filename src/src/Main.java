////////////////////////////////////////////////////////////////
// Main.java
// Run with arguments [int: number of simulations][boolean: display GUI]
// Johan Book
// 2015-10-12
////////////////////////////////////////////////////////////////

package src;

import java.util.Map;

import character.CharacterClass;
import settings.Settings;
import util.Printer;
import util.Util;

public class Main
{
    // Main [int: number of simulations][boolean: display GUI]
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
	int numbSim = 1; // number of simulations to run
	boolean useGUI = false; // whether to display GUI
	if (args.length >= 1)
	    numbSim = Integer.parseInt(args[0]);
	if (args.length >= 2)
	    useGUI = Boolean.parseBoolean(args[1]);

	useGUI = true; // TODO

	// Initiate a printer to write the final result
	Printer printer_summary = new Printer("summary");
	printer_summary.write("#Sim\t" + CharacterClass.classesToString());

	// Setup map for recording final values and average for each step
	Map<CharacterClass, Double>[] step_average = new Map[Settings.steps];
	for (int i = 0; i < Settings.steps; i++)
	    step_average[i] = Util.initiateMap(0);

	for (int i = 1; i <= numbSim; i++)
	{
	    Simulation simulation = new Simulation(useGUI);

	    // Run a simulation and print data to file
	    simulation.simulate(new Printer("result_sim_" + i), step_average);
	    printer_summary.write(i + "\t" + simulation.densitiesToString(true));

	    System.out.println("Simulation " + i + "/" + (numbSim) + " completed");
	}
	printer_summary.close();

	// Calculate average for each step and print
	Printer average_each_step = new Printer("step_average");
	average_each_step.write("#Step\t" + CharacterClass.classesToString());
	for (int step = 0; step < Settings.steps; step++)
	{
	    String[] string = Util.mapToString(step_average[step], numbSim, false);
	    average_each_step.write(step + "\t" + string[1]);
	}
	average_each_step.close();

	// Calculate final average and put into string
	String[] results = util.Util.mapToString(step_average[Settings.steps - 1], numbSim, true);

	// Print the final average from all simulations
	printer_summary = new Printer("summary_average");
	printer_summary.write("#Fraction of total survivors\n#");
	printer_summary.write("#" + results[0]);
	printer_summary.write(results[1]);
	printer_summary.close();

	// Exit
	System.out.println("All simulations complete");
	System.exit(0);
    }
}