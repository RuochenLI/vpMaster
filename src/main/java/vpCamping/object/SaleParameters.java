/*
 * Property
 */
package vpCamping.object;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * @author LRC
 */
public final class SaleParameters {

   private String saleIdValue;
   private String expectedMarkValue;
   private List<String> expectedSubMenuList;
   private List<String> expectedSizeList;
   private int scrollPageFrequence;

   private SaleParameters(Builder builder) {
      this.saleIdValue = builder.saleIdValue;
      this.expectedMarkValue = builder.expectedMarkValue;
      this.expectedSizeList = builder.expectedSizeList;
      this.expectedSubMenuList = builder.expectedSubMenuList;
      this.scrollPageFrequence = builder.scrollPageFrequence;
   }

   /**
    * @return the saleIdValue
    */
   public String getSaleIdValue() {
      return saleIdValue;
   }

   /**
    * @return the expectedMarkValue
    */
   public String getExpectedMarkValue() {
      return expectedMarkValue;
   }

   /**
    * @return the expectedSubMenuList
    */
   public List<String> getExpectedSubMenuList() {
      return expectedSubMenuList;
   }

   /**
    * @return the expectedSizeList
    */
   public List<String> getExpectedSizeList() {
      return expectedSizeList;
   }

   /**
    * @return the scrollPageFrequence
    */
   public int getScrollPageFrequence() {
      return scrollPageFrequence;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(saleIdValue, expectedMarkValue, expectedSubMenuList, expectedSizeList, scrollPageFrequence);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      final SaleParameters other = (SaleParameters) obj;
      return Objects.equal(this.saleIdValue, other.saleIdValue) && Objects.equal(this.expectedMarkValue, other.expectedMarkValue) && Objects.equal(this.expectedSubMenuList, other.expectedSubMenuList) && Objects.equal(this.expectedSizeList, other.expectedSizeList) && Objects.equal(this.scrollPageFrequence, other.scrollPageFrequence);
   }

   public static final class Builder {

      private String saleIdValue;
      private String expectedMarkValue;
      private List<String> expectedSubMenuList;
      private List<String> expectedSizeList;
      private int scrollPageFrequence;

      public Builder() {

      }

      public Builder(SaleParameters saleParameters) {
         this.saleIdValue = saleParameters.saleIdValue;
         this.expectedMarkValue = saleParameters.expectedMarkValue;
         this.expectedSizeList = Lists.newArrayList(saleParameters.expectedSizeList);
         this.expectedSubMenuList = Lists.newArrayList(saleParameters.expectedSubMenuList);
         this.scrollPageFrequence = saleParameters.scrollPageFrequence;
      }

      public Builder withSaleIdValue(String saleIdValue) {
         this.saleIdValue = saleIdValue;
         return this;
      }

      public Builder withExpectedMarkValue(String expectedMarkValue) {
         this.expectedMarkValue = expectedMarkValue;
         return this;
      }

      public Builder withExpectedSubMenuList(List<String> expectedSubMenuList) {
         this.expectedSubMenuList = new ArrayList<String>(expectedSubMenuList);
         return this;
      }

      public Builder withExpecedSizeList(List<String> expecedSizeList) {
         this.expectedSizeList = new ArrayList<String>(expecedSizeList);
         return this;
      }

      public Builder withScrollPageFrequence(int scrollPageFrequence) {
         this.scrollPageFrequence = scrollPageFrequence;
         return this;
      }

      public SaleParameters build() {
         return new SaleParameters(this);
      }

   }
}
