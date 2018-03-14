package io.healthathome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.healthathome.dto.payu.Address;
import io.healthathome.dto.payu.Buyer;
import io.healthathome.dto.payu.CreditCard;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-08T15:45:56.178Z")

public class Pay   {
  @JsonProperty("user")
  private String user;
  @JsonProperty("buyer")
  private Buyer buyer;
  @JsonProperty("shippingAddress")
  private Address shippingAddress;
  @JsonProperty("creditCard")
  private CreditCard creditCard;
  @JsonProperty("paymentMethod")
  private String paymentMethod;

  @ApiModelProperty(required = true)
  @NotNull
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }

  public Buyer getBuyer() {
    return buyer;
  }
  public void setBuyer(Buyer buyer) {
    this.buyer = buyer;
  }
  public Address getShippingAddress() {
    return shippingAddress;
  }
  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }
  public CreditCard getCreditCard() {
    return creditCard;
  }
  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }
  public String getPaymentMethod() {
    return paymentMethod;
  }
  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pay pay = (Pay) o;
    return Objects.equals(this.user, pay.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pay {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

