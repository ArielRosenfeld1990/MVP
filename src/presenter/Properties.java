package presenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Properties implements Serializable {
	static String searcher;
	static String mazeGenerator;
	static int numOfThreads;
	static String TypeOfView;
	static String TypeOfCache;
    Document docXML;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Properties() {
		searcher = "Bfs";
		numOfThreads = 7;
		mazeGenerator = "myMazeGenerator";
		TypeOfView="GUI";
		TypeOfCache="ZipCache";
		docXML=null;

	}
	public Properties(InputStream doc) throws FileNotFoundException{
		try {	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder;
	    	dBuilder = dbFactory.newDocumentBuilder();
			docXML=dBuilder.parse(doc);
			 loadFromXML();
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException("Properties file wasnt found");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Element typeofView = doc.createElement("typeofView");
			typeofView.appendChild(doc.createTextNode(TypeOfView));
			Properties.appendChild(typeofView);
			Element Caching = doc.createElement("typeofCache");
			Caching.appendChild(doc.createTextNode(TypeOfCache));
			Properties.appendChild(Caching);
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

	public void loadFromXML() throws FileNotFoundException {

		try {
			if (docXML==null) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			docXML = dBuilder.parse("Properties.xml");
			}
			
				NodeList nList = docXML.getElementsByTagName("Properties");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						searcher = eElement.getElementsByTagName("searcher").item(0).getTextContent();
						String numThreads = eElement.getElementsByTagName("ThreadsNum").item(0).getTextContent();
						numOfThreads = Integer.parseInt(numThreads);
						mazeGenerator = eElement.getElementsByTagName("Generator").item(0).getTextContent();
						TypeOfView=eElement.getElementsByTagName("typeofView").item(0).getTextContent();
						TypeOfCache=eElement.getElementsByTagName("typeofCache").item(0).getTextContent();
					}
					System.out.println("file loaded successfully");
				}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block 
			throw new FileNotFoundException("Properties file wasnt found");
		}

	}

	static public String getSearcher() {
		return searcher;
	}

	static public void setSearcher(String s) {
		searcher = s;
	}

	static public String getMazeGenerator() {
		return mazeGenerator;
	}

	static public void setMazeGenerator(String MG) {
		mazeGenerator = MG;
	}

	static public int getNumOfThreads() {
		return numOfThreads;
	}

	static public void setNumOfThreads(int NoT) {
		numOfThreads =  NoT;
	}

	public static String getTypeOfView() {
		return TypeOfView;
	}

	public static void setTypeOfView(String typeOfView) {
		TypeOfView = typeOfView;
	}

}
