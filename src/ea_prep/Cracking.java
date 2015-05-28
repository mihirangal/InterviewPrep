package ea_prep;

import java.util.*;

public class Cracking

{
    public HashMap<Character, ArrayList<Character>> chars;
    public Cracking()
    {
	chars= new HashMap<Character,ArrayList<Character> >();
	ArrayList<Character> one= new ArrayList<Character>();
	ArrayList<Character> two= new ArrayList<Character>();
	ArrayList<Character> three = new  ArrayList<Character>();
	ArrayList<Character> four = new ArrayList<Character>();
	ArrayList<Character> five = new ArrayList<Character>();
	ArrayList<Character> six = new ArrayList<Character>();
	two.add('a');
	two.add('b');
	two.add('c');
	three.add('d');
	three.add('e');
	three.add('f');
	four.add('g');
	four.add('h');
	four.add('i');
	five.add('j');
	five.add('k');
	five.add('l');
	six.add('m');
	six.add('n');
	six.add('o');
	
	
	
	chars.put('2', two);
	chars.put('3', three);
	chars.put('4', four);
	chars.put('5', five);
	chars.put('6', six);
    }
    public void printParen( int n)
    {
	this.printParenHelper(n-1, n, "(");
    }
    public void printParenHelper(int left_count, int right_count, String current)
    {
	if(left_count==0&& right_count==0)
	{
	    System.out.println(current);
	}
	else if (left_count== 0)
	{
	    for ( int i=0;i<right_count;i++)
	    {
		current= current+")";
	    }
	    printParenHelper(0,0, current);

	}
	else
	{

	    if(left_count==right_count)
	    {
		printParenHelper(left_count-1,right_count, current+"(");
	    }
	    else
	    {
		printParenHelper(left_count-1,right_count, current+"(");
		int diff = right_count-left_count;
		for( int i =0; i< diff; i++)
		{
		    current = current+")";
		}
		printParenHelper(left_count,right_count-diff, current);
	    }
	}
    }
    public int numChangeWays(int amount, int denom )
    {
	if(amount ==0)
	{
	    return 1;
	}
	if( amount<0)
	{
	    return 0;
	}
	int quarter= amount-25;
	int dime= amount-10;
	int nickel= amount-5;
	int penny = amount -1;
	int totalways=0;
	if( denom ==25)
	{
	    totalways= totalways+numChangeWays(quarter,25);
		    totalways += numChangeWays(dime,10)+numChangeWays(nickel,5)+numChangeWays(penny,1);
	}
	else if( denom ==10)
	{
	    totalways= totalways+numChangeWays(dime,10);
	    totalways += numChangeWays(nickel,5)+numChangeWays(penny,1);
	}
	else if ( denom ==5)
	{
	    totalways= totalways+numChangeWays(nickel,5);
	    totalways += numChangeWays(penny,1);
	}
	else if( denom ==1 )
	{
	    totalways += numChangeWays(penny,1);
	}
	//totalways= totalways+numChangeWays(quarter,25)+numChangeWays(dime,10)+numChangeWays(nickel,5)+numChangeWays(penny,1);
	return totalways;
    }
    /**
     * Given a sequence of numbers (34128) and an input map such as a dial pad on a phone (2->[a,b,c], 3->[d,e,f], 4->[g,h,i]) 
     * write an algorithm to return all possible words from the sequence.

	E.g. Input: 232
	Output: [ada, adb, adc, aea, aeb, aec, afa, afb, afc, bda, bdb, bdc, bea, beb, bec, 
	bfa, bfb, bfc, cda, cdb, cdc, cea, ceb, cec, cfa, cfb, cfc]  

     */
    public ArrayList<String> getPerms( int input)
    {
	ArrayList<String> retval= new ArrayList<String>();
	String number = String.valueOf(input);

	char[] digits1 = number.toCharArray();
	
	ArrayList<Character> digs= new ArrayList<Character>();
	for ( int i =0;i< digits1.length;i++)
	{
	    digs.add(digits1[i]);
	}
	//System.out.println(digs);
	getHelper(digs, retval, 0,"");
	return retval;
	
    }
    public void  getHelper(ArrayList<Character> number, ArrayList<String > in, int currlength, String curr)
    {
	//System.out.println(number);
	//System.out.println(currlength);
	if(currlength>=number.size())
	{
	    in.add(curr);
	}
	else
	{

	    if( number.size()>0&&chars.containsKey(number.get(0)))
	    {
		char keyval= number.get(currlength);
		//System.out.println(chars.get(keyval));
        	    for(char c: chars.get(keyval))
        	    {
        		//System.out.println(chars.get(keyval).size());
        		//System.out.println(chars.get(keyval).get(i));
        		//System.out.print(curr);
        		getHelper(number, in, currlength+1,curr+c);
        	    }
	    }
	}
	

	
    }
    /**
     * Given a sorted array [0-99]
	With input: [1, 5, 45, 86]
	Write a function that prints the empty regions, example Output: “0,2-4,6-44,46-85,87-99”  
     * @param input
     * @return
     */
    public ArrayList<String> findIntervals( ArrayList<Integer> input)
    {
	ArrayList<String> retval = new ArrayList<String>();
	int startpoint=0;
	int endpoint=-1;
	
	for ( int i =0;i< input.size();i++)
	{
	    endpoint = input.get(i)-1;
	    if( endpoint==startpoint)
	    {
		retval.add(startpoint+"");
		
	    }
	    else
	    {
		
		retval.add((startpoint)+"-"+(endpoint));
		startpoint= endpoint+2;
		
	    }
	}
	if( endpoint!=99)
	{
	    retval.add((endpoint+1)+"-"+99);
	}
	else
	{
	    retval.add( 99+"");
	}
	
	return retval;
    }
    public static void main(String[]args)
    {
	Cracking c = new Cracking();
	c.printParen(7);
	System.out.println(c.numChangeWays(100,25) );
	System.out.println(c.getPerms(2425435) );
	ArrayList<Integer> sample = new ArrayList<Integer>();
	sample.add(2);
	sample.add(5);
	sample.add(45);
	sample.add(86);
	sample.add(98);
	System.out.println(c.findIntervals(sample));
	
	
    }
}
