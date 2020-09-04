public class Loader {
   private final int identifier;
   private final Cruise cruise;

   Loader(int id, Cruise cruise){
       this.identifier = id;
       this.cruise = cruise;
   }

   public boolean canServe(Cruise c){
       // The loader can only serve once it completes a service on the current cruise
       // The loader must not be currently serving a cruise
       return (c.getArrivalTime() >= getNextAvailableTime());
   }

   public Loader serve(Cruise c){
       if (this.canServe(c))
           return new Loader(this.identifier, c);
       else{
           return this;
       }
   }
   
   public int getIdentifier() {
       return identifier;
   }

   public int getNextAvailableTime(){
       return this.cruise.getServiceCompletionTime();
   }

   @Override
   public String toString() {
       return String.format("Loader %d is serving %s",this.identifier,this.cruise);
   }

}
