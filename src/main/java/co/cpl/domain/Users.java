/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

// This is an example of how to implement builder pattern for domain objects
// please build your own model based on this
package co.cpl.domain;

public class Users {

    private final String id;
    private final String name;
    private final String status;
    private final String phone;
    private final String createdAt;
    private final String updatedAt;


    public Users(String id,
                 String name,
                 String status,
                 String phone,
                 String createdAt,
                 String updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private String id;
        private String name;
        private String status;
        private String phone;
        private String createdAt;
        private String updatedAt;

        public Users.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Users.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Users.Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Users.Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Users.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Users.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Users build() {
            return new Users(id, name, status, phone, createdAt, updatedAt);
        }
    }
}
