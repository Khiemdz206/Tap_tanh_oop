import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

abstract class Apartment{
	String code;
	int priceUnit;
	int area; 
	public Apartment(String code, int priceUnit, int area) {
		this.code = code; this.priceUnit = priceUnit; this.area = area;
	}
	abstract int price();

	public void updatePriceUnit(int priceU) { this.priceUnit = priceU;}	
	
}
class Studio extends Apartment{
	public Studio(String code, int priceUnit, int area) {
		super(code, priceUnit, area);
	}
	@Override
	public int price() {
		return priceUnit*area;
	}	
}
class Duplex extends Apartment{
	int area2;
	int priceUnit2;	
	public Duplex(String code, int priceUnit, int area, int priceUnit2, int area2) {
		super(code, priceUnit, area);
		this.priceUnit2 = priceUnit2;
		this.area2 = area2;
	}
	@Override
	public int price() {
	    return priceUnit*area + priceUnit2*area2;
	}	
}
public class Main {
	List<Apartment> apartments;
	public void run() {
		try {
			apartments = new ArrayList<Apartment>();
			Scanner in = new Scanner(System.in);
			while(true) {
				String line = in.nextLine();
				if(line.equals("#")) break;
				String[] s = line.split(" ");
				if(s[0].equals("ADD")) {
					if(s[1].equals("Studio")) {
						Studio studio = new Studio(s[2],Integer.valueOf(s[3]), 
								Integer.valueOf(s[4]));
						apartments.add(studio);
					}else if(s[1].equals("Duplex")) {
						Duplex duplex = new Duplex(s[2],Integer.valueOf(s[3]), 
								Integer.valueOf(s[4]),Integer.valueOf(s[5]),Integer.valueOf(s[6]));
						apartments.add(duplex);
					}
				}else if(s[0].equals("UPDATE_PRICE_UNIT")) {
					for(Apartment ap: apartments) {
						if(ap.code.equals(s[1])) {
							ap.updatePriceUnit(Integer.valueOf(s[2]));
						}
					}
				}else if(s[0].equals("VIEW_PRICE")) {
					boolean ok = false;
					for(Apartment ap: apartments) {
						if(ap.code.equals(s[1])) {
							System.out.println(ap.price());
							ok = true;
						}
					}
					if(!ok) {
						System.out.println("NULL");
					}
				}else if(s[0].equals("VIEW_TOTAL_PRICE")) {
					int total = 0;
					for(Apartment ap: apartments) {
						total += ap.price();
					}
					System.out.println(total);
				}
			}
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Main app = new Main();
		app.run();
	}

}

