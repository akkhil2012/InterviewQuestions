# InterviewQuestions
InterviewQuestions

===================
MICROSOFT INTERVIEW
====================
Design the Concurrent editing doc application like Google Doc (LLD + HLD)
NFR: The Doc mostly contains text
     Read/Write ratio is equivalent
	 Max. 10 people will be allowed to edit at moment(WRITE access)
	 Access to be role based.
	 Read Access count NO limit
	 Need to maintain the Version of Doc (Max 10 versions)
	 How to ensure that the Data is NOT inconsistent 
	    How about if same line is edited (Conflict)  Shall we defeer it to users to resolve/ Recommend based on
	 How to define the WRITE/READ event; to capture live events ( thus to maintain count of Users logged to updated Doc at any moment)	
	 How to unblock for new Write request ; if count of user accessing Document has already reached Celing: Edit frequency based/timeOut
     How to decide the timeOut if a user is Active BUt Stale.
     How to change permission from WRITE to READ if User has write permission BUT User request access after celing is reached.
	     Shall It be at RUnTime; using Cache ciuld be an option?
		  Save to DB and than change ROLE from Write to READ in DB
     Security aspects from infrasature (as Reverse Proxy)
     How to keep access safe ( Using Vault Connection as to store DB connection in AWS)
     What are the Performance BottleNecks,
