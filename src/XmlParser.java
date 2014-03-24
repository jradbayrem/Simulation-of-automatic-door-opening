
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
public class XmlParser {

	public static void main(String[] args) {
	try
		{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse("src/testing.xml");
		NodeList nodeListSomeNode=doc.getElementsByTagName("prenom");
		System.out.println(nodeListSomeNode.item(0).toString());

		}
		catch (SAXException e)
		{
		e.printStackTrace();
		}

catch (IOException e)
{

e.printStackTrace();
}

catch (ParserConfigurationException e)
{
e.printStackTrace();
}
}
	}


