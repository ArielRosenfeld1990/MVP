package presenter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Properties implements Serializable {
	String searcher;
	String mazeGenerator;
	int numOfThreads;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Properties() {
		searcher = "Bfs";
		numOfThreads = 7;
		mazeGenerator = "myMazeGenerator";

	}

	public void saveToXML() {
		try {
			Integer num1 = numOfThreads;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element Properties = doc.createElement("Properties");
			doc.appendChild(Properties);
			Element Search = doc.createElement("searcher");
			Search.appendChild(doc.createTextNode(searcher));
			Properties.appendChild(Search);
			Element ThreadsNum = doc.createElement("ThreadsNum");
			ThreadsNum.appendChild(doc.createTextNode(num1.toString()));
			Properties.appendChild(ThreadsNum);
			Element Generator = doc.createElement("Generator");
			Generator.appendChild(doc.createTextNode(mazeGenerator));
			Properties.appendChild(Generator);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Properties.xml"));
			transformer.transform(source, result);
			System.out.println("Properties saved");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	public void loadFromXML() {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse("Properties.xml");
			if (doc != null) {
				NodeList nList = doc.getElementsByTagName("Properties");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						searcher = eElement.getElementsByTagName("searcher").item(0).getTextContent();
						String numThreads = eElement.getElementsByTagName("ThreadsNum").item(0).getTextContent();
						numOfThreads = Integer.parseInt(numThreads);
						mazeGenerator = eElement.getElementsByTagName("Generator").item(0).getTextContent();

					}
					System.out.println("file loaded successfuly");
				}
			} else
				System.out.println("XML Properties file wasnt found");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getSearcher() {
		return searcher;
	}

	public void setSearcher(String searcher) {
		this.searcher = searcher;
	}

	public String getMazeGenerator() {
		return mazeGenerator;
	}

	public void setMazeGenerator(String mazeGenerator) {
		this.mazeGenerator = mazeGenerator;
	}

	public int getNumOfThreads() {
		return numOfThreads;
	}

	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}

}
