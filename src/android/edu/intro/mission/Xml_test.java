package android.edu.intro.mission;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class Xml_test
{
	static Document doc;
	public static void main(String[] args) throws Exception
	{
		BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("please input 1 or 2 to choose xml_read or xml_attri:");
		String str=buf.readLine();
		if(str.equalsIgnoreCase("1"))
		{
			String filename="src/map_npc.xml";
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			try
			{
				DocumentBuilder db=dbf.newDocumentBuilder();
				doc =db.parse(new File(filename));
				Node child=(Node)doc.getFirstChild();
				for(;child!=null;child=child.getNextSibling())
				{
					System.out.print("element:"+child.getNodeName());
					System.out.println("Type:"+child.getNodeType());
					pChild(child,0);
				}
			}
			catch(SAXException se)
			{
				Exception e=se;
				if(se.getException()!=null)
				{
					e=se.getException();
				}
				e.printStackTrace();
			}
			catch(ParserConfigurationException pe)
			{
				pe.printStackTrace();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
		else
		{
			Xml_test_attri att=new Xml_test_attri();
			att.read_att();
		}
	}
	
	private static void pChild(Node temp, int pos)
	{
		if(temp.hasChildNodes())
		{
			NodeList nodes= temp.getChildNodes();
			for (int i=0;i<nodes.getLength();i++)
			{
				int type=nodes.item(i).getNodeType();
				if(type==Node.ELEMENT_NODE)
				{
					printIndent(pos);
					System.out.println("element_ELEMENT_NODE:"+nodes.item(i).getNodeName());
					pChild(nodes.item(i),pos+1);
				}
				if(type==Node.TEXT_NODE)
				{
					String val=nodes.item(i).getNodeValue();
					if(val!=null)
					{
						printIndent(pos);
						System.out.print("element_TEXT_NODE:"+nodes.item(i).getNodeName());
						System.out.print(" value: ");
						if(val.trim().equals(""))
							System.out.println("WS");
						else
							System.out.println(val);
					}
				}
			}
		}
	}
	private static void printIndent(int num)
	{
		System.out.print("   +");
		for(int i=0;i<=num;i++)System.out.print("-");
	}

}
