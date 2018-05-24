import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.CapturePacketException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.ICMPPacket;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.RawPacket;
import net.sourceforge.jpcap.net.TCPPacket;
import net.sourceforge.jpcap.util.HexHelper;

public class PcapTest  implements RawPacketListener {

	protected static String NAME = "PcapTest";
	protected static int TEST_COUNT = 10;
	
	public void rawPacketArrived(RawPacket rawPacket) {
		    System.err.println(NAME + ": " + rawPacket + " arrived");
	}
	 
	 
	public PcapTest() {
	}

	  
	public void runTest() {
	    //-- create PacketCapture system
	    System.err.println(NAME + ": instantiating PacketCapture object.. ");
	    PacketCapture pc = new PacketCapture();
	    System.err.println(NAME + ": PacketCapture instantiated ok");

	    try {
			for(String dev : PacketCapture.lookupDevices())
			{
				 System.err.println(dev);
			}
		} 
	    catch (CaptureDeviceLookupException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		      System.exit(1);
		}

	    //-- open the capture device
	    try {
	      System.err.print(NAME + ": opening capture device.. ");
	      pc.open("\\Device\\NPF_{2EA6F309-53D5-42D6-BC0F-CE9D5A654B78}", true); //RealTek PCI controler
	      System.err.println(pc.toString());
	    }
	    catch(CaptureDeviceOpenException e) {
	      // usually occurs if the user doesn't have sufficient privileges
	      System.err.println(e);
	      System.exit(1);
	    }

	    //-- set filters
	    try {
	      String filter = ""; // nothing filtered by default
	      
	      // filter ICMP messages
	      // filter = "proto ICMP";
	      
	      System.err.println(NAME + ": setting filter to '" + filter + "'.. ");
	      pc.setFilter(filter, true);
	      System.err.println(NAME + ": filter compiled and activated ok");
	    }
	    catch(InvalidFilterException e) {
	      System.err.println(e);
	      System.exit(1);
	    }

	    //-- register the packet listener
	    System.err.print(NAME + ": registering as a packet listener.. ");
	    // pc.addRawPacketListener(this);
	    pc.addPacketListener(new TCPPacketHandler());
	    // pc.addPacketListener(new ICMPPacketHandler());
	    System.err.println("ok");
	    
	    //-- capture packets
	    try {
	      int count = TEST_COUNT;
	      System.err.println(NAME + ": waiting for " + count + " packet(s).. ");
	      pc.capture(count);
	      System.err.println(NAME + ": done capturing.");
	    }
	    catch(CapturePacketException e) {
	      System.err.println(e);
	      System.exit(1);
	    }

	    //-- dump statistics
	    System.err.println(pc.getStatistics());
	  }


	  /**
	   * Simple test to exercise PacketCapture.
	   */
	  public static void main(String [] args) {
		  PcapTest ct = new PcapTest();
		  ct.runTest();
	  }
	  
	  @SuppressWarnings("unused")
	private String _rcsid = 
			    "$Id: PcapTest.java,v 1.2 2001/05/23 02:55:02 pcharles Exp $";
}

class TCPPacketHandler implements PacketListener 
{
  public void packetArrived(Packet packet) {
    try {
      // only handle TCP packets

      if(packet instanceof TCPPacket) {
    	  TCPPacket tcpPacket = (TCPPacket)packet;
    	  byte[] data = tcpPacket.getTCPData();
	    
		String srcHost = tcpPacket.getSourceAddress();
		String dstHost = tcpPacket.getDestinationAddress();
		String isoData = new String(data, "ISO-8859-1");
		
		System.out.println(srcHost+" -> " + dstHost + ":" + tcpPacket.getDestinationPort() + " : " + isoData);
      }
    } 
    catch( Exception e ) {
      e.printStackTrace();
    }
  }
}

class ICMPPacketHandler implements PacketListener 
{
	  public void packetArrived(Packet packet) {
	    try {
	      ICMPPacket icmpPacket = (ICMPPacket) packet;

	      String srcHost = icmpPacket.getSourceAddress();
	      String dstHost = icmpPacket.getDestinationAddress();
		    
	      System.out.println(srcHost+" -> " + dstHost);
	      
	      System.out.println(icmpPacket.toColoredString(true));
	      System.out.println("message code: " + icmpPacket.getMessageCode());
	      System.out.println("message major: " + icmpPacket.getMessageMajorCode());
	      System.out.println("message minor: " + icmpPacket.getMessageMinorCode());
	      System.out.println("checksum: " + icmpPacket.getChecksum());

	      System.out.println("");
	      System.out.println("ethernet header: " + 
	                         HexHelper.toString(icmpPacket.getEthernetHeader()));
	      System.out.println("ethernet data: " + 
	                         HexHelper.toString(icmpPacket.getEthernetData()));
	      System.out.println("");

	      System.out.println("header: " + 
	                         HexHelper.toString(icmpPacket.getHeader()));

	      System.err.println("data: " + HexHelper.toString(icmpPacket.getData()));
	    } catch( Exception e ) {
	      e.printStackTrace();
	    }
	  }
}