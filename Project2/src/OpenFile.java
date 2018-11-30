//pick file
//Author Yizhe Wang DengYuan Wang
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

    public class OpenFile {
        //using JFileChooser to realize the funcitonality to pick a file from the local
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        StringBuilder sb = new StringBuilder();
        String s = "";
        //fucntion called PickMe to pick the file
        public void PickMe() throws Exception{
            int r = fileChooser.showOpenDialog(null);
            //if statement to check the approve_option
            if(r == JFileChooser.APPROVE_OPTION){
                    java.io.File file = fileChooser.getSelectedFile();
                    // s will store the selected file's path
                    s = file.getAbsolutePath();
                    //checking print out
                    System.out.print(s);
                    System.out.print("-----------------");
                    //scan the file
                    Scanner input = new Scanner(file);
                    //store the context in sb
                    while(input.hasNext()){
                        sb.append(input.nextLine());
                        sb.append("\n");
                    }

            }
            else{
                sb.append("No file was selected");
            }
        }
    }