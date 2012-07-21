package android.edu.intro.mission;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadMapNpc
{
	private Document doc;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private NodeList tagnodes;
	private NamedNodeMap npcnode;
	
	private ReadMapNpc(String src,String element) throws Exception
	{
		dbf=DocumentBuilderFactory.newInstance();
		db=dbf.newDocumentBuilder();
		doc =db.parse(new File("src/map_npc.xml"));
		tagnodes=doc.getElementsByTagName("npc");
	}
	
	public void setElementSN(int sn)
	{
		npcnode=tagnodes.item(sn).getAttributes();
	}
	
	public String getNpcAttr(String attr)
	{
		Node att=npcnode.getNamedItem(attr);
		return att.getNodeValue();
	}
	
	public int getNpcId()
	{
		Node id=npcnode.getNamedItem("id");
		return Integer.parseInt(id.getNodeValue());
	}
	
	public String getNpcName()
	{
		Node name=npcnode.getNamedItem("npc_name");
		return name.getNodeValue();
	}
	
	public int getNpcX()
	{
		Node x=npcnode.getNamedItem("x");
		return Integer.parseInt(x.getNodeValue());
	}
	
	public int getNpcY()
	{
		Node y=npcnode.getNamedItem("y");
		return Integer.parseInt(y.getNodeValue());
	}
}
