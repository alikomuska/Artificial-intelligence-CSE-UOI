import java.util.ArrayList;
import java.util.Scanner;

public class Node {
	private Node father;
	private ArrayList<Integer> state; 
	
	public Node (Node father,ArrayList<Integer> state) {
		this.father = father;
		this.state = state;		
	}
	
	public ArrayList<Integer> getState(){
		return this.state;
	}
	
	public Node getFather(){
		return this.father;
	}

}
