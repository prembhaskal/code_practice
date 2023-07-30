package basics.factorypattern;

public interface Engine {

    void startEngine();

}

class CarEngine implements Engine {

    private final String carmake;

    CarEngine(String carmake) {
        this.carmake = carmake;
    }

    @Override
    public void startEngine() {
        System.out.println("car started");
    }
}

class BusEngine implements Engine {

    private final int buscapacity;
    private final String busmake;

    BusEngine(int buscapacity, String busmake) {
        this.buscapacity = buscapacity;
        this.busmake = busmake;
    }

    @Override
    public void startEngine() {
        System.out.println("bus started");
    }
}


class EngineFactory {
    Engine createEngine(String engineType) {
        if (engineType == null) {
            throw new IllegalArgumentException("null engine type");
        }
        if (engineType.equals("bus")) {
            return new BusEngine(40, "VRL");
        }
        if (engineType.equals("car")) {
            return new CarEngine("Maruti");
        }

        throw new IllegalArgumentException("unknown engine type: " + engineType);
    }
}


class Car {
    private final Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }
}

class Starter {
    public static void main(String[] args) {
        Engine carEngine = new EngineFactory().createEngine("car");
        Car car = new Car(carEngine);
    }
}
