package hr.fer.zemris.evolution;

import java.util.ArrayList;

public class GenerationalAlgorithm extends GeneticAlgorithm {
	
	private boolean elitism;

	public GenerationalAlgorithm(Function function, int maxPopulation, int maxIterations, double mutationProbability, boolean elitism) {
		super(function, maxPopulation, maxIterations, mutationProbability);
		this.elitism = elitism;
	}

	@Override
	protected void createGeneration() {
		for(Individual individual : this.population) {
			individual.setCostFunction(this.function.evaluateIndividual(individual));
		}
		
		ArrayList<Individual> auxillaryPopulation = new ArrayList<>();
		if (this.elitism) auxillaryPopulation.add(this.population.get(0));
		
		for(int i=0; i<this.maxPopulation; i++) {
			if(this.elitism && i==0) continue; // preskočimo jednu iteraciju
			Individual parentA = Selection.kTournament(this.population, 3);
			Individual parentB = Selection.kTournament(this.population, 3);
			Individual child = Individual.crossover(parentA, parentB);
			child.mutate(this.mutationProbability);
			
			child.setCostFunction(this.function.evaluateIndividual(child));
			auxillaryPopulation.add(child);
		}
		
		this.population = auxillaryPopulation;
		
	}

}
