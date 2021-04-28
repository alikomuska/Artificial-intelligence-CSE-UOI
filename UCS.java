import java.util.Scanner;
import java.util.Collections;
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
    
    
    public static void printPath(Node finalNode) {
    	ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
    	Node pointer = finalNode;
    	while(pointer.getFather()!=null) {
    		temp.add(pointer.getState());
    		pointer = pointer.getFather();
    	}
    	temp.add(pointer.getState());
    	System.out.print("Path is: " + temp.get(temp.size()-1));
    	
    	for(int i=temp.size()-2; i>-1; i--) {
    		System.out.print("-> " + temp.get(i));	
    	}
    	
    	return;	
    }

	
    
    
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Node> searchFrontier = new ArrayList<Node>();  //metopo anazithshs
		HashMap<ArrayList<Integer>,Integer> visitedNodes = new HashMap<ArrayList<Integer>,Integer>();  // kleisto sinolo pou parallila tha krataei to depth tou kathe kombou pou tha exoume episkefthei
		
		ArrayList<Integer> startingState = new ArrayList<Integer>();  						 //arxikh katastash
		ArrayList<Integer> finalState = new ArrayList<Integer>();  		
		
		
		
		System.out.println("Give me N");    //zhtao to N
		int N = Integer.parseInt(input.next()); 
	
		
		System.out.println("Give me one by one the numbers of the Starting State");
		for(int i=0; i<N; i++) {        //pairno ena ena tous arithmous me thn shra ths arxikhs katastashs
			int temp = Integer.parseInt(input.next()); 
			startingState.add(temp);	
		}
		System.out.println("Calculating...");
		

		Node startingNode = new Node(null,startingState);
		
		
		searchFrontier.add(startingNode);    //bazo thn arxikh katastash sto metopo anazitishs
		visitedNodes.put(startingNode.getState(),0);
	
		
		for(int n=1; n<=N; n++) {
			finalState.add(n);
		}

		int nodeExtension=0;

		while(searchFrontier.size()!=0) { // UCS
			Node minDepth = searchFrontier.get(0);
			int depthOfMin = visitedNodes.get(minDepth.getState());
			for(Node min: searchFrontier) {	
				if(visitedNodes.get(min.getState())<depthOfMin) {
					minDepth = min; 
					depthOfMin = visitedNodes.get(min.getState());
				}	
			
			}
			
			nodeExtension++;
					
			for(int k=2; k<=N; k++) {   /// prosthese kombous pou the exeis ksanadei sto metopo
				ArrayList<Integer> temp;
				if(visitedNodes.containsKey(temp = t(minDepth.getState(),k))==false){
					Node tempNode = new Node(minDepth,temp);
					if(finalState.equals(temp)){    //tsekaro an o kombos pou pao na epektino einai h telikh katastash
						System.out.println("Final Node Found!");
						printPath(tempNode);
						System.out.println("\nDepth of Final State: " + (visitedNodes.get(minDepth.getState())+1));
						System.out.println("Nodes extensions: " + nodeExtension);
						return;
					}
					searchFrontier.add(tempNode);
					visitedNodes.put(t(minDepth.getState(),k),(visitedNodes.get(minDepth.getState())+1));		
				}
			}
			
			searchFrontier.remove(minDepth); // bgazo to kombo pou epektina
		}
		
	}

 }
