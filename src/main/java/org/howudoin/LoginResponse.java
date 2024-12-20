package org.howudoin;

import lombok.*; // Importing Lombok annotations

@Getter
@Setter

@NoArgsConstructor
@Builder
@ToString
public class LoginResponse {

    private String token;
    private String userName;

    // Private constructor to ensure objects are created only through the builder
    private LoginResponse(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    // Getter methods
    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    // Static Builder class
    public static class Builder {
        private String token;
        private String userName;

        // Setter methods for builder
        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        // Build method to return the LoginResponse object
        public LoginResponse build() {
            return new LoginResponse(this.token, this.userName);
        }
    }

    // Method to get a new builder instance
    public static Builder builder() {
        return new Builder();
    }
}