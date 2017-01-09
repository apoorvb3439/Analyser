package com.Apoorv.Analyser;
import java.lang.*;
import java.awt.Color;

abstract class Analyst
{

abstract void func(long inputSize);


public static long maxDefault=100000000L;

public static long minDefault=100L;

private double[] _Time;
private double[] _logTime;
private double[] _n;
private double[] _logn;


private double[] _power;
public double complexity;

private double[] _Tn;
private double[] _Tn2;
private double[] _Tlogn;
private double[] _Tnlogn;


  public Analyst(){
    this(minDefault,maxDefault);
  }

  public Analyst(long _Min,long _Max)
  {

  int _testCases = (int)((Math.log(_Max)-Math.log(_Min))/Math.log(2)+1);

  _Time=new double[_testCases];
  _logTime=new double[_testCases];
  _n=new double[_testCases];
  _logn=new double[_testCases];
  _power = new double[_testCases+1];
  /////////////////


  _Tn2=new double[_testCases];
  _Tlogn=new double[_testCases];
  _Tn=new double[_testCases];
  _Tnlogn=new double[_testCases];


  ////////////////
  int _i=0;

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

      _tempInputSize=_tempInputSize*2;

	  ////////////////////

	  _Tn[_i]=_n[_i];
	  _Tn2[_i]=_n[_i]*_n[_i];
	  _Tlogn[_i]=(Math.log(_n[_i]))/(Math.log(2));
	  _Tnlogn[_i]=(_n[_i]*Math.log(_n[_i]))/(Math.log(2));



	  /////////////////////////


      _i++;
    }

    _i=_testCases/2;
	int _save=_i;
    complexity=0;

    while(_i<_testCases-1)
    {
      _power[_i]=((_logTime[_i+1]-_logTime[_i])/(_logn[_i+1]-_logn[_i]));
      complexity=complexity+_power[_i];
      _i++;

    }

    complexity=complexity/((_testCases-1)-_save);





      Plot plot = Plot.plot(Plot.plotOpts().
          title("Code Complexity Analyser").

			width(1000).
			height(600).

		  legend(Plot.LegendFormat.TOP)).

      xAxis("Log(n)", Plot.axisOpts().
          range(this._logn[0],this._logn[_testCases-1])).
      yAxis("Log(T(n))", Plot.axisOpts().
          range(this._logTime[0],this._logTime[_testCases-1])).
      series("log-log", Plot.data().
          xy(this._logn,this._logTime),
          Plot.seriesOpts().
              marker(Plot.Marker.DIAMOND).
              markerColor(Color.GREEN).
              color(Color.BLACK));




    try{
      // saving sample_minimal.png
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
				range(this._Time[0],this._Time[_testCases-1])).
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
      // saving sample_minimal.png
      plot.save("Analysis", "png");
    }catch(Exception e){
      System.out.print("Not Saved Error");
    }

  }



}
