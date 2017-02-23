# Matrix Multiplication

## Introduction
This is a library written in Java, which can be used to multiply matrices by different matrix multiplication algorithms. Currently, the library provides two algorithms:
* the naive iterative multiplication with complexity O(n^3), and
* the Strassen algorithm with complexity O(n^2.8).

## How to Use
To use this matrix multiplication library:
* Import package sc.labs.matrices into your project
* Use sc.labs.matrices.Matrix class to create matrices
* Create an onbject of either one of the two MatrixMultiplicators (IterativeMultiplicator or StrassenMultiply)
* Pass two Matrix objects to the multiply method of the MatrixMultiplicator object.
* Resulting Matrix object will be returned if multiplication is successful.
* IllegalArgumentException will be thrown if the given matrices cannot be multiplied.

## Documentation
Visit https://sfllhkhan95.github.io/matrix-multiplication for JavaDoc

