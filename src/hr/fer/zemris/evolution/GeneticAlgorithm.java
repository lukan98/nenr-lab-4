package hr.fer.zemris.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class GeneticAlgorithm {
	
	protected int maxPopulation;
	protected int maxIterations;
	protected double mutationProbability;
	
	protected ArrayList<Individual> population;
	protected Function function;
	
	// create a genetic algorithm with a random initial population and set parameters
	public GeneticAlgorithm(Function function, int maxPopulation, int maxIterations, double mutationProbability) {
		this.maxPopulation = maxPopulation;
		this.maxIterations = maxIterations;
		this.mutationProbability = mutationProbability;
		this.function = function;
		
		this.population = new ArrayList<>();
		Random rand = new Random();
		
		for (int popCount=0; popCount<this.maxPopulation; popCount++) {
			Individual newMember = new Individual(rand);
			newMember.setCostFunction(this.function.evaluateIndividual(newMember));
			population.add(newMember);
		}
	}
	
	public void execute() {
		
		for(int i=0; i<this.maxIterations; i++) {
			Collections.sort(this.population);
			double costFunction = this.population.get(0).getCostFunction();
			
			System.out.println("Generation "+ Integer.toString(i+1) + "; Best " + this.population.get(0) + "; error = " + costFunction);
			this.createGeneration();
		}
		
	}
	
	protected abstract void createGeneration();
	
}
