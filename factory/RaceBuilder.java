/**
 * Gai Shukrun : 315809863 , Vlad Forman : 206818239
 */
package factory;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer;
import utilities.EnumContainer.Color;

    public class RaceBuilder {

        private static RaceBuilder instance;
        private ClassLoader classLoader;
        private Class<?> classObject;
        private Constructor<?> constructor;

        private RaceBuilder() {}

        public static RaceBuilder getInstance() {
            if (instance == null) {
                instance = new RaceBuilder();
            }
            return instance;
        }

        public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.classLoader = ClassLoader.getSystemClassLoader();
            this.classObject = this.classLoader.loadClass(arenaType);
            this.constructor = this.classObject.getConstructor(double.class,int.class);
            return (Arena) this.constructor.newInstance(length, maxRacers);
        }

        public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, Color color) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.classLoader = ClassLoader.getSystemClassLoader();
            this.classObject = this.classLoader.loadClass(racerType);
            this.constructor = this.classObject.getConstructor(String.class,double.class,double.class, EnumContainer.Color.class);
            return (Racer) this.constructor.newInstance(name, maxSpeed, acceleration, color);
        }

        public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, Color color, int numOfWheels) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.classLoader = ClassLoader.getSystemClassLoader();
            this.classObject = this.classLoader.loadClass(racerType);

            this.constructor = this.classObject.getConstructor(String.class,double.class,double.class,EnumContainer.Color.class, int.class);

            return (Racer) this.constructor.newInstance(name, maxSpeed, acceleration, color, numOfWheels);
        }
        /**
         * Hm3
         */
        public Racer buildWheeledRacer(String racerType) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.classLoader = ClassLoader.getSystemClassLoader();
            this.classObject = this.classLoader.loadClass(racerType);
            this.constructor = this.classObject.getConstructor();
            return (Racer) this.constructor.newInstance();
        }
    }

