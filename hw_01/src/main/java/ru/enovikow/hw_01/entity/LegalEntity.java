package ru.enovikow.hw_01.entity;

public class LegalEntity extends Client {
    private final String address;

    protected LegalEntity(LegalEntityBuilder<?, ?> b) {
        super(b);
        this.address = b.address;
    }

    public static LegalEntityBuilder<?, ?> builder() {
        return new LegalEntityBuilderImpl();
    }

    public abstract static class LegalEntityBuilder<C extends LegalEntity, B extends LegalEntityBuilder<C, B>> extends Client.ClientBuilder<C, B> {
        private String address;

        public LegalEntityBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B address(String address) {
            this.address = address;
            return this.self();
        }

        public String toString() {
            String baseEntity = super.toString();
            return "LegalEntity.LegalEntityBuilder(super=" + baseEntity + ", address=" + this.address + ")";
        }
    }

    private static final class LegalEntityBuilderImpl extends LegalEntityBuilder<LegalEntity, LegalEntityBuilderImpl> {
        private LegalEntityBuilderImpl() {
        }

        protected LegalEntityBuilderImpl self() {
            return this;
        }

        public LegalEntity build() {
            return new LegalEntity(this);
        }
    }

    public String getAddress() {
        return this.address;
    }
}
