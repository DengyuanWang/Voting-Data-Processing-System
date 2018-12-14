/** Voting System
 * Code for counting votes
 *@author Yuanli Wang
 *@version V1.0
 */

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Voting_System {
    private Data_IO Ballotdata;
    public String VoteType;
    public int numofballot;
    public int numofcand;
    public Audit auditfile;

    public int[][] Cand_Ballot;    //Cand_Ballot[i][j]: the number of ballots received by candidate#i with ranking#j
    public Set<Integer> invballot;
    public Vector IRaudit;
    public Vector IRinvalid;
    public int NUMTERM=0, IRwinner=-1;
    public boolean win=false, fstrun=true;

    public int numofseats;
    public int[] OPLvote;       //votes
    public HashMap<String,Integer> OPLcandres;        //allocated seats
    public HashMap<String, String> OPLcandparty;
    public HashMap<String, Integer> OPLpartyvote;
    public HashMap<String, Integer> OPLpartyres;
    public HashMap<String, Integer> OPLpartyremain;     //remaining votes

    /**
     sort map by value
     @param map a hashmap
     @return sorted map
     */
    private static TreeMap<Integer, Integer> sortMapByValue(HashMap<Integer, Integer> map){
        Comparator<Integer> comparator = new ValueComparator(map);
        //TreeMap is a map sorted by its keys.
        //The comparator is used to sort the TreeMap by keys.
        TreeMap<Integer, Integer> result = new TreeMap<Integer, Integer>(comparator);
        result.putAll(map);
        return result;
    }

    /**
     pick a random number
     @param x input number x
     @param y another input number y
     @return return x or y randomly
     */
    private static int pickRandom(int x, int y){
        int array[]=new int[2];
        array[0]=x;
        array[1]=y;
        int length=array.length;
        Random random = new Random();
        //System.out.println("RANDOM:  "+x+" , "+y+"=="+array[random.nextInt(length)]);
        return array[random.nextInt(length)];
    }

    /**
     process voting data
     @param _data Data_IO object
     @return return status
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
            Cand_Ballot=new int[numofcand][numofcand+1];
            invballot=new HashSet<>();
            IRaudit = new Vector();
            IRinvalid = new Vector();
            NUMTERM=0; IRwinner=-1;
            win=false; fstrun=true;

            String winner;
            winner = IR_Voting();
            System.out.println(winner);
        }
        if(VoteType.equals("OPL"))
        {
            numofseats=Ballotdata.data[0].get_numofseat();
            OPLvote=new int[numofcand];       //votes
            OPLcandres=new HashMap<>();        //allocated seats
            OPLcandparty=new HashMap<>();
            OPLpartyvote=new HashMap<>();
            OPLpartyres=new HashMap<>();
            OPLpartyremain=new HashMap<>();     //remaining votes
            auditfile.OPLVotingprocess=new OPL_frame[3];
            auditfile.OPLVotingprocess[0]=new OPL_frame();
            auditfile.OPLVotingprocess[1]=new OPL_frame();
            auditfile.OPLVotingprocess[2]=new OPL_frame();
            auditfile.OPLVotingprocess[0].partyFinish=false;
            auditfile.OPLVotingprocess[1].partyFinish=false;
            auditfile.OPLVotingprocess[2].partyFinish=true;

            HashMap<String, Integer> OPLans=OPL_Voting();
        }
        return true;        //TODO: return status
    }

    private void IR_Voting_prepare()
    {
        for(int j=0;j<numofballot;j++) {
            int numofranks=0;
            for(int i=0;i<numofcand;i++) {
                int candrank=Ballotdata.data[j].get_rank_from_cand(i); //rank of candidate#i in ballot#j
                if(candrank!=-1)   numofranks++;
            }
            if(numofranks<numofcand/2) {       //invalid ballot
                IRinvalid.add(j);
                invballot.add(j);
            }
        }
    }

    private void IR_Voting_saveres()
    {
        auditfile.IRVotingprocess=new IR_frame[NUMTERM];
        IRaudit.copyInto(auditfile.IRVotingprocess);
        auditfile.IRVInvalidBallots=new Integer[IRinvalid.size()];
        auditfile.IRVInvalidBallots_size=IRinvalid.size();
        if(IRinvalid.size()>0)    IRinvalid.copyInto(auditfile.IRVInvalidBallots);
    }

    private void IR_Voting_count_ballot()
    {
        for (int j = 0; j < numofballot; j++) {
            if (!invballot.contains(j)) {
                for (int i = 0; i < numofcand; i++) {
                    int candrank = Ballotdata.data[j].get_rank_from_cand(i); //rank of candidate#i in ballot#j
                    if (candrank != -1)    Cand_Ballot[i][candrank]++;
                }
            }
        }
    }

    /**
     process IR voting data
     @return return winner
     */
    private String IR_Voting()
    {   IR_Voting_prepare();
        IR_Voting_count_ballot();
        do {IR_frame tmp=new IR_frame();
            int numofhalfballot=(int)Math.floor((double) (numofballot-IRinvalid.size())/2.0), min1stcandval=Cand_Ballot[0][1], min1stcandidx=0;
            for(int i=0;i<numofcand;i++) {
                if(!win && Cand_Ballot[i][1]>numofhalfballot)      //greater than half, winner
                    IRwinner = i;
                else if(win && Cand_Ballot[i][1]>numofhalfballot)
                    IRwinner = pickRandom(i,IRwinner);
                if(Cand_Ballot[i][1]>numofhalfballot)
                {   win=true;
                    tmp.Cand_Ballot=new int[numofcand][numofcand+1];
                    for(int q=0;q<Cand_Ballot.length;q++) {
                        tmp.Cand_Ballot[q]=Cand_Ballot[q].clone(); }
                    tmp.Winner=IRwinner;
                    tmp.islastterm=true;
                }
                if(Cand_Ballot[i][1]<min1stcandval && Cand_Ballot[i][1]!=-1) {   //find candidate with min ranking#1
                    min1stcandval = Cand_Ballot[i][1];
                    min1stcandidx = i;
                }
                else if(Cand_Ballot[i][1]==min1stcandval && Cand_Ballot[i][1]!=-1) {   //choose random one while finding 2 candidates with min ranking#1
                    min1stcandval = Cand_Ballot[i][1];
                    min1stcandidx = pickRandom(min1stcandidx, i);
                }
            }
            if(!win) {
                tmp.Cand_Ballot=new int[numofcand][numofcand+1];
                for(int i=0;i<Cand_Ballot.length;i++)
                    tmp.Cand_Ballot[i]=Cand_Ballot[i].clone();
                Cand_Ballot[min1stcandidx][1]=-1;       //candidate #min1stcandidx is defeated
                for(int i=0;i<numofballot;i++) {
                    if(!invballot.contains(i)) {
                        if (Ballotdata.data[i].get_cand_from_rank(1) == min1stcandidx) {    //for the ballots which elect min1stcandidx as ranking#1 ,
                            int cand2nd = Ballotdata.data[i].get_cand_from_rank(2);
                            if (cand2nd != -1)    Cand_Ballot[cand2nd][1]++;  //add to the ranking#2 of these ballots
                        }
                    }
                }
                tmp.candidate_fail=min1stcandidx;
                tmp.islastterm=false;
            }
            IRaudit.addElement(tmp);    NUMTERM++;
        }while(!win);
        IR_Voting_saveres();
        return Ballotdata.data[0].get_candidate(IRwinner);
    }

    /**
     process OPL voting data
     @return return result within a hashmap
     */
    private HashMap<String, Integer> OPL_Voting()
    {   int quota=numofballot/numofseats, undecidedseat=numofseats;
        for(int i=0;i<numofballot;i++) {
            int voteidx=Ballotdata.data[i].get_cand_from_rank(1);
            String partyofcand=Ballotdata.data[i].get_party(voteidx);
            OPLpartyvote.put(partyofcand, OPLpartyvote.getOrDefault(partyofcand, 0) + 1);
            OPLvote[voteidx]++;
        }
        for(String party: OPLpartyvote.keySet()) {   ////First Allocation of Seats by parties
            int vootes = OPLpartyvote.get(party);
            OPLpartyres.put(party, vootes/quota );
            OPLpartyremain.put(party, vootes%quota);
            undecidedseat-=OPLpartyres.get(party);
        }
        auditfile.OPLVotingprocess[0].PartyVotes=OPLpartyvote;
        auditfile.OPLVotingprocess[0].PartySeats=(HashMap<String, Integer>) OPLpartyres.clone();
        while(undecidedseat!=0) {            //Second Allocation of Seats
            int tmpval=-1;     String tmppty="";
            undecidedseat--;
            for(String party: OPLpartyvote.keySet()) {
                if(OPLpartyremain.get(party)>tmpval) {
                    tmpval=OPLpartyremain.get(party);
                    tmppty=party;
                }
            }
            OPLpartyres.put(tmppty, OPLpartyres.get(tmppty) + 1);
        }
        auditfile.OPLVotingprocess[1].PartyVotes=OPLpartyremain;
        auditfile.OPLVotingprocess[1].PartySeats=OPLpartyres;
        for(String party: OPLpartyvote.keySet()) {
            HashMap<Integer, Integer> candinparty = new HashMap<Integer, Integer>();
            for(int i=0;i<numofcand;i++) {
                if(Ballotdata.data[0].get_party(i).equals(party))
                    candinparty.put(i, OPLvote[i]);
            }
            TreeMap<Integer, Integer> sortedMap = sortMapByValue(candinparty);
            Set<Integer> keys=sortedMap.keySet();
            Iterator<Integer> iter=keys.iterator();
            for(int i=0;i<OPLpartyres.get(party);i++) {
                int idx=iter.next();
                OPLcandres.put(Ballotdata.data[0].get_candidate(idx),i+1);
                OPLcandparty.put(Ballotdata.data[0].get_candidate(idx), Ballotdata.data[0].get_party(idx));
            }
        }
        auditfile.OPLVotingprocess[2].PartySeats=OPLpartyres;
        auditfile.OPLVotingprocess[2].CandSeats=OPLcandres;
        auditfile.OPLVotingprocess[2].CandParty=OPLcandparty;
        return OPLcandres;
    }
}
