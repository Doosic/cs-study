package recursion.factorial;

public class Factorial {

  public Factorial() {}

  public int factorialFunction(int number){
    if(number == 1 || number == 0){
      return 1;
    }else{
      return number * factorialFunction(number - 1);
    }
  }
}
