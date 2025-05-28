0. Rate limiting:
     1. Rate Limiting:
    Benefits:
	    Resource Starvation; due to DoS
		eg: 
		 300 tweets/3 hour per person
		 300 read/60 sec for google read
        Reduces Cost;
         Limiting access requests means fewer servers and allocating more resources to high priority APIs
         in case of 3rd party APIs; per call Charges means limiting calls and hence reduces cost.
    Client Side Vs Server Side API limiter?? Diff
	  Clinet Sie:
	     At application code/ internal tools
		 chances to implement at client be different and can cause APIs to abused
		 Used when USer Experience is priority
	  Server-Side:
         for Public APIs where Client can't be trusted
         -ve: can add to latency		 

     Throttle Rules:
             based on IP, UserId or Other properties
    Need to inform User about being throttled			 
	
	API Gateway:
	   deals with RAte Limiting, SSL Termination, authentication, IP whitelisting, serving static content
	To Store token/Counters:
           The counter per user is stored in in-memory DataBAse as;
    fast to retrieve and has time based expiration Stretegy
    HTTP 429// error code for Too Many Requests
    Techniques:
          1. Sliing Window
		  2. Fixed Widnow
		  2. Token Bucket
		  4. leaky Bucket
2. URL shortner
3. Cache
4. ThreadPool
5. LoggingSystem
6. Distributed loging System
