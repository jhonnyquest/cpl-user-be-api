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

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * PaymentRequestDto: Data transformation object for json transformation of payment request object
 * @author jmunoz
 * @since 05/08/2018
 * @version 1.0.0
 */
public class UserDto {

    private  String id;
    private  String name;
    private  String status;
    private  String phone;
    private  String createdAt;
    private  String updatedAt;

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

    @JsonProperty("createdAt")
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() { return updatedAt;  }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt;  }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
