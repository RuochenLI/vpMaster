package vpCamping.process;

/**
 * Created by ruli on 7/22/15.
 */
public final class PurchaseInfo {

   private int nbItem;

   public PurchaseInfo() {
      nbItem = 0;
   }

   public void incrementItem() {
      nbItem++;
   }


   public int getNbItem() {
      return nbItem;
   }

   @Override
   public String toString() {
      return "Total： " +
        nbItem + "items";
   }
}
