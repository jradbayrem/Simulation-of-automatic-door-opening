import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Saisie {

	static DateFormat format = new SimpleDateFormat("dd/mm/yyyy");

	/********* MAIN ***********/
	public static void main(String[] arg) {

		/* SAISIE PERSONNE *//* SAISIE DATE */

		int rep = 0;
		String reponse = "y";
		String acces;

		java.util.Scanner entree = new java.util.Scanner(System.in);
		File xx = new File("src/testing.xml");
		System.out.println("Acces :");
		System.out.println("Admin ===> 1");
		System.out.println("User  ===> 2");
		acces = entree.next();
		switch (acces) {
		case "1": {
		while (reponse.equals("y") || reponse.equals("Y")) {

			
				
			

				System.out.println("Donnez votre prénom et votre nom");
				String prenomP = entree.next();
				String nomP = entree.next();

				java.util.Date dateP = null;
				while (dateP == null) {
					System.out.print("date (dd/mm/yyyy): ");
					String ns = entree.next();

					try {
						dateP = format.parse(ns);
					} catch (ParseException e) {
						System.out.println("erreur saisie date");
					}
				}

				// affichage
				System.out.println("nom: " + nomP);
				System.out.println("prenom: " + prenomP);
				System.out.println("date: " + format.format(dateP));

				/******************** XML ***********************************/

				try {

					DocumentBuilderFactory docFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder docBuilder = docFactory
							.newDocumentBuilder();

					// root elements
					Document doc = docBuilder.newDocument();
					Element employee = doc.createElement("employee");
					doc.appendChild(employee);

				

					// set attribute to staff element
					Attr attr = doc.createAttribute("id");
					attr.setValue("1");
					employee.setAttributeNode(attr);

					// shorten way
					// staff.setAttribute("id", "1");

					// prenom elements
					Element prenom = doc.createElement("prenom");
					prenom.appendChild(doc.createTextNode(prenomP));
					employee.appendChild(prenom);

					// nom elements
					Element nom = doc.createElement("nom");
					nom.appendChild(doc.createTextNode(nomP));
					employee.appendChild(nom);

					// dateNaissance elements
					Element dn = doc.createElement("dateNaissance");
					dn.appendChild(doc.createTextNode(dateP.toString()));
					employee.appendChild(dn);

					// write the content into xml file
					TransformerFactory transformerFactory1 = TransformerFactory
							.newInstance();
					Transformer transformer1 = transformerFactory1
							.newTransformer();
					DOMSource source1 = new DOMSource(doc);
					// StreamResult result = new StreamResult(new
					// File("C:\\file.xml"));
					StreamResult result = new StreamResult(xx);
					transformer1.transform(source1, result);

					System.out.println("Personne ajoutée");
					System.out
							.println("voulez-vous ajouter d'autres employée  Y/N");
					reponse = entree.next();

				} catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				} catch (TransformerException tfe) {
					tfe.printStackTrace();

				}
			} }break ; 
		default: System.out.print("erreur de saisie ");

		}
	}
}
