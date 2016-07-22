package entities;

import java.util.LinkedList;
import java.util.List;

public class ActorsList<AbstractActor> {
	private List<AbstractActor> actors_list;
	
	public ActorsList(){
		actors_list = new LinkedList<AbstractActor>();
	}
	
	public void add(AbstractActor actor){
		actors_list.add(actor);
	}
	
	public AbstractActor getActor(int index){
		return actors_list.get(index);
	}
	
	
	public List<AbstractActor> getList(){
		return actors_list;
	}
	
	public int size(){
		return actors_list.size();
	}
	
}
