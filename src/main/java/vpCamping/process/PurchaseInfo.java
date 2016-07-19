package vpCamping.process;

import com.google.common.base.Objects;

/**
 * Created by ruli on 7/22/15.
 */
public final class PurchaseInfo {

   private int nbItem;
   private double totalValue;
   private double originalTotalValue;

   public PurchaseInfo() {
      nbItem = 0;
      totalValue = 0;
   }

   public void incrementItem() {
      nbItem++;
   }

   public void addValue(double value) {
      totalValue += value;
   }

   public void addOriginalValue(double value) {
      originalTotalValue += value;
   }

   public double getTotalValue() {
      return totalValue;
   }

   public int getNbItem() {
      return nbItem;
   }

   public double getOriginalTotalValue() {
      return originalTotalValue;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(nbItem, totalValue, originalTotalValue);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      final PurchaseInfo other = (PurchaseInfo) obj;
      return Objects.equal(this.nbItem, other.nbItem) && Objects.equal(this.totalValue, other.totalValue) && Objects.equal(this.originalTotalValue, other.originalTotalValue);
   }

   @Override
   public String toString() {
      return "Total： " +
        nbItem + "items" +
        ", costs" + totalValue +
        "euro. Save " + originalTotalValue + " euro";
   }
}
