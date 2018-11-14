/** Voting System
 * Code for counting votes
 *@author Yuanli Wang
 *@version V1.0
 */

import java.lang.reflect.Array;
import java.util.*;

public class Voting_System {
    private Data_IO Ballotdata;
    public String VoteType;
    public int numofballot;
    public int numofcand;
    public Audit auditfile;

    public static TreeMap<Integer, Integer> sortMapByValue(HashMap<Integer, Integer> map){
        Comparator<Integer> comparator = new ValueComparator(map);
        //TreeMap is a map sorted by its keys.
        //The comparator is used to sort the TreeMap by keys.
        TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>(comparator);
        result.putAll(map);
        return result;
    }
    /**
     sort map by value
     @param map a hashmap
     @return sorted map
     */

    public boolean process_voting(Data_IO _data)
    {
    	auditfile= new Audit(_data);
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
    /**
     process voting data
     @param data Data_IO object
     @return return status
     */

    public String IR_Voting()
    {
        int[][] Cand_Ballot;    //Cand_Ballot[i][j]: the number of ballots received by candidate#i with ranking#j
        int NUMTERM=0;
        Vector IRaudit = new Vector();
        boolean win=false;
        int IRwinner=-1;
        Cand_Ballot=new int[numofcand][numofcand+1];
        do {
            IR_frame tmp=new IR_frame();
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
                    tmp.Cand_Ballot=Cand_Ballot;
                    tmp.Winner=IRwinner;
                    tmp.islastterm=true;

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
                tmp.Cand_Ballot=Cand_Ballot;
                tmp.candidate_fail=min1stcandidx;
                tmp.islastterm=false;
            }
            IRaudit.addElement(tmp);
            NUMTERM++;
        }while(!win);
        auditfile.IRVotingprocess=new IR_frame[NUMTERM];
        IRaudit.copyInto(auditfile.IRVotingprocess);
        return Ballotdata.data[0].get_candidate(IRwinner);
    }
    /**
     process IR voting data
     @return return winner
     */

    public HashMap<String, Integer> OPL_Voting()
    {
        int numofseats=Ballotdata.data[0].get_numofseat();
        int[] OPLvote=new int[numofcand];       //votes
        HashMap<String,Integer> OPLcandres=new HashMap<>();        //allocated seats
        HashMap<String, String> OPLcandparty=new HashMap<>();
        HashMap<String, Integer> OPLpartyvote=new HashMap<>();
        HashMap<String, Integer> OPLpartyres=new HashMap<>();
        HashMap<String, Integer> OPLpartyremain=new HashMap<>();     //remaining votes
        int quota=numofballot/numofseats;
        int undecidedseat=numofseats;
        for(int i=0;i<numofballot;i++)
        {
            int voteidx=Ballotdata.data[i].get_cand_from_rank(1);
            String partyofcand=Ballotdata.data[i].get_party(voteidx);
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
        auditfile.OPLVotingprocess=new OPL_frame[3];
        auditfile.OPLVotingprocess[0]=new OPL_frame();
        auditfile.OPLVotingprocess[0].PartyVotes=OPLpartyvote;
        auditfile.OPLVotingprocess[0].partyFinish=false;
        auditfile.OPLVotingprocess[0].PartySeats=(HashMap<String, Integer>) OPLpartyres.clone();

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
        }
        for(String party: OPLpartyvote.keySet())
        {
            System.out.print(party);
            System.out.print("==");
            System.out.println(OPLpartyres.get(party));
        }

        auditfile.OPLVotingprocess[1]=new OPL_frame();
        auditfile.OPLVotingprocess[1].partyFinish=false;
        auditfile.OPLVotingprocess[1].PartyVotes=OPLpartyremain;
        auditfile.OPLVotingprocess[1].PartySeats=OPLpartyres;

        for(String party: OPLpartyvote.keySet())
        {
            System.out.print(party);
            System.out.print("---");
            System.out.print(OPLpartyres.get(party));
            System.out.println("---");

            HashMap<Integer, Integer> candinparty = new HashMap<Integer, Integer>();
            for(int i=0;i<numofcand;i++)
            {
                if(Ballotdata.data[0].get_party(i).equals(party)) {
                    candinparty.put(i, OPLvote[i]);
                }
            }
            TreeMap<Integer, Integer> sortedMap = sortMapByValue(candinparty);
            System.out.println(sortedMap);

            Set<Integer> keys=sortedMap.keySet();
            Iterator<Integer> iter=keys.iterator();
            for(int i=0;i<OPLpartyres.get(party);i++)
            {
                int idx=iter.next();
                System.out.print(Ballotdata.data[0].get_party(idx));
                System.out.print("***");
                System.out.print(Ballotdata.data[0].get_candidate(idx));
                System.out.print("***");
                System.out.print(i+1);
                System.out.println("");
                OPLcandres.put(Ballotdata.data[0].get_candidate(idx),i+1);
                OPLcandparty.put(Ballotdata.data[0].get_candidate(idx), Ballotdata.data[0].get_party(idx));
            }
        }

        auditfile.OPLVotingprocess[2]=new OPL_frame();
        auditfile.OPLVotingprocess[2].partyFinish=true;
        auditfile.OPLVotingprocess[2].PartySeats=OPLpartyres;
        auditfile.OPLVotingprocess[2].CandSeats=OPLcandres;
        auditfile.OPLVotingprocess[2].CandParty=OPLcandparty;

        System.out.println(auditfile.OPLVotingprocess[0].PartySeats);
        System.out.println(auditfile.OPLVotingprocess[1].PartySeats);
        System.out.println(OPLcandres);
        return OPLcandres;
    }
    /**
     process OPL voting data
     @return return result within a hashmap
     */
}
