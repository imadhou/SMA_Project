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