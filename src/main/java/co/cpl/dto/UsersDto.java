/******************************************************************
 *
 * This code is for the Pappking service project.
 *
 *
 * © 2018, Pappking Management All rights reserved.
 *
 *
 ******************************************************************/
package co.cpl.dto;

import co.cpl.enums.Currency;
import co.cpl.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


/**
 * PaymentRequestDto: Data transformation object for json transformation of payment request object
 * @author jmunoz
 * @since 05/08/2018
 * @version 1.0.0
 */
public class UsersDto {

    private  String id;
    private  String name;
    private  String status;
    private  String phone;
    private  String createDate;
    private  String updateDate;

    @JsonProperty("id")
    public String getId() { return id;  }
    public void setId(String id) { this.id = id; }

    @JsonProperty("name")
    public String getName() { return name;  }
    public void setName(String name) { this.name = name;  }

    @JsonProperty("status")
    public String getStatus() { return status;  }
    public void setStatus(String status) { this.status = status;  }

    @JsonProperty("phone")
    public String getPhone() { return phone;  }
    public void setPhone(String phone) { this.phone = phone;  }

    @JsonProperty("createDate")
    public String getCreateDate() { return createDate; }
    public void setCreateDate(String createDate) { this.createDate = createDate; }

    @JsonProperty("updateDate")
    public String getUpdateDate() { return updateDate;  }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate;  }

    @Override
    public String toString() {
        return "UsersDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

}
