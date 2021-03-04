package hr.fer.zemris.evolution;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Function f = new Function("/Users/lukanamacinski/FER-workspace/NENR-workspace/lab4-input/input_2.txt");
		
		GeneticAlgorithm ga = new GenerationalAlgorithm(f, 500, 1000, 0.2, true);
		
		ga.execute();
		
		GeneticAlgorithm ssa = new SteadyStateAlgorithm(f, 500, 10000, 0.2);
		
//		ssa.execute();
	}

}
