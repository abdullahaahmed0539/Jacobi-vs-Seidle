# Jacobi-vs-Seidle
This is a numerical analysis project (third semester, sophomore year of my Bachelors of Computer Science) which compares two methods Jacobi and Seidle. It informs the number of interations taken by each method for comparison.
To use this program, you should enter:

  1) value of n, which is the order of the matrix (i.e n x n).
  2) your choice to enter values into the matrices yourself or let the program do it automatically (assigning random value).
  3) Enter tolerance value. You can enter whatever value you want but the more it is closer to zero, the more accurate the algorithms perform.

The program does the following tasks:

  1) After entering you choice, it creates a diagonally dominated matrix. If you are entering values yourself, it makes sure that the matrice you entered is              diagonally dominated.
  2) It performs Jacobi and Seidle method. It keep tracks for iterations performed for both.
  3) It outputs the iterations done by both algorithms for comparison
