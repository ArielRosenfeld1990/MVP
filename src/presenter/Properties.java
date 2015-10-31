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
	static String RemoteIPaddress;
	static String mazeGenerator;
	static int numOfThreads;
	static String TypeOfView;
	static String TypeOfCache;
	static int RemotePort;
    Document docXML;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <h1>Properties constructor</h1>
	 * constructor for Properties class
	 */
	public Properties() {
		RemoteIPaddress = "127.0.0.1";
		RemotePort=5400; 
		numOfThreads = 7;
		mazeGenerator = "myMazeGenerator";
		TypeOfView="GUI";
		TypeOfCache="ZipCache";
		docXML=null;

	}
	/**
	 * <h1>Properties constructor</h1>
	 *non-default constructor for Properties class
	 * @param doc is XML file that from it we will build our Properties object
	 * @throws FileNotFoundException if such InputStream wasn't found
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
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <h1>saveToXML</h1>
	 *this method is used to save our setting in XML file
	 */
	public void saveToXML() {
		try {
			Integer num1 = numOfThreads;
			Integer num2 = RemotePort;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element Properties = doc.createElement("Properties");
			doc.appendChild(Properties);
			Element IPaddress = doc.createElement("RemoteIPaddress");
			IPaddress.appendChild(doc.createTextNode(RemoteIPaddress));
			Properties.appendChild(IPaddress);
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
			Element PortNum = doc.createElement("RemotePort");
			PortNum.appendChild(doc.createTextNode(num2.toString()));
			Properties.appendChild(PortNum);
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
	 * <h1>loadFromXML</h1>
	 *this method is used to load from XML file the requested settings
	 *@throws FileNotFoundException if the Properties.xml file wasn't found
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
						RemoteIPaddress = eElement.getElementsByTagName("RemoteIPaddress").item(0).getTextContent();
						String numThreads = eElement.getElementsByTagName("ThreadsNum").item(0).getTextContent();
						numOfThreads = Integer.parseInt(numThreads);
						mazeGenerator = eElement.getElementsByTagName("Generator").item(0).getTextContent();
						TypeOfView=eElement.getElementsByTagName("typeofView").item(0).getTextContent();
						TypeOfCache=eElement.getElementsByTagName("typeofCache").item(0).getTextContent();
						String PortNum = eElement.getElementsByTagName("RemotePort").item(0).getTextContent();
						RemotePort = Integer.parseInt(PortNum);
					}
					System.out.println("file loaded successfully");
				}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			
			throw new FileNotFoundException("Properties file wasnt found");
		}

	}

	/**
	 * <h1>getMazeGenerator</h1>
	 *this method is a getter for our maze generator setting
	 *@return mazeGenerator is the generator 
	 */
	public static String getMazeGenerator() {
		return mazeGenerator;
	}
	/**
	 * <h1>setMazeGenerator</h1>
	 *this method is a setter for our maze generator setting
	 *@param MG is the generator
	 */
	public static void setMazeGenerator(String MG) {
		mazeGenerator = MG;
	}
	/**
	 * <h1>getNumOfThreads</h1>
	 *this method is a getter for our number of threads setting
	 *@return numOfThreads setting
	 */
	public static int getNumOfThreads() {
		return numOfThreads;
	}
	/**
	 * <h1>setNumOfThreads</h1>
	 *this method is a setter for our number of threads setting
	 *@param NoT is the number of threads value
	 */
	public static void setNumOfThreads(int NoT) {
		numOfThreads =  NoT;
	}
	/**
	 * <h1>getTypeOfView</h1>
	 *this method is a getter for our type of view setting
	 *@return TypeOfView is the the way we will implement our view - gui or cli
	 */
	public static String getTypeOfView() {
		return TypeOfView;
	}
	/**
	 * <h1>setTypeOfView</h1>
	 *this method is a getter for our type of view
	 *@param TypeOfView is the the way we will implement our view - gui or cli
	 */
	public static void setTypeOfView(String typeOfView) {
		TypeOfView = typeOfView;
	}
	/**
	 * <h1>getRemoteIPaddress</h1>
	 * is a getter for the IP address setting
	 * @return RemoteIPaddress setting 
	 */
	public static String getRemoteIPaddress() {
		return RemoteIPaddress;
	}
	/**
	 * <h1>setRemoteIPaddress</h1>
	 * a setter for the remoteIPaddress setting
	 * @param remoteIPaddress is the value for the remoteIPaddress parameter
	 */
	public static void setRemoteIPaddress(String remoteIPaddress) {
		RemoteIPaddress = remoteIPaddress;
	}
	/**
	 * <h1>getTypeOfCache</h1>
	 * a getter for our getTypeOfCache setting
	 * @return TypeOfCache data member
	 */
	public static String getTypeOfCache() {
		return TypeOfCache;
	}
	/**
	 * <h1>setTypeOfCache</h1>
	 * a setter for setTypeOfCache setting
	 * @param typeOfCache is the value for the typeOfCache data member
	 */
	public static void setTypeOfCache(String typeOfCache) {
		TypeOfCache = typeOfCache;
	}
	/**
	 * <h1>getRemotePort</h1>
	 * a getter for the getRemotePort setting
	 * @return getRemotePort data member
	 */
	public static int getRemotePort() {
		return RemotePort;
	}
	/**
	 * <h1>setRemotePort</h1>
	 * a setter for the RemotePort setting
	 * @param remotePort is the value for the RemotePort data member
	 */
	public static void setRemotePort(int remotePort) {
		RemotePort = remotePort;
	}

}
