package application;

/**
 * Enum contenant les différentes matiere
 * @author Clement Baranger
 *	@author Pierre Lemaigre
 */
public enum Matiere {
	AP("AP"), BD("BD"), CDIN("CDIN"), SI("SI");
	private String intitule;
	Matiere(String intitule) {
		this.intitule = intitule;
	}
	/**
	 * 
	 * @return un string de la matiere
	 */
	public String getString() {
		return intitule;
	}
	/**
	 * 
	 * @param m : un string de la matiere
	 * @return un une matiere du type Matiere
	 */
	public static Matiere get(String m) {
		switch (m) {
			case "BD" :return BD;				
			case "AP":return AP;
			case "SI":return SI;
			case "CDIN":return CDIN;
		}
		return null;
	}

}
