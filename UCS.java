import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class UCS{
	
	public static String toString(ArrayList<Integer> list) {
		String strg = "";
		for(int i=0; i<list.size(); i++) {
			strg = strg + list.get(i);
		}
		return strg;
	}

	public static ArrayList<Integer> T(ArrayList<Integer> list, int n){		
		return list;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> searchFrontier = new ArrayList<ArrayList<Integer>>();  //metopo anazithshs
		ArrayList<Integer> startingState = new ArrayList<Integer>();   
		ArrayList<Integer> extra = new ArrayList<Integer>();       //arxikh katastash
		HashMap<String,Integer> visitedNodes = new HashMap<String,Integer>();                // kleisto sinolo pou parallila tha krataei to depth tou kathe kombou pou tha exoume episkefthei
		
		
		
		System.out.println("Give me N");    //zhtao to N
		int N = Integer.parseInt(input.next()); 
		
		System.out.println("Give me one by one the numbers of the Starting State");
		
		for(int i=0; i<N; i++) {        //pairno ena ena tous arithmous me thn shra ths arxikhs katastashs
			int temp = Integer.parseInt(input.next()); 
			startingState.add(temp);	
		}
		searchFrontier.add(startingState);    //bazo thn arxikh katastash sto metopo anazitishs
		visitedNodes.put(toString(startingState),0);
		
		/**
		extra.add(4);
		extra.add(3);
		extra.add(2);
		searchFrontier.add(extra);
		visitedNodes.put(toString(extra),-1);**/
		
		
		System.out.println(toString(startingState)); //to be deleted


		while(searchFrontier.size()!=0) { // UCS
			ArrayList<Integer> minDepth = searchFrontier.get(0);
			int depthOfMin = visitedNodes.get(toString(searchFrontier.get(0) ));
			for(ArrayList<Integer> min: searchFrontier) {	
				if(visitedNodes.get(toString(min))<depthOfMin) {
					minDepth = min; 
					depthOfMin = visitedNodes.get(toString(min));
				}	
			}	
						/// prosthese kombous sto metopo
			for(int k=2; k<=N; k++) {
				if(searchFrontier.contains(toString(T(minDepth,k)))==false){
					searchFrontier.add(T(minDepth,k));
					visitedNodes.put(toString(T(minDepth,k)),(visitedNodes.get(toString(minDepth))+1));
				}
			}
			break;
			
			
		}
		
	}

}

