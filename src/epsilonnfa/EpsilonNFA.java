 
package epsilonnfa;

import java.util.Scanner;

/**
 *
 * @author ABHISHEK
 */
public class EpsilonNFA {
 
    int[][][] EpsNFA;
    
    int states;
    int symbols;
    int finalStates;
    
    
    int[] symbolArray;
    int[] markFinalStates;
    
    String input;
    
    int[] currentStates;
    int[] temp;
    
    int[][] EpsClosure;
    
     int indexEC = 0;
    int tempEC = 0;
    
    int ind;
    int ind1;
    
    public void init()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the Number of States : ");
        states = scanner.nextInt();
        
        System.out.println("Enter the Number of Symbols(Symbols will be from 0 to N-1 automatically and Last symbol will represent Epsilon) : ");
        symbols = scanner.nextInt();
        
        symbolArray = new int[symbols];
        
        for(int i = 0; i < symbols; i++)
        {
            symbolArray[i] = i;
        }
        
        
        System.out.println("Enter Number of Final States : ");
        finalStates = scanner.nextInt();
        
        markFinalStates = new int[states];
        
        for(int i = 0; i < states; i++)
        {
            markFinalStates[i] = 0;
        }
        
      
        for(int i = 0 ; i < finalStates ; i++)
        {
            System.out.println("Enter the No of the state which is the Final state : ");
            
            int stateFinal = scanner.nextInt();
            
            markFinalStates[stateFinal] = 1;
        }
        
        
        EpsNFA = new int[states][symbols][states];
        
        for(int i = 0; i < states; i++)
        {
            for(int  j = 0; j < symbols; j++)
            {
                for(int k = 0; k < states; k++)
                {
                    EpsNFA[i][j][k] = -1;
                }
            }
        }
        
        EpsClosure = new int[states][10];
        for(int i = 0; i < states; i++)
        {
           for(int j = 0; j < 10; j++)
           {
               EpsClosure[i][j] = -1;
           }
        }
        
        currentStates = new int[50];
        temp = new int[50];
        
    }
    
    public void getTransitions()
    {        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Define the relations for State and Input Symbol : ");
        
        for(int i = 0; i < states; i++)
        {
            for(int  j = 0; j < symbols; j++)
            {
                int nreln;
                
                System.out.println("Enter the Number of relations from State " + i + " for Input Symbol : " + j + " : ");
                
                nreln = scanner.nextInt();
                
                for(int k = 0; k < nreln; k++)
                {
                    System.out.println("Enter the Relation No : " + k + " for the Transition(ie Next State)");
                    
                    EpsNFA[i][j][k] = scanner.nextInt();
                }
            }
        }
        
    }
    
    public void computeEC(int state)
    {
        EpsClosure[indexEC][tempEC++] = state;
        
        int k = 0;
        while(EpsNFA[state][symbols-1][k] != -1)
        {
            if(checkIfRepeated(state,EpsNFA[state][symbols-1][k]))
            {
                return;
            }
            computeEC(EpsNFA[state][symbols-1][k]);
            k++;
        }
    }
    
    public boolean checkIfRepeated(int state, int value)
    {
        boolean  check = false;
        
        for(int i = 0 ; i < 10; i++)
        {
           if(EpsClosure[state][i] == value)
           {
               check = true;
               break;
           }
        }
        
        return check;
    }
    
    public void displayEC()
    {
        for(int i = 0; i < states; i++)
        {
            System.out.println("");
            for(int j = 0; j < 10; j++)
            {
                if(EpsClosure[i][j]!=-1)
                {
                    System.out.printf(EpsClosure[i][j] + " ");
            
                }
            }
        }
    }
    
    public void displayTransition()
    {
        System.out.println("State    Symbol   No  Next");
        for(int i = 0; i < states; i++)
        {
            //System.out.println("");
            for(int  j = 0; j < symbols; j++)
            {
               // System.out.println("");
                for(int k = 0; k < states; k++)
                {
                    System.out.printf(EpsNFA[i][j][k] + " ");
                }
            }
        }
        
    }
    
    public void implement()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of String to be tested");
        
        int n = scanner.nextInt();
        
        for(int k = 0; k < n; k++)
        {
            reset();

        System.out.println("\nEnter the Input Array to be Checked(Terminate the Array with 99 in the end) : ");
        //String s = scanner.next();
        //int l = s.length();
        
        int arr[] = new int[50];
        
        for(int i = 0; i < 50; i++)
        {
           if( (arr[i] = scanner.nextInt()) == 99)
           {
               break;
           }
        }
        System.out.println("Done with Input String");
        int a;
        for( a= 0; a < states; a++)
        {
            if(EpsClosure[0][a]!=-1)
            {
               // System.out.println("Null Pointer Exception itseems");
                
                currentStates[a] = EpsClosure[0][a];
                
                System.out.println(currentStates[a]);
            }
            else
            {
                
                System.out.println("Value is -1");
                break;
            }
        }
        
        
        ind = a;
        System.out.println("ind value is : " + a);
        
           boolean flag2 = false;
        for(int i = 0; i < 50; i++)
        {
            System.out.println("Are u in loop1?");
           
            if(arr[i] == 99)
            {
                break;
            }
            
            int element = arr[i];
            
            System.out.println("Element No : " + i + " is : " + element);
            
            ind1 = 0;
            
            for(int l1 = 0; l1 < ind; l1++)
            {
                System.out.println("Are u in loop2?");
                
                int j = 0; 
                
                while(EpsNFA[currentStates[l1]][element][j] != -1)
                {
                    System.out.println("Inside While?");
                    System.out.println(EpsNFA[currentStates[l1]][element][j]);
                    temp[ind1++] = EpsNFA[currentStates[l1]][element][j];
                    j++;
                }
            }

            int nextInd = 0;

            for(int l1 = 0; l1 < ind1; l1++)
            {
                if(l1 == 0)
                {                   
                    currentStates[l1] = temp[l1];
                    System.out.println(currentStates[l1]);
                    nextInd = modifyCurrStates(currentStates[l1],l1+1);
                }
                else
                {
                    System.out.println("Value of temp : " + temp[l1]);
                    currentStates[nextInd] = temp[l1];
                    System.out.println(currentStates[nextInd]);
                    nextInd =  modifyCurrStates(currentStates[nextInd],nextInd+1);
                }
                    
                
                
            }
            
         
            if(nextInd != 0)
            {
            ind = nextInd;    
            }
            else
            {
               // System.out.println("Entered String is Rejected");
                flag2 = true;
                break;
            }
        }
        
        int flag = 0;
        
        for(int x = 0; x < 50; x++)
        {
            System.out.println("Are u in final Loop");
            System.out.println(currentStates[x]);
            if(currentStates[x]!= -1)
            {
                if(markFinalStates[currentStates[x]] == 1)
                 {
                      flag = 1;
                      break;
                 }
            }
        }
        
        if(flag == 1 && flag2 == false)
        {
            System.out.println("Entered String is Accepted");
        }
        else
        {
            System.out.println("Entered String is Rejected");
        }
        }
        
    }
    public void reset()
    {
        for(int c = 0; c < 50; c++)
        {
            currentStates[c] = temp[c] = -1;
        }
    }
    public int modifyCurrStates(int state, int index)
    {
        for(int i = 0; i < states; i++)
        {
            if(EpsClosure[state][i] != -1)
            {
                currentStates[index++] = EpsClosure[state][i];
            }
        }
        return index;
        
        
    }
    public static void main(String[] args) {
 
        EpsilonNFA enfa = new EpsilonNFA();
        
        
        enfa.init();
        
        enfa.getTransitions();
        
       
        System.out.println("Displaying Transition Table");
        
        enfa.displayTransition();
        
        System.out.println("Done Display Transition");
        
        for(int i =0 ; i < enfa.states; i++)
        {
            enfa.tempEC = 0;
            enfa.indexEC = i;
            enfa.computeEC(i);
        }
        
        System.out.println("\nEpsilon Closure's of each state");
        enfa.displayEC();
        
        enfa.implement();
        
         
    }
    
}
