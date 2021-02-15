package poop_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PAMFormatter extends Formatter {

	public PAMFormatter() {
		
	}

	@Override
	public void write(String location, Image image) throws IOException {
		
		String header = "P7\n"+
						"WIDTH "+image.getWWidth()+"\n"+
						"HEIGHT "+image.getHHeight()+"\n"+
						"DEPTH 4\n"+
						"MAXVAL 255\n"+
						"TUPLTYPE RGB_ALPHA\n"+
						"ENDHDR\n";
		
		String data = header;
		
		StringBuilder str = new StringBuilder(data);
		
		
		for (int i = 0; i < image.getHHeight();i++ ) {
			for (int j = 0; j < image.getWWidth();j++ ) {
				Pixel pixel_write = image.calculatePixel(i, j);
				str.append((char)pixel_write.getRed()).append((char)pixel_write.getGreen()).append((char)pixel_write.getBlue()).append((char)pixel_write.getAlpha());
				//data += ((char)pixel_write.getRed()) + ((char)pixel_write.getGreen()) + ((char)pixel_write.getBlue()) + ((char)pixel_write.getAlpha());
			}
		}
		File output = new File(location);
		output.createNewFile();
		FileWriter writer = new FileWriter(output);
		writer.write(str.toString());
		writer.flush(); writer.close();
	}
	

	@Override
	public Layer read(String location) throws IOException {
		
		byte[] data = Files.readAllBytes(Paths.get(location));
		String buffer = new String(data);
		
		//std::regex rx("P7WIDTH (.*)HEIGHT (.*)DEPTH (.*)MAXVAL (.*)TUPLTYPE (.*)ENDHDR");//P7WIDTH 128HEIGHT 128DEPTH 4MAXVAL 255TUPLTYPE RGB_ALPHAENDHDR
		
		Pattern regex =Pattern.compile("P7\nWIDTH ([0-9]*)\nHEIGHT ([0-9]*)\nDEPTH ([0-9]*)\nMAXVAL ([0-9]*)\nTUPLTYPE (.*)\nENDHDR\n(.*)");
		Matcher matcher = regex.matcher(buffer);
		//System.out.print(buffer);
		
		boolean flag = matcher.matches();
		System.out.println(flag);
		
		
		int W = Integer.parseInt(matcher.group(1));
		int H = Integer.parseInt(matcher.group(2));
		
//		Layer layer = new Layer(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)));
		Layer layer = new Layer(W,H);
		if(flag) {
			
			byte[] pixels = matcher.group(6).getBytes();
			
			
			for(int i = Integer.parseInt(matcher.group(2))-1 ; i >=0;i--) {
				for(int j =0 ; j < Integer.parseInt(matcher.group(1));j++) {
					layer.pixels.get(i).set(j,
									  new Pixel((pixels[(i*W+j)*4+0] & 255),
												(pixels[(i*W+j)*4+1] & 255),
												(pixels[(i*W+j)*4+2] & 255),
												(pixels[(i*W+j)*4+3] & 255)));
				}
			}
			
			
		}else {
			System.out.println("Greska pri citnaju PAM formata");
		}
		
		
		return layer;
	}

}
