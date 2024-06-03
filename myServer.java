import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class myServer {

    public static void main(String[] args)   {

        ServerSocket serverSocket;
        Socket socket;
        DataInputStream dis;
        DataOutput dos;
        String msg="";

        // String cmsge1="ACK";
        // byte[] byteArrraysrvr = cmsge1.getBytes();
        //String srvrmsge=""+Astm.ACK;

        try {
             serverSocket=new ServerSocket(44444);
             System.out.println("Server started..");
             socket=serverSocket.accept();
             dis= new DataInputStream(socket.getInputStream());
             dos =new DataOutputStream(socket.getOutputStream());

             ByteBuffer buffer = ByteBuffer.allocate(1024);

             //buffer.limit(10);

             while (true) {
                buffer.clear();
                while (dis.available() > 0 && buffer.hasRemaining()) {
                    buffer.put(dis.readByte());
                }
                buffer.flip();

                while (buffer.hasRemaining()) {
                    byte clientMessage = buffer.get();
                    if (clientMessage==Astm.ENQ) {
                        msg+=(char)clientMessage;
                        System.out.println("Start from client:"+msg);
                        dos.writeByte(Astm.ACK);
                        msg="";
                        
                        
                    } 
                   else if (clientMessage==Astm.EOT) {
                        msg+=(char)clientMessage;
                        System.out.println("end from client:"+msg);
                        msg="";
                        
                    }
                    else {
    
    
                        if(clientMessage==Astm.STX){
                            msg+=(char)clientMessage; 
                           // System.out.println(msg);
                         }
                         else if(clientMessage==Astm.LF){
                            msg+=(char)clientMessage; 
                            System.out.println("from client:"+msg);
    
                            msg="";
    
                            // String kk=""+Astm.ACK;
                            // byte[] byteArrrayxx = kk.getBytes();
                            dos.writeByte(Astm.ACK);
    
    
    
    
    
                    }else{
                        msg+=(char)clientMessage; 
                        //System.out.print(msg);
                    }
    
    
    
    
    
                        
                    }
                }

            // byte clientMessage = dis.readByte();
            //     if (clientMessage==Astm.ENQ) {
            //         msg+=(char)clientMessage;
            //         System.out.println("Start from client:"+msg);
            //         dos.writeByte(Astm.ACK);
            //         msg="";
                    
                    
            //     } 
            //    else if (clientMessage==Astm.EOT) {
            //         msg+=(char)clientMessage;
            //         System.out.println("end from client:"+msg);
            //         msg="";     
            //     }
            //     else {
            //         if(clientMessage==Astm.STX){
            //             msg+=(char)clientMessage; 
            //            // System.out.println(msg);
            //          }
            //          else if(clientMessage==Astm.LF){
            //             msg+=(char)clientMessage; 
            //             System.out.println("from client:"+msg);
            //             msg="";
            //             // String kk=""+Astm.ACK;
            //             // byte[] byteArrrayxx = kk.getBytes();
            //             dos.writeByte(Astm.ACK);
            //     }else{
            //         msg+=(char)clientMessage; 
            //         //System.out.print(msg);
            //     }





                    
            //     }






                
            //     if(clientMessage==Astm.STX){
            //         msg+=(char)clientMessage; 
            //        // System.out.println(msg);
            //      }
            //      else if(clientMessage==Astm.LF){
            //         msg+=(char)clientMessage; 
            //         System.out.println("from client:"+msg);
            //         // byte[] byteArrray =srvrmsge.getBytes();
            //         // dos.write(byteArrray);
            //         msg="";
            // }else{
            //     msg+=(char)clientMessage; 
            //     //System.out.print(msg);
            // }
           // System.out.println("hhhmsg : " + msg);

             }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
        
    }
    
}
