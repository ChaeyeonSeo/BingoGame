import java.awt.BorderLayout; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.OutputStreamWriter; 
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.JTextField; 

public class ClientFrame extends JFrame{ 

   private JTextArea textarea; 
   private JTextField sendMsgTf; 
   private JScrollPane scrollPane;
   
   private Socket socket;
   private BufferedWriter bw; 
   
   public static ArrayList <Integer> numbers = new ArrayList<Integer>(); 
   
   public ClientFrame() { 
      textarea = new JTextArea(); 
      sendMsgTf = new JTextField(); 
      textarea.setEditable(false);
      scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      setSize(400, 600); 
      setLocation(750, 100);
      setAlwaysOnTop(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      
      setTitle("chatting"); 
      
      sendMsgTf.addKeyListener(new MsgSendListener()); 
      add(scrollPane,BorderLayout.EAST);
      add(textarea,BorderLayout.CENTER);
      add(sendMsgTf,BorderLayout.SOUTH);
      setVisible(true); 
   } 

   public void setSocket(Socket socket) { 
      this.socket = socket; 
      try { 
         OutputStream out = socket.getOutputStream(); 
         bw = new BufferedWriter(new OutputStreamWriter(out));
      } catch (Exception e) { 
         e.printStackTrace(); 
      }
   } 
   
   public boolean isCalled(int value)
    {
        for (int x : numbers) {
            if (value == (int) x)
                return true;
        }
        return false;
    }
   

   class MsgSendListener implements KeyListener { 
      @Override public void keyTyped(KeyEvent e) { 
      
      }@Override public void keyPressed(KeyEvent e) { 
         
      }@Override public void keyReleased(KeyEvent e) {

         if (e.getKeyCode()==KeyEvent.VK_ENTER) {

            String msg = sendMsgTf.getText(); 
            System.out.println(msg);
            if(!isCalled(Integer.parseInt(msg))) {
               textarea.append("[ me ]: "+msg+"\n"); 
               numbers.add(Integer.parseInt(msg));
               sendMsgTf.setText(""); 
               try { 
                  bw.write(msg+"\n"); 
                  bw.flush(); 
               } catch (IOException e1) { 
                  // TODO Auto-generated catch block 
                  e1.printStackTrace(); 
               }
            }
            else {
               textarea.append("This number is already called. Please enter again.\n");
            }
         }
      } 
   } 

   class TcpClientReceiveThread implements Runnable { 
      private Socket socket;
      public TcpClientReceiveThread(Socket socket) { 
         this.socket = socket; 
      } 
      @Override public void run() { 

         try { 
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) { 
               String msg = br.readLine();
               textarea.append("[ other ]" + msg + "\n");
               numbers.add(Integer.parseInt(msg));

               for(int i : numbers) {
                  System.out.println((int)i);
               }
            } 
         } catch (Exception e) { 
            textarea.append("Connection End."); 
         } finally { 
            try { 
               if (socket!=null&&!socket.isClosed()) { 
                  socket.close();
               } 
            } catch (Exception e2) { 
               
            } 
         } 
      } 
   } 
   public static void main(String[] args) { 
      try {         
         Runplease run = new Runplease();
         
         //use server IP
         Socket socket = new Socket("localhost", 5000); 
         ClientFrame cf = new ClientFrame(); 
         cf.setSocket(socket);
         TcpClientReceiveThread th1 = cf.new TcpClientReceiveThread(socket); 
         new Thread(th1).start();
            
      } catch (Exception e) { 
         e.printStackTrace(); 
      } 
   } 
}
