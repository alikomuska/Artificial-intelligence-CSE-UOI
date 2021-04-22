import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class UCS{
	
	
    public static ArrayList<Integer> reverse(ArrayList<Integer> al)
    {

        for (int i = 0, j = al.size() - 1; i < j; i++)
        {
            al.add(i, al.remove(j));
        }

        return(al);
    }

    public static ArrayList<Integer> join(ArrayList<Integer> ral, ArrayList<Integer> ar)
    {
        ArrayList<Integer> b = new ArrayList<Integer>(ral);
        b.addAll(ar);
        return(b);
    }

    public static ArrayList<ArrayList<Integer>> split(ArrayList<Integer> list,int k)
    {
        ArrayList<Integer> first = new ArrayList<Integer>();
        ArrayList<Integer> second = new ArrayList<Integer>();

        int size = list.size();

        for (int i = 0; i < k; i++)
            first.add(list.get(i));

        for (int i = k; i < size; i++)
            second.add(list.get(i));

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        lists.add(first);
        lists.add(second);

        return(lists);
    }

    public static ArrayList<Integer> t(ArrayList<Integer> a,int k) {
        ArrayList<ArrayList<Integer>> lists = split(a, k);
        ArrayList<Integer> al = lists.get(0);
        ArrayList<Integer> ar = lists.get(1);

        ArrayList<Integer> ral = reverse(al);
        ArrayList<Integer> b = join(ral, ar);
        return (b);
    }

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> searchFrontier = new ArrayList<ArrayList<Integer>>();  //metopo anazithshs
		ArrayList<Integer> startingState = new ArrayList<Integer>();  						 //arxikh katastash
		ArrayList<Integer> finalState = new ArrayList<Integer>();  		
		HashMap<ArrayList<Integer>,Integer> visitedNodes = new HashMap<ArrayList<Integer>,Integer>();  // kleisto sinolo pou parallila tha krataei to depth tou kathe kombou pou tha exoume episkefthei
		
		
		
		System.out.println("Give me N");    //zhtao to N
		int N = Integer.parseInt(input.next()); 
		
		System.out.println("Give me one by one the numbers of the Starting State");
		
		for(int i=0; i<N; i++) {        //pairno ena ena tous arithmous me thn shra ths arxikhs katastashs
			int temp = Integer.parseInt(input.next()); 
			startingState.add(temp);	
		}
		
		searchFrontier.add(startingState);    //bazo thn arxikh katastash sto metopo anazitishs
		visitedNodes.put(startingState,0);
				
		
		
		for(int n=1; n<=N; n++) {
			finalState.add(n);
		}

		
		
		ArrayList<Integer> minDepth = searchFrontier.get(0);
		int depthOfMin = visitedNodes.get(searchFrontier.get(0));
		

		while(searchFrontier.size()!=0) { // UCS
			minDepth = searchFrontier.get(0);
			depthOfMin = visitedNodes.get(minDepth);
			for(ArrayList<Integer> min: searchFrontier) {	
				if(visitedNodes.get(min)<depthOfMin) {
					minDepth = min; 
					depthOfMin = visitedNodes.get(min);
				}	
			
			}
			
			
			if(finalState.equals(minDepth)){    //tsekaro an o kombos pou pao na epektino einai h telikh katastash
				System.out.println("I found the Final Node");
				System.out.println(minDepth);
				System.out.println("Depth of Final State: " + visitedNodes.get(minDepth));
				break;
			}
			
			
			
								
			for(int k=2; k<=N; k++) {   /// prosthese kombous pou the exeis ksanadei sto metopo
				if(visitedNodes.containsKey(t(minDepth,k))==false){
					searchFrontier.add(t(minDepth,k));
					visitedNodes.put(t(minDepth,k),(visitedNodes.get(minDepth)+1));
				}
			}
			
			searchFrontier.remove(minDepth); // bgazo to kombo pou epektina		
			
			for(ArrayList<Integer> min: searchFrontier) {
				System.out.println(min);
				
			}
			System.out.println("----------------------");
			
		}
		
	}

 }
