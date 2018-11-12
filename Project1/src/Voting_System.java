import java.util.HashMap;

public class Voting_System {
    private Data_IO Ballotdata;
    public String VoteType;
    public int numofballot;
    public int numofcand;

    public boolean process_voting(Data_IO _data)
    {
        Ballotdata=_data;
        VoteType = Ballotdata.data[0].Ballot_type;
        numofballot = Ballotdata.ballot_num;
        numofcand = Ballotdata.data[0].get_candidatesnum();

        System.out.println(numofballot);
        System.out.println(numofcand);

        if(VoteType.equals("IR"))
        {
            String winner;
            winner = IR_Voting();
            System.out.println(winner);
        }
        if(VoteType.equals("OPL"))
        {
            HashMap<String, Integer> OPLans=OPL_Voting();
        }
        return true;        //TODO: return status
    }

    public String IR_Voting()
    {
        int[][] Cand_Ballot;    //Cand_Ballot[i][j]: the number of ballots received by candidate#i with ranking#j
        boolean win=false;
        int IRwinner=-1;
        Cand_Ballot=new int[numofcand][numofcand+1];
        do {
            for(int j=0;j<numofballot;j++)
            {
                for(int i=0;i<numofcand;i++)
                {
                    int candrank=Ballotdata.data[j].get_rank_from_cand(i); //rank of candidate#i in ballot#j
                    if(candrank!=-1)
                    {
                        Cand_Ballot[i][candrank]++;
                    }
                }
            }
            int numofhalfballot=numofballot/2;
            int min1stcandval=Cand_Ballot[0][1];
            int min1stcandidx=0;
            for(int i=0;i<numofcand;i++)
            {
                if(Cand_Ballot[i][1]>=numofhalfballot)      //greater than half, winner
                {
                    win=true;
                    IRwinner = i;
                }
                if(Cand_Ballot[i][1]<min1stcandval && Cand_Ballot[i][1]!=-1)    //find candidate with min ranking#1
                {
                    min1stcandval = Cand_Ballot[i][1];
                    min1stcandidx = i;
                }
            }
            if(!win)
            {
                Cand_Ballot[min1stcandidx][1]=-1;       //candidate #min1stcandidx is defeated
                for(int i=0;i<numofballot;i++)
                {
                    if(Ballotdata.data[i].get_cand_from_rank(1)==min1stcandidx)     //for the ballots which elect min1stcandidx as ranking#1 ,
                    {
                        int cand2nd=Ballotdata.data[i].get_cand_from_rank(2);
                        if(cand2nd!=-1)
                            Cand_Ballot[cand2nd][1]++;  //add to the ranking#2 of these ballots
                    }
                }
            }
        }while(!win);
        return Ballotdata.data[0].get_candidate(IRwinner);
    }

    public HashMap<String, Integer> OPL_Voting()
    {
        int numofseats=Ballotdata.data[0].get_numofseat();
        int[] OPLres=new int[numofcand];        //allocated seats
        int[] OPLvote=new int[numofcand];       //votes
        int[] OPLremain=new int[numofcand];     //remaining votes
        HashMap<String, Integer> OPLpartyvote=new HashMap<>();
        HashMap<String, Integer> OPLpartyres=new HashMap<>();
        HashMap<String, Integer> OPLpartyremain=new HashMap<>();
        int quota=numofballot/numofseats;
        int undecidedseat=numofseats;
        for(int i=0;i<numofballot;i++)
        {
            int voteidx=Ballotdata.data[i].get_cand_from_rank(1);
            String partyofcand=Ballotdata.data[i].get_party(voteidx);
            System.out.print(partyofcand);
            System.out.print("--");
            System.out.println(Ballotdata.data[i].get_candidate(voteidx));
            OPLpartyvote.put(partyofcand, OPLpartyvote.getOrDefault(partyofcand, 0) + 1);
            OPLvote[voteidx]++;
        }
        for(String party: OPLpartyvote.keySet())    ////First Allocation of Seats by parties
        {
            int vootes = OPLpartyvote.get(party);
            OPLpartyres.put(party, vootes/quota );
            OPLpartyremain.put(party, vootes%quota);
            undecidedseat-=OPLpartyres.get(party);
            System.out.print(party);
            System.out.print("--");
            System.out.print(OPLpartyres.get(party));
            System.out.print("--");
            System.out.println(OPLpartyremain.get(party));
        }
        System.out.println(undecidedseat);
        /*
        for(int i=0;i<numofcand;i++)        //First Allocation of Seats
        {
            OPLres[i]=OPLvote[i]/quota;
            undecidedseat-=OPLres[i];
            OPLremain[i]=OPLvote[i]%quota;
        }
        */

        while(undecidedseat!=0)             //Second Allocation of Seats
        {
            int tmpval=-1;
            int tmpidx=-1;
            String tmppty="";
            undecidedseat--;
            for(String party: OPLpartyvote.keySet())
            {
                if(OPLpartyremain.get(party)>tmpval)
                {
                    tmpval=OPLpartyremain.get(party);
                    tmppty=party;
                }
            }
            OPLpartyres.put(tmppty, OPLpartyres.get(tmppty) + 1);
            OPLpartyremain.put(tmppty, OPLpartyremain.get(tmppty)-1);

            /*
            for(int i=0;i<numofcand;i++)
            {
                if(OPLremain[i]>tmpval)
                {
                    tmpval=OPLremain[i];
                    tmpidx=i;
                }
            }
            OPLres[tmpidx]++;
            OPLremain[tmpidx]=-1;
            */
        }
        for(String party: OPLpartyvote.keySet())
        {
            System.out.print(party);
            System.out.print("==");
            System.out.print(OPLpartyres.get(party));
            System.out.print("==");
            System.out.println(OPLpartyremain.get(party));
        }

        return OPLpartyres;
    }
}
