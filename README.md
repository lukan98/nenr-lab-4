# Genetic Algorithm for Symbolic Regression (NENR Lab 4)

A Java genetic algorithm that fits a nonlinear function to measured data. It was written for the **NENR (Fuzzy, Evolutionary and Neurocomputing)** course at FER, University of Zagreb.

## Problem

Each individual encodes five real-valued genes. The fitted function is:

```text
f(x, y) = sin(g0 + g1*x) + g2*cos(x*(g3 + y)) / (1 + exp((x - g4)^2))
```

The algorithm minimizes the mean squared error between this function and the measured values in a dataset.

## Implemented variants

- **Generational algorithm** — replaces the whole population each generation
- **Steady-state algorithm** — replaces part of the population each iteration
- Tournament selection
- Mutation and crossover of real-valued genes

## Input format

A whitespace-separated text file with three columns:

```text
x y f(x, y)
```

## Requirements

- Java JDK 11 or newer
- Eclipse is optional; the repository includes Eclipse project metadata
- A `.tool-versions` file pins JDK 11 for asdf users

Install the pinned toolchain with asdf:

```bash
asdf install
```

## Build and run

From the project root:

```bash
mkdir -p bin
find src -name "*.java" -print0 | xargs -0 javac -d bin
java -cp bin hr.fer.zemris.evolution.Main
```

## Notes

`src/hr/fer/zemris/evolution/Main.java` contains an absolute path to the original dataset (`lab4-input/input_2.txt`). The dataset is **not included** in this repository, so edit the path to point to your own file before running. The class also supports switching between the generational and steady-state algorithms.

## Project structure

```text
src/hr/fer/zemris/evolution/
├── Function.java             # dataset loading and individual evaluation
├── Individual.java           # real-valued genotype
├── Selection.java            # tournament selection
├── GeneticAlgorithm.java     # shared GA logic
├── GenerationalAlgorithm.java
├── SteadyStateAlgorithm.java
└── Main.java
```
