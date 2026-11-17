package com.pegasus.odontosys.util;

public record JWTUserData(Long userId, String email) {

    // Método estático para iniciar o Builder de forma fluida
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long userId;
        private String email;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public JWTUserData build() {
            return new JWTUserData(userId, email);
        }
    }
}