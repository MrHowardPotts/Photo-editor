package poop_project;

import java.util.Vector;

public class CompositeOperation extends Operation{

	Vector<Operation> operacije = new Vector<Operation>();
	Vector<String> savedData;
	
	public CompositeOperation(Vector<Operation> op) {
		super("Composite Default");
		operacije = op;
	}
	
	@Override
	public void work(Image image) {
		for(Operation op: operacije) {
			op.work(image);
		}
		
	}
	
	

}
