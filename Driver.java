import com.Apoorv.Analyser.*;
import java.util.ArrayList;
public class Driver extends Analyst
{

  @Override
  public void func(long n)
  {
	 long ij=1;
    while (ij<n) {
		ij=ij+1;
	}
	
  }


  public static void main(String[] args) {
  
    Driver analyst = new Driver();

    System.out.println("\nThe Code Complexity is : N^ "+analyst.complexity);

  }

}
