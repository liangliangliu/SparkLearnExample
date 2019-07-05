package com.learn.spark.jdk8;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
  interface Supplier<T> {
    T get();
}

public class Car {
	
	//Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

	public static void main(String[] args) {
		final Car tar = Car.create( Car::new );
		final Car car1 = Car.create( Car::new );
		final Car car2 = Car.create( Car::new );
		final Car car3 = Car.create( Car::new );
		final List< Car > cars = Arrays.asList( car1,car2,car3 );
		cars.forEach( tar::follow );
	}

}
