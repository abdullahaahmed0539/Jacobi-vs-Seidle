package Numerical_analysis;

import java.util.Scanner;

public class Main {
    // Ax=b
    static float[][] A;
    static float[] x;
    static float[] b;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //entering number of rows and columns for matrix A
        System.out.println();
        System.out.println("PLEASE ENTER MATRIX A's SIZE.");
        System.out.print("Please enter n: ");
        int r = input.nextInt();
        int c = r;

        //creating matrix A,x & b
        A = new float[r][c];
        x = new float[c];
        b = new float[c];

        System.out.println();
        System.out.println("Do you want to enter the matrix yourself? true or false");
        boolean choice =input.nextBoolean();
        if(choice==true){diagonally_dominant_yourself(r,c);}
        else{diagonally_dominant(r,c);}

        System.out.println();
        System.out.println();

        System.out.println("Enter tolerance:");
        double tolerance = input.nextDouble();

        System.out.println();
        System.out.println();

        System.out.println("JACOBI");
        System.out.println("----------------------------------");
        int j=jacobi(tolerance, r, c);
        System.out.println();
        System.out.println();

        for(int i=0;i<c;i++){
            x[i]=0;//resetting x values to 0
        }

        System.out.println("SIEDLE");
        System.out.println("----------------------------------");
        int s=seidle(tolerance,r,c);

        System.out.println();
        System.out.println();
        System.out.println("JACOBI TOOK "+j+" ITERATIONS WHEREAS SEIDLE TOOK "+s+" ITERATIONS.");
        System.out.println("------------------------------------------------------------------");
    }


    public static void diagonally_dominant(int r1,int c1){
      int random_number;
      for(int i=0;i<r1;i++){ //creating a diagonally dominant matrix A
          for(int j=0;j<c1;j++){
              if (i==j){
                    random_number= (int) ((Math.random()*1000)+1); //+1 make sures that the diagonal never has 0 in it so that det!=0
                    A[i][j]=random_number;
              }else{A[i][j]=0;} 
          }
      }

        //printing matrix A
        System.out.println();
        System.out.println("Printing Matrix A");
        for(int i=0;i<r1;i++){
            for(int j=0;j<c1;j++){
                System.out.print(A[i][j]+"    ");
            }
            System.out.println();
        }

        for(int i=0;i<c1;i++){ ////creating matrix b
            random_number= (int) (Math.random()*100);
            b[i]=random_number*A[i][i];
        }

        //printing matrix A
        System.out.println();
        System.out.println("Printing Matrix b");
        for(int i=0;i<c1;i++){
            System.out.println(b[i]);
        }


    }


    public static void diagonally_dominant_yourself(int r1,int c1){
        Scanner input=new Scanner(System.in);
        //entering elements of matrix A
        System.out.println("PLEASE ENTER ELEMENTS OF MATRIX A. MAKE SURE ITS DIAGONALLY DOMINATED!");
        for (int i = 0; i < r1; i++) {
            System.out.println("row: " + (i + 1));
            for (int j = 0; j < c1; j++) {
                System.out.println("    column: " + (j + 1));
                A[i][j] = input.nextInt();
                if((i==j) && (A[i][j] == 0)){
                    while(A[i][j] == 0){
                        System.out.println("You cannot enter 0");
                        System.out.println("Enter again for column "+ (j + 1));
                        A[i][j] = input.nextInt();
                    }
                }
            }
        }

        System.out.println();
        System.out.println();

        //entering element of matrix x
        for (int i = 0; i < c1; i++) {
            x[i] = 0;
        }

        System.out.println();
        System.out.println();

        //entering elements of matrix b
        System.out.println("PLEASE ENTER ELEMENTS OF MATRIX b.");
        for (int i = 0; i < c1; i++) {
            System.out.println("row: " + (i + 1));
            b[i] = input.nextInt();
        }


        System.out.println();
        System.out.println();

        //printing matrix A
        System.out.println("MATRIX A.");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();


        //printing matrix b
        System.out.println("MATRIX b.");
        for (int i = 0; i < c1; i++) {
            System.out.println(b[i]);

        }

        System.out.println();
        System.out.println();

    }


    public static int jacobi(double tolerance, int r1, int c1) {
        int itr = 0;
        int count = 0;
        float sum = 0;
        float temp[] = new float[c1];
        float[] diff = new float[c1];
        boolean[] tolerance_met = new boolean[c1];

        while (count != c1) {// loop continues till every xi has met tolerance
            System.out.println("----");
            System.out.println("iteration# " + (++itr));//iteration counter

            for (int i = 0; i < c1; i++) {//make sures that the previous values of x are not overwritten
                temp[i] = x[i];
            }

            for (int j = 0; j < r1; j++) {
                sum = 0;//make sures that sum is zero for each xi
                for (int i = 0; i < c1; i++) {//calculates sum
                    if (i != j) {
                        sum = sum + (A[j][i] * temp[i]);
                    }
                }
                x[j] = (b[j] / A[j][j]) - (sum / A[j][j]);//finds xi
                System.out.println("x" + (j + 1) + "=" + x[j]);
            }

            for (int i = 0; i < c1; i++) {
                diff[i] = x[i] - temp[i]; //finds diff between current xi+1 and xi
                if (diff[i] < 0) {
                    diff[i] *= -1;
                }//turns diff to +ve
                if (diff[i] <= tolerance) {
                    tolerance_met[i] = true;
                }
            }

            count = 0;
            for (int i = 0; i < tolerance_met.length; i++) {
                if (tolerance_met[i] == true) {
                    count++; //count number of xi's which have met the tolerance
                }
            }
        }
        return itr;
    }


    public static int seidle( double tolerance,int r1,int c1){
        int itr = 0;
        int count = 0;
        float sum = 0;
        float temp[] = new float[c1];
        float[] diff = new float[c1];
        boolean[] tolerance_met = new boolean[c1];

        while (count != c1) {// loop continues till every xi has met tolerance
            System.out.println("----");
            System.out.println("iteration# " + (++itr));//iteration counter

           for (int i = 0; i < c1; i++) {//make sures that the previous values of x are not overwritten
               temp[i] = x[i];
            }

           for (int j = 0; j < r1; j++) {
                sum = 0;//make sures that sum is zero for each xi
                for (int i = 0; i < c1; i++) {//calculates sum
                    if (i != j) {
                        sum = sum + (A[j][i] * x[i]);
                    }
                }
                x[j] = (b[j] / A[j][j]) - (sum / A[j][j]);//finds xi
                System.out.println("x" + (j + 1) + "=" + x[j]);
            }

            for (int i = 0; i < c1; i++) {
                diff[i] = x[i] - temp[i]; //finds diff between current xi+1 and xi
                if (diff[i] < 0) {
                    diff[i] *= -1;//turns diff to +ve
                }
                if (diff[i] <= tolerance) {
                    tolerance_met[i] = true;
                }
            }

            count = 0;
            for (int i = 0; i < tolerance_met.length; i++) {
                if (tolerance_met[i] == true) {
                    count++; //count number of xi's which have met the tolerance
                }
            }
        }
        return itr;
    }


}