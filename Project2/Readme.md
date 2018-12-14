# repo-Team25
Team25 (Wang, Wang, Wang)

1)  Yuanli Wang (wang8662)
2)  Yizhe Wang (wang5959)
3)  Dengyuan Wang (wang8660)

Expectations:  
1)  I agreed to do as a team (everyone agrees)
2)  Evening work
3)  We will start when assignment is posted.

Default login account:
Username: 3WUser
Password: 5801

## Explanation of the output

### Here is a sample of our output in IR voting:

```
CandidatesName: Rosen (D)  Kleinberg (R)  Chou (I)  Royce(L) 
FirstChoice:  5  4  5  2 
CandidatesName: Rosen (D)  Kleinberg (R)  Chou (I)  Royce(L) 
 1Changes:  1  0  1  X 
 1Choice:  6  4  6  -1 
CandidatesName: Rosen (D)  Kleinberg (R)  Chou (I)  Royce(L) 
 2Changes:  2  X  2  0 
 2Choice:  8  -1  8  -1 
CandidatesName: Rosen (D)  Kleinberg (R)  Chou (I)  Royce(L) 
 3Changes:  5  0  X  0 
 3Choice:  13  -1  -1  -1 
Winner: Rosen (D)
```

Choice means the current result of ballots of each candidate. -1 means this candidate has been __

Changes means the changes of ballots in current round. X means this candidate will be __ since he/she got minium rank#1 ballots

### Here is a sample of our output in OPL voting:

```
PartyName: D R 
Votes num:6 3 
Seats num:2 1 
Votes num:0 0 
Seats num:2 1 
CandidateWinSeatsName: Foster Pike Borg 
Party name of them:D D R
```

"Votes num && Seats num" means the number of seats of each party in each round.

CandidateWinSeatsName means the candidates who get the seats, and their party.

