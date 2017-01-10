/*

This is the Temporary class that user will define



*/

import com.Apoorv.Analyser.*;			//// import this package to use this Library
public class Driver extends Analyst		//// your class must extends Analyst Class
{

  @Override
  public void func(long n)			//// You must overwrite or define this function 
  {									//// where you should write function in this way
									//// that it depends on n as Input Size
	  long ij=1;
	  while(ij<n)
	  {  
		ij=ij+1;
		
		}
	
	
  }


  public static void main(String[] args) {
  

	
	
	Driver analyst = new Driver();			//// make a new Object of your class
	
	//analyst.setMinDefault(10L);				////to set default min Value for Input Size
	//analyst.setMaxDefault(100L);		.	////to set default max Value for Input Size
	
	analyst.analyse();			//// call the analyse function on your class object
	
    System.out.println("\nThe Code Complexity for Input Size between "+analyst.getMinDefault()+" - "+analyst.getMaxDefault()+" is : "+analyst.constant+" N^ "+analyst.complexity);

  }

}
