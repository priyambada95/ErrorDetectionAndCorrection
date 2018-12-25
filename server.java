package error;
importjava.awt.*;
import java.net.*;
import java.io.*;
importjava.awt.event.*;
class Server implements ActionListener
{
Button b1,b2,b3,b4,b5,b6,b7;
Label l1;
TextField t1,t2;
Frame f;
Socket s;
DataInputStream dis;
String str=null;
TextAreatx;
int flag = 0;
Server()
{
f=new Frame(&quot;Server&quot;);
b1= new Button(&quot;Inititalize connection&quot;);
b2 = new Button(&quot;close Connection&quot;);
b3 = new Button(&quot;Close Window&quot;);
b4 = new Button(&quot;LRC&quot;);
b5 = new Button(&quot;ACK&quot;);
b6 = new Button(&quot;HMC&quot;);
b7 = new Button(&quot;CRC&quot;);
l1 = new Label (&quot;Connection Status&quot;);
t1 = new TextField();
t2 = new TextField();
tx= new TextArea(&quot;Status of System: &quot; +
&quot;\n&quot;,15,1,TextArea.SCROLLBARS_VERTICAL_ONLY);
b1.setBounds(50,43,130,30);
b2.setBounds(320,43,130,30);
b3.setBounds(200,43,100,25);
b4.setBounds(220,200,60,30);
b5.setBounds(220,270,70,30);
b6.setBounds(220,250,70,30);
b7.setBounds(220,290,70,30);
l1.setBounds(0,80,499,20);

t1.setBounds(90,140,290,25);
t1.setEditable(false);
t2.setBounds(90,170,290,25);
tx.setBounds(30,350,440,100);
t2.setEditable(false);
tx.setEditable(false);
l1.setAlignment(Label.CENTER);
b3.setBackground(Color.blue);
l1.setBackground(Color.green);
b5.setVisible(false);
f.add(b1);
f.add(b2);
f.add(b3);
f.add(b4);
f.add(b5);
f.add(b6);
f.add(b7);
f.add(l1);
f.add(t1);
f.add(t2);
f.add(tx);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
b7.addActionListener(this);
f.setSize(500,500);
f.setLayout(null);
f.setVisible(true);
}
/* (non-Javadoc)
* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
*/
public void actionPerformed(ActionEventae)
{
try
{
finalServerSocketss= new ServerSocket();
ss.setSoTimeout(20000);
if(ae.getSource()==b1)
{
l1.setText(&quot;Connection Initiated (Ready to accept )&quot;);
tx.append(&quot;. Connection Initiated &quot;+ &quot;\n&quot;);
InetAddressip = InetAddress.getByName(&quot;127.0.0.1&quot;);

System.out.println(&quot;ip&quot;+ip);
SocketAddresssk = new InetSocketAddress(ip,6666);
ss.bind(sk,6666);
s = ss.accept();
System.out.println(&quot;Connection Accepted&quot;);
l1.setText(&quot;Connection Accepted&quot;);
tx.append(&quot;. Connection Accepted&quot;+ &quot;\n&quot;);
dis=new DataInputStream(s.getInputStream());
str=(String)dis.readUTF();
l1.setText(&quot;Data Received , click Show to see&quot;);
tx.append(&quot;. Data is received&quot;+ &quot;\n&quot;);
}
if(ae.getSource()==b2)
{
if(s.isClosed())
{
System.out.println(&quot;Connection Closed&quot;);
l1.setText(&quot;Connection Closed&quot;);
tx.append(&quot;. Connection Closed by server&quot;+ &quot;\n&quot;);
}
Else
{
s.close();
ss.close();
System.out.println(&quot;Connection Closed&quot;);
l1.setText(&quot;Connection Closed&quot;);
tx.append(&quot;. Connection Closed by server&quot;+ &quot;\n&quot;);
} }
if(ae.getSource()==b3)
{
s.close();
ss.close();
tx.append(&quot;. Closed and Excited&quot;+ &quot;\n&quot;);
System.out.print(&quot;Closed and Exited&quot;);
System.exit(0);
}
if(ae.getSource()==b4)
{
tx.append(&quot;. Calculating LRC and verifying validity&quot;+ &quot;\n&quot;);
char message[] = new char[7];
bytelrc_check=0;
for(inti=0;i&lt;message.length;i++)
{
if(str.charAt(i)==&#39;?&#39;)

{
lrc_check ^=(byte) str.charAt(i);
message[i]=(char)00;
}
else
{
lrc_check ^= (byte)str.charAt(i);
message[i]=str.charAt(i);
}
}
tx.append(&quot;. LRC(int): &quot;+lrc_check+&quot;, LRC(char) : &quot;+(char)lrc_check+ &quot;\n&quot;);
if(lrc_check != (byte)str.charAt(message.length))
{
flag=1;
b4.setVisible(false);
b5.setVisible(true);
b7.setVisible(false);
b6.setVisible(false);
t1.setText(&quot;Message received is wrong LRC calculated: &quot; + (char)lrc_check);
t2.setText(&quot;Message is Corrupted&quot;);
tx.append(&quot;. Data Received is not valid , Request Resend&quot;+ &quot;\n&quot;);
System.out.println(&quot;Message is not correct, there was some error introduced&quot;);
}
else
{
flag=0;
b4.setVisible(false);
b5.setVisible(true);
b7.setVisible(false);
b6.setVisible(false);
str =new String(message);
System.out.println(&quot;Message is correctly Received.&quot;);
System.out.println(&quot;Received message is: &quot; + str);
t1.setText(&quot;Message is correctly Received with LRC : &quot; + (char)lrc_check);
tx.append(&quot;. Data Received is Correct&quot;+ &quot;\n&quot;);
t2.setText(&quot;Message Received : &quot; + str );
tx.append(&quot;. Message Received : &quot;+ str + &quot;\n&quot;);
}
}
if(ae.getSource()==b6)
{
tx.append(&quot;. Calculating HMC and verifying validity&quot;+ &quot;\n&quot;);
charcomCode[] = new char[11];
char C,C1,C2,C3,C4 ;
C1=(char)
(comCode[0]^comCode[2]^comCode[4]^comCode[6]^comCode[8]^comC
ode[10]);
C2=(char)

(comCode[1]^comCode[2]^comCode[5]^comCode[6]^comCode[9]^comCode[10]);
C3=(char) (comCode[3]^comCode[4]^comCode[5]^comCode[6]);
C4=(char) (comCode[7]^comCode[8]^comCode[9]^comCode[10]);
C =(char) (C4*8+C3*4+C2*2+C1);
if(C != 0)
{
flag=1;
b4.setVisible(false);
b5.setVisible(true);
b7.setVisible(false);
b6.setVisible(false);
t1.setText(&quot;Message received is wrong HMC calculated: &quot; + str);
t2.setText(&quot;Message is Corrupted&quot;);
tx.append(&quot;. ERROR ON POSITION :&quot; + C);
tx.append(&quot;. Data Received is not valid , Request Resend&quot;+ &quot;\n&quot;);
System.out.println(&quot;Message is not correct, there was some error introduced&quot;);
}
else
{ flag=0;
b6.setVisible(false);
b5.setVisible(true);
b7.setVisible(false);
b4.setVisible(false);
/*str =new char(C);*/
System.out.println(&quot;Message is correctly Received.&quot;);
System.out.println(&quot;Received message is: &quot; + str);
t1.setText(&quot;Message is correctly Received with HMC : &quot; + str);
tx.append(&quot;. Data Received is Correct&quot;+ &quot;\n&quot;);
t2.setText(&quot;Message Received : &quot; + str );
tx.append(&quot;. Message Received : &quot;+ str + &quot;\n&quot;);
}
}
if(ae.getSource()==b7)
{
tx.append(&quot;. Calculating CRC and verifying validity&quot;+ &quot;\n&quot;);
char m[] = new char[12];
bytecrc_check=0;
tx.append(&quot;. CRC(int): &quot;+crc_check+&quot;, CRC(char) : &quot;+crc_check+ &quot;\n&quot;);
if(crc_check != (char)str.charAt(m.length))
{
flag=1;
b4.setVisible(false);
b5.setVisible(true);
b6.setVisible(false);
b7.setVisible(false);
t1.setText(&quot;Message received is wrong CRC calculated: &quot; +crc_check);
t2.setText(&quot;Message is Corrupted&quot;);

tx.append(&quot;. Data Received is not valid , Request Resend&quot;+ &quot;\n&quot;);
System.out.println(&quot;Message is not correct, there was some error introduced&quot;);
}
else
{
flag=0;
b4.setVisible(false);
b5.setVisible(true);
b6.setVisible(false);
b7.setVisible(false);
str =new String(m);
System.out.println(&quot;Message is correctly Received.&quot;);
System.out.println(&quot;Received message is: &quot; + str);
t1.setText(&quot;Message is correctly Received with LRC : &quot; + crc_check);
tx.append(&quot;. Data Received is Correct&quot;+ &quot;\n&quot;);
t2.setText(&quot;Message Received : &quot; + str );
tx.append(&quot;. Message Received : &quot;+ str + &quot;\n&quot;);
} }
if(ae.getSource()==b5)
{
DataOutputStreamdout = new DataOutputStream(s.getOutputStream());
if(flag==1)
{
dout.writeUTF(&quot;Server says : Please Resend the Data&quot;);
l1.setText(&quot;Resending Requested &quot;);
tx.append(&quot;. Resending Requested &quot;+&quot;\n&quot;);
}
else
{
dout.writeUTF(&quot;Server says : Data is Correct&quot;);
l1.setText(&quot; Acknowledged &quot;);
tx.append(&quot;. Acknowledged &quot;+&quot;\n&quot;);
}
dout.close();
}
}
catch(Exception e)
{
b4.setVisible(false);
b6.setVisible(false);
b7.setVisible(false);
b5.setVisible(true);
flag = 1;
l1.setText(&quot;Something has gone wrong close and try again&quot;);
tx.append(&quot;. Something has gone wrong close and try again&quot;+ &quot;\n&quot;);
System.out.println(&quot;Something has gone wrong please close and try again&quot;);
} }
public static void main(String arg[])

{
new Server();
}}

 