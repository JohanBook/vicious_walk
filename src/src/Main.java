////////////////////////////////////////////////////////////////
// Main.java
// Run with arguments [int: number of simulations][boolean: display GUI]
// Johan Book
// 2015-10-12
////////////////////////////////////////////////////////////////

package src;

import gui.GUI;

import java.util.ArrayList;
import java.util.Map;

import character.Character;
import character.Class;
import react.Collision;
import settings.Settings;
import util.Pair;
import util.Printer;
import util.Random;
import util.Util;

public class Main {
	private final ArrayList<Character> characters;
	private final Map<Class, Double> densities;
	private final Settings settings;

	public Main() {
		settings = new Settings();
		Initializer placer = new Initializer(settings);
		characters = placer.getInitalCharacters();

		// Assumes equal amount of each class
		densities = Util.initiateMap(settings.numbPALADIN);
	}

	// Simulates all time-steps. If supplied with a printer writes data to file
	// Stores densities of each step in map
	public void simulate(Printer printer, Map<Class, Double>[] map) {
		// Print file header
		if (printer != null)
			printer.write("#" + Class.classesToString());

		// Take each step
		for (int step = 0; step < Settings.steps; step++) {
			int penis = characters.size();
			for (int j = 0; j < penis; j++)
				simulate();

			// Heal characters
			healAllCharacters();

			if (printer != null)
				printer.write(step + "\t" + densitiesToString(false));

			if (map != null)
				map[step] = Util.addMaps(map[step], Util.copyMap(densities));
		}
		if (printer != null)
			printer.close();
	}

	// Simulates time-steps together with a GUI
	public GUI createGUI() {
		return new GUI(characters, settings.getDimensions());
	}

	// Put all densities into a string
	public String densitiesToString(boolean normalize) {
		String string = "";
		int norm = normalize ? densities.size() : 1;
		for (Map.Entry<?, Double> entry : densities.entrySet())
			string += entry.getValue() / norm + "\t";
		return string;
	}

	// Simulates one time-step
	private void simulate() {

		// Create lists to store Characters to remove and add
		ArrayList<Character> remove_list = new ArrayList<Character>();

		// Pick and move one random Character
		Character selected_Character = characters.get(Random.nextInt(characters
				.size()));
		selected_Character.move();

		// check for reactions/fights
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i) == selected_Character)
				continue;

			// Check for reaction
			Pair<Boolean, Character> reaction = Collision.check(
					selected_Character, characters.get(i));

			// if there is no reaction continue
			if (!reaction.first)
				continue;

			// If there is a fight remove the one who loses
			if (reaction.second != null) {
				remove_list.add(reaction.second);
				densities.compute(reaction.second.getType(), (k, v) -> v - 1);
			}
			break;
		}

		// Remove and add Character
		for (Character Character : remove_list)
			characters.remove(Character);
	}

	private void healAllCharacters() {
		for (Character character : characters)
			character.heal();
	}

	// Main [int: number of simulations][boolean: display GUI]
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int numbSim = 1; // number of simulations to run
		boolean useGUI = false; // whether to display GUI
		if (args.length >= 1) {
			numbSim = Integer.parseInt(args[0]);
			useGUI = false;
		}
		if (args.length >= 2)
			useGUI = true;

		// Initiate a printer to write the final result
		// from each simulation and a map to record the average.
		Printer printer_summary = new Printer("summary");
		printer_summary.write("#Sim\t" + Class.classesToString());

		// Setup map for recording final values and average for each step
		Map<Class, Double>[] step_average = new Map[Settings.steps];
		for (int i = 0; i < Settings.steps; i++)
			step_average[i] = Util.initiateMap(0);

		for (int i = 0; i < numbSim; i++) {
			Main simulation = new Main();

			// Initiate GUI if used
			GUI gui = null;
			if (useGUI)
				gui = simulation.createGUI();

			// Run a simulation and print data to file
			simulation.simulate(new Printer("result_sim_" + (i + 1)),
					step_average);
			printer_summary
					.write(i + "\t" + simulation.densitiesToString(true));

			System.out.println("Simulation " + (i + 1) + "/" + (numbSim)
					+ " completed");

			// Close GUI
			if (useGUI)
				gui.dispose();
		}
		printer_summary.close();

		// Calculate average for each step and print
		Printer average_each_step = new Printer("step_average");
		average_each_step.write("Step\t" + Class.classesToString());
		for (int step = 0; step < Settings.steps; step++) {
			String[] string = Util.mapToString(step_average[step], numbSim,
					false);
			average_each_step.write(step + "\t" + string[1]);
		}
		average_each_step.close();

		// Calculate final average and put into string
		String[] results = util.Util.mapToString(
				step_average[Settings.steps - 1], numbSim, true);

		// Print the final average from all simulations
		printer_summary = new Printer("summary_average");
		printer_summary.write("#" + results[0]);
		printer_summary.write(results[1]);
		printer_summary.close();

		// Exit
		System.out.println("All simulations complete");
		System.exit(0);
	}

}
