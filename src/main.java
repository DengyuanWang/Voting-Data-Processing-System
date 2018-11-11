import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.print("hello world\n");
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        Data_IO D_IO = new Data_IO();
        if(D_IO.specify_path_in("./test.csv")==false)
        {
            System.out.print("Specify path fail\n");
        }

        if(D_IO.load_data()==false)
            System.out.print("Load data fail\n");

        Voting VO=new Voting();
        VO.InitVoting(D_IO);

        if(VO.VoteType.equals("IR"))
        {
            String winner;
            winner = VO.IR_Voting();
            System.out.println(winner);
        }
        if(VO.VoteType.equals("OPL"))
        {
            int numofseats=9;       //TODO: add numofseats in Data_IO.java
            int[] OPLans=VO.OPL_Voting(numofseats);
        }
    }
}
