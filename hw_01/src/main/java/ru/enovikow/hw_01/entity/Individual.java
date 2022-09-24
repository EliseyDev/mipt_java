package ru.enovikow.hw_01.entity;

public class Individual extends Client {
    private final int age;

    public Individual(IndividualBuilder<?, ?> b) {
        super(b);
        this.age = b.age;
    }

    public static IndividualBuilder<?, ?> builder() {
        return new IndividualBuilderImpl();
    }

    public abstract static class IndividualBuilder<C extends Individual, B extends IndividualBuilder<C, B>> extends Client.ClientBuilder<C, B> {
        private int age;

        public IndividualBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public B age(int age) {
            this.age = age;
            return this.self();
        }

        public String toString() {
            String baseEntity = super.toString();
            return "Individual.IndividualBuilder(super=" + baseEntity + ", age=" + this.age + ")";
        }
    }

    private static final class IndividualBuilderImpl extends IndividualBuilder<Individual, IndividualBuilderImpl> {
        private IndividualBuilderImpl() {
        }

        protected IndividualBuilderImpl self() {
            return this;
        }

        public Individual build() {
            return new Individual(this);
        }
    }

    public int getAge() {
        return this.age;
    }
}
