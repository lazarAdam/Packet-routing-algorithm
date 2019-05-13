import java.util.LinkedList;




	
	class Router implements Runnable {
	    private LinkedList<Packet> list = new LinkedList<Packet>();
	    private int routes[];
	    private Router routers[];
	    private int routerNum;
	    private boolean end = false;
	    
	    Router(int rts[], Router rtrs[], int num) {
	        routes = rts;
	        routers = rtrs;
	        routerNum = num;
	    }
	    /*
	     * Add a packet to this router.  Add some details on how this works.
	     */
	    public  void addWork(Packet p) {

	    	synchronized(this){
	            list.add(p);
	            notify();
	        }
	    	
	    }
	    /*
	     * End the thread, once no more packets are outstanding.
	     */
	    public synchronized void end() {
	    	
	    	end = true; 
	    	
	    	notify();
	    	
	    }

	    public synchronized void networkEmpty() {
	    	
	    	
	    	notify();
	    	
	    }

	    /*
	     * Process packets.  Add some details on how this works.
	     */
	    public void run() {
	    	
	    	
	    	
	    	 Packet p;
	         while(true){
	             p = null;
	             if((end) & (routing.getPacketCount()< 0)){
	                 return;
	             }
	             synchronized(this){
	                 if(list.isEmpty()){
	                     try{
	                     wait();
	                 }catch(Exception e){

	                 }
	                 } else {
	                     p = list.pop();
	                 }
	             }

	             if(p != null){
	                 int nextRouterNum = routes[p.getDestination()]; 
	                 p.Record(routerNum);
	                 if(nextRouterNum != -1){
	                     routers[nextRouterNum].addWork(p);
	                 } else {
	                     routing.decPacketCount();
	                 }
	             }
	         }
	  
	    }
	    	
	    
	    }

