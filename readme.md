# Multi-Agent System for Parallel Integral Computation  

## Overview  
This project uses **JADE (Java Agent DEvelopment Framework)** to compute numerical integrals using **Simpson’s method**. It demonstrates **parallel task execution** through agent communication and coordination.  

## Goals  
- Implement a **distributed computing system** using multi-agent technology.  
- Compare **sequential vs. parallel** numerical integration.  
- Utilize **JADE agents** for task distribution and communication.  

## Implementation  
### Agents  
- **ComputeAgent**: Registers a computation service, processes integration tasks, and returns results.  
- **TestParallelAgent**: Splits tasks, assigns them to agents, collects results, and compares execution times.  

### Core Components  
- **Function.java**: Abstract class defining a mathematical function.  
- **ReverseFunction.java**: Implements **f(x) = 1/x**, ensuring safe calculations.  
- **Simpson’s Rule**: Numerical integration technique used for computation.  

## How It Works  
1. **TestParallelAgent** computes the integral sequentially.  
2. It searches for **ComputeAgents**, divides the workload, and assigns tasks.  
3. **ComputeAgents** perform calculations and return results.  
4. The system measures execution time for both methods and compares efficiency.  

## Requirements  
- Java  
- JADE framework  

# Usage

First you need to compile the project by runing the script `compile.sh` : 
```
./compile.sh
```


To run the project you need to run the script `run_cli.sh`.

The basic usage of the script is like the following example:

```
./run_cli.sh -agents -agents 'phil:pl.jade.ComputeAgent;parellel:pl.jade.TestParrallelAgent'
```
By default the program is calculating the integral from 1 to 10 with a setp of 0.000001

-You can add more agents or specify parameters for the parrellel compute agent (lower_bound, upper_bound, step) using the following syntax:

```
./run_cli.sh -agents -agents 'phil:pl.jade.ComputeAgent;phil2:pl.jade.ComputeAgent;parellel:pl.jade.TestParrallelAgent(1, 10, 0.000001)'
```


# Work repartition

The work has been distributed like this :

| Part of the project(Classes)                          | Mourtada            | Ghilas             |
|----------------------------------------------|--------------------|--------------------|
| Function                              | X |                    | 
| ComputeAgent                               | X |                    | 
| ParrallelAgent                               |                    | X | 
| README                                       | X |                    | 
| shell scripts                                |                    | X | 
| Simpson Algorithme                                |                    | X | 
| Javadoc                                |X                    | X | 
