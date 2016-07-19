/*
 * Property
 */
package vpCamping.object;

import com.google.common.base.Objects;

/**
 *
 * @author LRC
 */
public final class UserParameters {

    private final String login;
    private final String pwd;

    private UserParameters(Builder builder) {
       this.login = builder.login;
       this.pwd = builder.pwd;
    }

   @Override
   public int hashCode() {
      return Objects.hashCode(login, pwd);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      final UserParameters other = (UserParameters) obj;
      return Objects.equal(this.login, other.login) && Objects.equal(this.pwd, other.pwd);
   }

   /**
     * @return the loginEmailValue
     */
    public String getLoginEmailValue() {
        return login;
    }

    /**
     * @return the loginPasswordValue
     */
    public String getLoginPasswordValue() {
        return pwd;
    }

   public static final class Builder{

      private final String login;
      private final String pwd;

      public Builder(String login, String pwd) {
         this.login = login;
         this.pwd = pwd;
      }

      public UserParameters build(){
         return new UserParameters(this);
      }
   }
}
