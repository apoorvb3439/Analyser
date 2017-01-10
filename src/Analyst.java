/*
Analyser : To Analyse the complexity of your code

@Author : Apoorv

Documentation is given

Read it

*/


//package com.Apoorv.Analyser;       ////////////////// thats the package where all classes lies
import java.lang.*;
import java.awt.Color;

public abstract class Analyst   ///////////// thats the abstract class, user class should inherit it 
{

public abstract void func(long inputSize);   ///////////thats the function you should define

private long MAX;								///// thats the max Input Size for program to run
private long MIN;								///// thats the min Input Size for program to run

public static long maxDefault=100000000L;		//// Max Default Input Size

public static long minDefault=100L;				//// Min Default Input Size


private double[] _Time;						/////Array to store Running Time for given input
private double[] _logTime;					/////Array to store Log Running Time for given input
private double[] _n;						/////Array to store Input
private double[] _logn;						/////Array to store Log of Input

/*

relation used : T(n)= c * pow(n,b)
				or T(n)= constant * Math.pow(n,complexity)
*/

public double complexity;				//// variable to store final result of complexity

public double constant;				//// variable to store value of constant in complexity relationship

private double[] _Tn;					/*Arrays to stote Running Time */
private double[] _Tn2;					/*for refrence plots of O(n),O(n^2),O(logn),O(nlogn)*/
private double[] _Tlogn;
private double[] _Tnlogn;


public Analyst(){						//// Constructor for class
	
	this.MIN=minDefault;
	this.MAX=maxDefault;

	
}


public long getMaxDefault(){			//// function to get Max Default Value for Input Size
	
	return MAX;

}
public long getMinDefault(){			//// function to get Min Default Value for Input Size
	
	return MIN;
	
}




public void setMaxDefault(long maxdef){			//// function to set Max Default Value for Input Size
	
	this.MAX=maxdef;

}
public void setMinDefault(long mindef){			//// function to set Min Default Value for Input Size
	
	this.MIN=mindef;
	
}





  public void analyse(){    //// Default method that will analyse the function Complexity
	
	analyse(MIN,MAX);
  
  }

  public void analyse(long _Min,long _Max)			//// method that will analyse the function Complexity 
{													//// for inputSize between _Min,_Max
  
	/////////////////////////////
  
  MAX=_Max;
  MIN=_Min;
  
	/////////////////////////////
  int _testCases = (int)((Math.log(_Max)-Math.log(_Min))/Math.log(2)+1);  // No. of TestCases for plots

  _Time=new double[_testCases];
  _logTime=new double[_testCases];
  _n=new double[_testCases];
  _logn=new double[_testCases];
  
  /////////////////


  _Tn2=new double[_testCases];
  _Tlogn=new double[_testCases];
  _Tn=new double[_testCases];
  _Tnlogn=new double[_testCases];


  ////////////////
  
  int _i=0;						// Counter
  
  ///////////////////
  
  double _max=0,_min=0;							////min and max values for plots of T(n) vs n
  double _logmin=0,_logmax=0;					////min and max values for plots of logT(n) vs logn
  
  /////////////////////
  
  long _tempInputSize = _Min;					
  double _t1,_t2;


    while(_i<_testCases)
    {

      _n[_i]=_tempInputSize;
      _t1=System.nanoTime();

      func(_tempInputSize);

      _t2=System.nanoTime();
      _Time[_i]=_t2-_t1;

      _logTime[_i]=(Math.log(_Time[_i])/Math.log(2));

      _logn[_i]=(Math.log(_n[_i])/Math.log(2));
	
		////////////////////

	  _Tn[_i]=_n[_i];
	  _Tn2[_i]=_n[_i]*_n[_i];
	  _Tlogn[_i]=(Math.log(_n[_i]))/(Math.log(2));
	  _Tnlogn[_i]=(_n[_i]*Math.log(_n[_i]))/(Math.log(2));


	
	  /////////////////////////

		
		////////////
		if(_max< _Time[_i]){
			 _max =_Time[_i];
		}
		
		
		if(_i==0){
				 _min =_Time[_i];
			
		}
		
		if(_min> _Time[_i]){
			 _min =_Time[_i];
		}
		
		
		/////////////

      _tempInputSize=_tempInputSize*2;

      _i++;
    }

		complexity=Math.log((_Time[_testCases-1]/_Time[_testCases-2]))/Math.log(2);
		
		constant=Math.pow((_n[_testCases-1]),complexity);
      
	  if(constant!=0){
		  constant=(_Time[_testCases-1])/constant;
	  }
	  else{
		  System.out.println("Complexity is too small");
		  constant=1;
		  
	  }
	  
	  _logmin=(Math.log(_min)/Math.log(2));
      _logmax=(Math.log(_max)/Math.log(2));

	////////////////////////////////////////////Plotting the data



      Plot plot = Plot.plot(Plot.plotOpts().
          title("Code Complexity Analyser").

			width(1000).
			height(600).

		  legend(Plot.LegendFormat.TOP)).

      xAxis("Log(n)", Plot.axisOpts().
          range(this._logn[0],this._logn[_testCases-1])).
      yAxis("Log(T(n))", Plot.axisOpts().
          range(_logmin,_logmax)).
      series("log-log", Plot.data().
          xy(this._logn,this._logTime),
          Plot.seriesOpts().
              marker(Plot.Marker.DIAMOND).
              markerColor(Color.GREEN).
              color(Color.BLACK));




    try{
      // saving LogAnalysis.png
      plot.save("LogAnalysis", "png");
    }catch(Exception e){
      System.out.print("Not Saved Error");
    }





	plot = Plot.plot(Plot.plotOpts().
				title("Code Analyser").
				width(1000).
				height(600).
				/*
				bgColor(Color.GRAY).
				fgColor(Color.BLUE).
				 */
				legend(Plot.LegendFormat.TOP)).
			xAxis("Input Size", Plot.axisOpts().
				range(this._n[0],this._n[_testCases-1])).
			yAxis("Running Time", Plot.axisOpts().
				range(_min,_max)).
			series("Actual", Plot.data().
				xy(this._n,this._Time),
				Plot.seriesOpts().
					lineWidth(3).
					marker(Plot.Marker.NONE).
					markerColor(Color.ORANGE).
					color(Color.GREEN))






			 .series("O(n)", Plot.data().
				xy(this._n, this._Tn),
				Plot.seriesOpts().
					//line(Line.NONE).
					marker(Plot.Marker.CIRCLE).
					markerColor(Color.MAGENTA).
					color(Color.RED)).



			series("O(n^2)", Plot.data().
				xy(this._n, this._Tn2),
				Plot.seriesOpts().
					//line(Line.DASHED).
					lineDash(new float[] { 3.0f, 5.0f }).
					marker(Plot.Marker.DIAMOND)).



			series("O(nlog(n))", Plot.data().
				xy(this._n, this._Tnlogn),
				Plot.seriesOpts().
					//line(Line.NONE).
					marker(Plot.Marker.DIAMOND)
					.markerColor(Color.RED)).



			series("O(log(n))", Plot.data().
				xy(this._n, this._Tlogn),
				Plot.seriesOpts().
					//line(Line.NONE).
					color(Color.BLACK).
					marker(Plot.Marker.CIRCLE).
					markerColor(Color.CYAN).
					markerSize(12));

/////////////////////////////////////////////


    try{
      // saving Analysis.png
      plot.save("Analysis", "png");
    }catch(Exception e){
      System.out.print("Not Saved Error");
    }

  }



}
