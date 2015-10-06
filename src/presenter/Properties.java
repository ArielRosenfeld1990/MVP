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
/**
 * <h1>Properties</h1> The Properties class is used for saving 
 * our properties in XML file - to save our setting
 * <p>
 *
 * @author Ariel Rosenfeld,Ofir Calif
 *
 * 
 */
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
	/**
	 * constructor for Properties
	 */
	public Properties() {
		searcher = "Bfs";
		numOfThreads = 7;
		mazeGenerator = "myMazeGenerator";
		TypeOfView="GUI";
		TypeOfCache="ZipCache";
		docXML=null;

	}
	/**
	 * constructor for Properties
	 * @param doc is XML file that from it we will build our Properties object
	 */
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
	/**
	 *this method is used to save our setting in XML file
	 */
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
	/**
	 *this method is used to load from XML file the requested settings
	 */
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
	/**
	 *this method is a getter for our searcher
	 *@return searcher is the algorithm which will solve the maze
	 */
	static public String getSearcher() {
		return searcher;
	}
	/**
	 *this method is a setter for our searcher
	 *@param s is the algorithm which will solve the maze
	 */
	static public void setSearcher(String s) {
		searcher = s;
	}
	/**
	 *this method is a getter for our maze generator
	 *@return mazeGenerator is the generator which will generate our maze
	 */
	static public String getMazeGenerator() {
		return mazeGenerator;
	}
	/**
	 *this method is a setter for our maze generator
	 *@param MG is the generator which will generate our maze
	 */
	static public void setMazeGenerator(String MG) {
		mazeGenerator = MG;
	}
	/**
	 *this method is a getter for our number of threads
	 *@return numOfThreads is the number of threads that would define our thread pool
	 */
	static public int getNumOfThreads() {
		return numOfThreads;
	}
	/**
	 *this method is a setter for our number of threads
	 *@param numOfThreads is the number of threads that would define our thread pool
	 */
	static public void setNumOfThreads(int NoT) {
		numOfThreads =  NoT;
	}
	/**
	 *this method is a getter for our type of view
	 *@return TypeOfView is the the way we will implement our view - gui or cli
	 */
	public static String getTypeOfView() {
		return TypeOfView;
	}
	/**
	 *this method is a getter for our type of view
	 *@param TypeOfView is the the way we will implement our view - gui or cli
	 */
	public static void setTypeOfView(String typeOfView) {
		TypeOfView = typeOfView;
	}

}
