# <img src="https://cdn-icons-png.flaticon.com/512/2079/2079291.png" alt="Avocardo Image" height="40px" width="40px"/>  crispy-guacamole


## About the Program

Design and Analysis of Algorithms (DAA) Assignment, assigned on 14 March, 2023.  
Use of Knuth–Morris–Pratt(KMP) and Brute-Force string matching algorithms on a word puzzle.

|   |   |   |   |   |   |
|---|---|---|---|---|---|
| A | B | C | D | V | D |
| M | X | Y | J | K | A |
| J | Z | L | Y | O | M |
| L | P | R | O | S | E |
| N | D | S | P | U | R | 
| F | I | R | E | N | N |


We are required to search for a given word in this matrix.
The word could be present in a horizontal or vertical manner but not diagonally.

#### Implementation:
* Brute force: Two types of brute force pattern matching, horizontal and vertical done across each row and column of the matrix, respectively.
* Knuth-Morris-Pratt Algorithm: Two types of KMP pattern matching, horizontal and vertical done across each row and column of the matrix, respectively.

> #### Implementation is done using Java

## Getting Started

### How to Run the Program [On Windows using Command Prompt or Power Shell]
> - Navigate to the location ```\src\main\java\```
> - Compile using the command: ```javac org\example\Main.java``` 
> - Class file _Main.class_ will be generated at the locaiton "org\example\"
> - Run the program using the command: ```java org.example.Main```

### Input Details
> The program prompts the user for an input string, which is to be searched in the matrix   
> ```Enter a string to be searched: <Input-String>```   
> After the results are shown, the program prompts the user if they want to search again or not   
> ```Search again? [y/n]: <y/Y for searching again>```   

### Sample Run
![sample](https://user-images.githubusercontent.com/76421618/228444050-20fbf122-f9bb-4d1a-be02-b1be0b0c2f82.png)
