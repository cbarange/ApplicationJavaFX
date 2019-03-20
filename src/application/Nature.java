package application;
	/**
	 * Enum contenant les differents nature de "cour"
	 * @author Clement Baranger
	 * @author Pierre Lemaigre
	 */
public enum Nature {
	TD("TD"),Cours("Cours"),TP("TP");
	private String intitule;
	Nature(String intitule) {
		this.intitule=intitule;
	}
	/**
	 * 
	 * @return un string de la nature
	 */
	public String getString() {
		return intitule;
	}
	
	/**
	 * 
	 * @param m : un string contenant la nature
	 * @return une nature du type Nature
	 */
	public static Nature get(String m) {
		switch (m) {
			case "TP" :return TP;				
			case "TD":return TD;
			case "Cours":return Cours;
		}
		return null;
	}
}
