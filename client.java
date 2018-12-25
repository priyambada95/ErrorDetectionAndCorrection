package error;
importjava.awt.*;
import java.net.*;
import java.io.*;
importjava.awt.event.*;
importjava.util.Scanner;
class Client implements ActionListener
{
Button en,C,D,cl,lr,sen,ck,rs,hc,cr;
Label l,l1;
TextField t1,t2;
String s1,er;
Socket s = new Socket();
TextAreatx;
bytelrc,hmc,crc;
Client()
{
Frame f = new Frame(&quot;Client &quot;);
C = new Button(&quot;Connect&quot;);
l = new Label(&quot;Connection Status&quot;);
cl = new Button(&quot;Close Window&quot;);
D = new Button(&quot;Disconnect&quot;);
t1= new TextField();
en = new Button(&quot;Submit&quot;);
l1 = new Label(&quot;Enter 7 characters then click Submit use ? if less than 7&quot;);
lr = new Button(&quot;LRC&quot;);
hc = new Button(&quot;HMC&quot;);
cr = new Button(&quot;CRC4&quot;);
t2= new TextField();
sen = new Button(&quot;Send Data&quot;);
tx= new TextArea(&quot;Status of System : &quot; +
&quot;\n&quot;,15,1,TextArea.SCROLLBARS_VERTICAL_ONLY);
ck = new Button(&quot;Reset Con.&quot;);
rs = new Button(&quot;Edit&quot;);
D.setBounds(340,43,80,30);
C.setBounds(70,43,80,30);
l.setBounds(0,80,499,20);

cl.setBounds(205,43,100,25);
t1.setBounds(10,110,350,25);
l1.setBounds(10,142,300,20);
en.setBounds(363,110,60,25);
lr.setBounds(220,170,60,25);
hc.setBounds(270,170,90,25);
cr.setBounds(320,170,120,25);
t2.setBounds(10,170,200,25);
sen.setBounds(10,200,80,30);
tx.setBounds(30,350,440,100);
ck.setBounds(10,240,100,30);
rs.setBounds(150,200,160,30);
ck.setVisible(true);
t2.setEditable(false);
tx.setEditable(false);
l.setAlignment(Label.CENTER);
l.setBackground(Color.yellow);
cl.setBackground(Color.blue);
f.add(C);
f.add(D);
f.add(l);
f.add(cl);
f.add(t1);
f.add(l1);
f.add(en);
f.add(lr);
f.add(hc);
f.add(cr);
f.add(t2);
f.add(sen);
f.add(tx);
f.add(ck);
f.add(rs);
C.addActionListener(this);
D.addActionListener(this);
cl.addActionListener(this);
en.addActionListener(this);
en.addActionListener(this);
lr.addActionListener(this);
hc.addActionListener(this);
cr.addActionListener(this);
sen.addActionListener(this);
ck.addActionListener(this);
rs.addActionListener(this);
f.setSize(500,500);

f.setLayout(null);
f.setVisible(true);
}

public void actionPerformed(ActionEventae)
{
try
{
if(ae.getSource()==C)
{
InetAddressadr = InetAddress.getByName(&quot;127.0.0.1&quot;);
SocketAddress sock = new InetSocketAddress(adr,6666);
s.connect(sock);
l.setText(&quot;Connected&quot;);
tx.append(&quot;. Connected&quot;+ &quot;\n&quot;);
System.out.println(&quot;Connected&quot;);
}
else
if(ae.getSource()==D)
{
s.close();
l.setText(&quot;Disconnected&quot;);
tx.append(&quot;. Disconnected&quot;+ &quot;\n&quot;);
System.out.println(&quot;Disconnected&quot;);
}
if(ae.getSource()==cl)
{
s.close();
tx.append(&quot;. Closed and Exiting&quot;+ &quot;\n&quot;);
System.out.println(&quot;Closed and Exited&quot;);
System.exit(0);
}
if(ae.getSource()==en)
{
s1 = t1.getText();
t1.setEditable(false);
tx.append(&quot;. Submitted Data is : &quot;+s1+ &quot;\n&quot;);
}
if(ae.getSource()==rs)
{ s1 = null;
t1.setEditable(true);
tx.append(&quot;. Rentering&quot;+ &quot;\n&quot;);
lrc=0;
t2.setText(null);

}

if(ae.getSource()==lr)
{
tx.append(&quot;. Calculating LRC&quot;+ &quot;\n&quot;);
char message[] = new char[8];
lrc=0; //binary: 00000000
for(inti=0;i&lt;message.length-1;i++)
{
message[i]=s1.charAt(i); //gives the corresponding character of string to the message
array element
lrc^=(byte)message[i];
}
tx.append(&quot;. LRC (int ) : &quot;+lrc+&quot;, LRC (character) : &quot;+(char)lrc+ &quot;\n&quot;);
message[message.length-1]=(char)lrc;
s1 = new String(message);
t2.setText(&quot;String with LRC is &quot; + s1);
tx.append(&quot;. Message to be Sent with LRC : &quot;+s1+ &quot;\n&quot;);
}
if(ae.getSource()==hc)
{
tx.append(&quot;. Calculating Hamming code &quot;+ &quot;\n&quot;);
chardataCode[] = new char[7];
char parity[] = new char[4];
charcomCode[] = new char[11];
for(inti=0;i&lt;7;i++)
{
dataCode[i]=s1.charAt(i);
}
parity[0] = (char)(dataCode[0]^dataCode[1]^dataCode[3]^dataCode[4]^dataCode[6]);
parity[1] = (char)(dataCode[0]^dataCode[2]^dataCode[3]^dataCode[5]^dataCode[6]);
parity[2] = (char) (dataCode[1]^dataCode[2]^dataCode[3]);
parity[3] = (char) (dataCode[4]^dataCode[5]^dataCode[6]);
comCode[0] = parity[0];
comCode[1] = parity[1];
comCode[2] = dataCode[0];
comCode[3] = parity[2];
comCode[4] = dataCode[1];
comCode[5] = dataCode[2];
comCode[6] = dataCode[3];
comCode[7] = parity[3];
comCode[8] = dataCode[4];
comCode[9] = dataCode[5];

comCode[10] = dataCode[6];
s1 = new String(comCode);
t2.setText(&quot;String with hmc is &quot; + s1);
tx.append(&quot;. Message to be Sent with HMC : &quot;+s1+ &quot;\n&quot;);
}
if(ae.getSource()==cr)
{
tx.append(&quot;. Calculating CRC&quot;+ &quot;\n&quot;);
char g[],z[],r[],msb,i,j,k;
char d[]=new char[12];
char div[]=new char[5];
char m[]=new char[12];
div[0]=&#39;1&#39;;
div[1]=&#39;0&#39;;
div[2]=&#39;0&#39;;
div[3]=&#39;1&#39;;
div[4]=&#39;1&#39;;
for(i=0;i&lt;7;i++)
{
d[i]=s1.charAt(i);
}
for(i=0;i&lt;4;i++)
{
d[7+i]=&#39;0&#39;;
}
m[0]=d[0];
m[1]=d[1];
m[2]=d[2];
m[3]=d[3];
m[4]=d[4];
m[5]=d[5];
m[6]=d[6];
m[7]=d[7];
m[8]=d[8];
m[9]=d[9];
m[10]=d[10];
m[11]=d[11];
r=new char[12];
for(i=0;i&lt;5;i++)
{
r[i]=m[i];
}
z=new char[5];

for(i=0;i&lt;5;i++)
{
z[i]=&#39;0&#39;;
}
for(i=0;i&lt;7;i++)
{
k=0;
msb=r[i];
for(j=i;j&lt;5+i;j++)
{
if(msb==&#39;0&#39;)
r[j]= (char) xor(r[j],z[k]);
else
r[j]= (char) xor(r[j],div[k]);
k++;
}
r[5+i]=d[5+i];
}
for(i=7;i&lt;11;i++)
{
m[i]=r[i];
}
s1 = new String(m);
t2.setText(&quot;String with crc is &quot; + s1);
tx.append(&quot;. Message to be Sent with CRC : &quot;+s1+ &quot;\n&quot;);
}
if(ae.getSource()==sen)
{
l.setText(&quot;Data Sent....&quot;);
tx.append(&quot;. Data Sent ... &quot;+ &quot;\n&quot;);
DataOutputStreamdout=new DataOutputStream(s.getOutputStream());
dout.writeUTF(s1);
DataInputStream dis = new DataInputStream(s.getInputStream());
er = (String)dis.readUTF();
l.setText(er);
tx.append(&quot;. &quot;+er+&quot;\n&quot;);
dout.flush();
dout.close();
dis.close();
}
if(ae.getSource()==sen)
{
l.setText(&quot;Data Sent....&quot;);
tx.append(&quot;. Data Sent ... &quot;+ &quot;\n&quot;);

DataOutputStreamdout=new DataOutputStream(s.getOutputStream());
dout.writeUTF(s1);
DataInputStream dis = new DataInputStream(s.getInputStream());
er = (String)dis.readUTF();
l.setText(er);
tx.append(&quot;. &quot;+er+&quot;\n&quot;);
dout.flush();
dout.close();
dis.close();
}
}
catch(Exception e)
{
tx.append(&quot;. Server Disconnected , Connect before sending , Empty etc.&quot;+ &quot;\n&quot;);
l.setText(&quot;Something Wrong has happened please reset and try again&quot;);
System.out.println(&quot;Server Disconnected itself abnormally&quot;);
}
if(ae.getSource()==ck)
{
s=new Socket();
l.setText(&quot;Connection Status&quot;);
tx.setText(&quot;Status of System: \n . New Client is set \n&quot;);
s1=null;
er=null;
t1.setText(null);
t1.setEditable(true);
t2.setText(null);
}
}
public static void main(String arg[])
{
new Client();
}
public static char xor(char x,char y)
{
if(x==y)
return (&#39;0&#39;);
else
return (&#39;1&#39;);
}
}
