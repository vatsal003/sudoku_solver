import java.util.*;
// import java.lang.*;
// import java.io.*;



class Sudoku{
  
  int sudoku[][]=new int[9][9];

  //You can eneter either space either seperated or continuous
  Sudoku() {
    System.out.println("Enter the Sudoku elements, row by row. (use 0 for blank position)");
  Scanner sc = new Scanner(System.in).useDelimiter("\\n");
  for(int i=0;i<9;i++){
    String s=sc.next();
    
    for(int j=0,k=0;((j<9)&&(k<s.length()));k++){
      if(Character.isDigit(s.charAt(k))){
        sudoku[i][j]=Character.getNumericValue(s.charAt(k));
        j++;  
    }
    }
  }
  sc.close();
  }

    Sudoku(int [][] sudoku){
      this.sudoku=sudoku;
  }

  boolean check_row(int row,int v){
    for(int j=0;j<9;j++){
      if(v==this.sudoku[row][j]){
        return false;
      }
    }
    return true;
  }


  boolean check_column(int column,int v){
    for(int j=0;j<9;j++){
      if(v==this.sudoku[j][column]){
        return false;
      }
    }
    return true;
  }

  boolean check_grid(int r,int c, int v){
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        if(this.sudoku[(r/3)*3+i][(c/3)*3+j]==v){
          return false;
        }
      }
    }

    return true;
  }


  boolean check_validity(){

    HashSet<Integer>[] grid = new HashSet[9];

    for (int i = 0; i < grid.length; ++i)
        grid[i] = new HashSet<Integer>();   

    //row-wise validity check
    for(int i=0;i<9;i++){
      HashSet<Integer> elements = new HashSet<Integer>();
      for(int j=0;j<9;j++){
        //&& !(this.sudoku[i][j]>=0 && this.sudoku[i][j]<=9)
        if (this.sudoku[i][j]!=0 &&( !elements.add(this.sudoku[i][j]) || !((HashSet<Integer>)grid[(((i/3)*3)+(j/3))]).add(this.sudoku[i][j])) ){
          return false;
        }

      }
      elements.clear();
    }


    for(int i=0;i<9;i++){
      HashSet<Integer> elements = new HashSet<Integer>();
      for(int j=0;j<9;j++){
        if (this.sudoku[j][i]!=0 && !elements.add(this.sudoku[j][i])){
          return false;
        }

      }
      elements.clear();
    }
    return true;
  }

boolean solve_sudoku(int i,int j){
  //System.out.println("Welcome"+i+" "+j);
  if((i>8)||(j>8)){
    return false;
  }

  int nexti=i,nextj=j;
  if(j==8 && i==8){
    nexti=-1;
    nextj=-1;
  }
  else if(j==8){
    nexti+=1;
    nextj=0;
  }
  else{
    nextj+=1;
  }

  if(this.sudoku[i][j]==0){
    for(int k=1;k<=9;k++){
      if(this.check_row(i,k) && this.check_column(j,k) && this.check_grid(i,j,k)){
        this.sudoku[i][j]=k;
        if(i==8 && j==8){
          return true;
        }
        if(this.solve_sudoku(nexti,nextj)){
          return true;
        }
        this.sudoku[i][j]=0;

      }
    }
      //System.out.println(i+" "+j);
    return false;
  }
  else{
        if(i==8 && j==8){
          return true;
        }
        return this.solve_sudoku(nexti,nextj);
  }

}
void print_solution(){
  System.out.println("Solution to given sudoku puzzle is: ");
  for(int i=0;i<9;i++){
    System.out.println(Arrays.toString(this.sudoku[i]));
  }
}

}


class Main {
  public static void main(String[] args)
   {
     int[][] prog={{3, 0, 6, 5, 0, 8, 4, 0, 0}, 
                      {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
                      {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
                      {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
                      {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
                      {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
                      {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
                      {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}}; 
    Sudoku s=new Sudoku();
    //Sudoku s=new Sudoku(prog);
    if(s.check_validity()){
      System.out.println("Sudoku puzzle is valid");
      if(s.solve_sudoku(0, 0)){
        s.print_solution();
      }else{
        //s.print_solution();
        System.out.println("Solution does not exist!");

      }
    }
    else{
      System.out.println("Sudoku puzzle is invalid");
    }
  }
}


