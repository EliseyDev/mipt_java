package ru.enovikow.hw_01.entity;

public class Holding extends Client {
    private final String address;

    protected Holding(HoldingBuilder<?, ?> b) {
        super(b);
        this.address = b.address;
    }

    public static HoldingBuilder<?, ?> builder() {
        return new HoldingBuilderImpl();
    }

    public abstract static class HoldingBuilder<C extends Holding, B extends HoldingBuilder<C, B>> extends Client.ClientBuilder<C, B> {
        private String address;

        public HoldingBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B address(String address) {
            this.address = address;
            return this.self();
        }

        public String toString() {
            String baseEntity = super.toString();
            return "Holding.HoldingBuilder(super=" + baseEntity + ", address=" + this.address + ")";
        }
    }

    private static final class HoldingBuilderImpl extends HoldingBuilder<Holding, HoldingBuilderImpl> {
        private HoldingBuilderImpl() {
        }

        protected HoldingBuilderImpl self() {
            return this;
        }

        public Holding build() {
            return new Holding(this);
        }
    }

    public String address() {
        return this.address;
    }
}
