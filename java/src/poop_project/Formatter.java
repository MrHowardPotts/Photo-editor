package poop_project;

import java.io.IOException;

public abstract class Formatter {

		abstract public void write(String location, Image image)throws IOException;
		
		abstract public Layer read(String location)throws IOException;
}
