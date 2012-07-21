package android.edu.intro.mission;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Xml_test_attri
{
	Document doc;
	public void read_att() throws Exception
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setCoalescing(false);
		DocumentBuilder db=dbf.newDocumentBuilder();
		doc =db.parse(new File("src/map_npc.xml"));
		/*Node root=doc.getDocumentElement();
		System.out.print("root element:"+root.getNodeName());
		NodeList nodes = root.getChildNodes();
		for(int i=0;i<nodes.getLength();i++)
		{
			System.out.println("element:"+nodes.item(i).getNodeName());
			if(nodes.item(i).hasChildNodes())
			{
				NodeList childs=nodes.item(i).getChildNodes();
				for(int j=0;j<childs.getLength();j++)
				{
					int type=childs.item(j).getNodeType();
					if(type==Node.ELEMENT_NODE)
					{
						System.out.print("    +--"+childs.item(j).getNodeName());
						//System.out.println("/"+childs.item(j).getFirstChild().getNodeValue());
					}
				}
			}
		}*/
		NodeList tagnodes=doc.getElementsByTagName("npc");
		for(int i=0;i<tagnodes.getLength();i++)
		{
			System.out.println("npc("+(i+1)+"):");
			NamedNodeMap atts=tagnodes.item(i).getAttributes();
			for(int j=0;j<atts.getLength();j++)
			{
				Node att=atts.item(j);
				System.out.print("    +--"+att.getNodeName());
				System.out.println("/"+att.getNodeValue());
			}
		}
		System.out.println("---------------TEST---------------");
		NamedNodeMap testatt=tagnodes.item(2).getAttributes();
		Node x=testatt.getNamedItem("x");
		System.out.print("x="+x.getNodeValue());
	}
}
