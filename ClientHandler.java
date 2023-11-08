import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            FileInputStream fos = new FileInputStream("test.txt");
            byte b[] = new byte[fos.available()];
            int x;
            int i=0;
            while((x=fos.read())!=-1) {
                b[i++] = (byte)x;
            }

            int key=2;
            for(int j=0;j<b.length;j++){
                b[j]= (byte) (b[j]+key);
            }
            System.out.print("To send enter 1 else 0: ");
            Scanner sc=new Scanner(System.in);
            int choice;
            choice=sc.nextInt();
            if(choice==1){
                OutputStream fos1 = clientSocket.getOutputStream();
                // Send the file to the client

                fos1.write(b);
                fos1.flush();
            }
            // Send the file to the client

//            sendFile(fileToSend, clientSocket.getOutputStream());

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void sendFile(File file, OutputStream outputStream) throws IOException {
//        byte[] buffer = new byte[1024];
//        FileInputStream fis = new FileInputStream(file);
//        BufferedInputStream bis = new BufferedInputStream(fis);
//
//        DataOutputStream dos = new DataOutputStream(outputStream);
//        dos.writeUTF(file.getName());
//
//        int count;
//        while ((count = bis.read(buffer)) > 0) {
//            dos.write(buffer, 0, count);
//        }
//
//        // Close the streams
//        dos.flush();
//        bis.close();
//    }
}
