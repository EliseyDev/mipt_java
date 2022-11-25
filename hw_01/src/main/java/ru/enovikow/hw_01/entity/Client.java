package ru.enovikow.hw_01.entity;

public abstract class Client {
    private final String name;
    private final int inn;

    protected Client(ClientBuilder<?, ?> b) {
        this.name = b.name;
        this.inn = b.inn;
    }

    protected abstract static class ClientBuilder<C extends Client, B extends ClientBuilder<C, B>> {
        private String name;
        private int inn;

        public ClientBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B name(String name) {
            this.name = name;
            return this.self();
        }

        public B inn(int inn) {
            this.inn = inn;
            return this.self();
        }

        public String getName() {
            return this.name;
        }

        public int getInn() {
            return this.inn;
        }

        public String toString() {
            return "Client.ClientBuilder(name=" + this.name + ", inn=" + this.inn + ")";
        }
    }

}
