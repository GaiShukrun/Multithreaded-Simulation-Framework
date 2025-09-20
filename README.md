<div align="center">
<h1>MULTITHREADED RACING SIMULATION FRAMEWORK</h1>

A comprehensive Java-based racing simulation demonstrating advanced design patterns and object-oriented programming principles
</div>

<div align="center">

**Authors:** Gai Shukrun 

[![Java](https://img.shields.io/badge/java-8+-orange)]()
[![Swing](https://img.shields.io/badge/swing-GUI-blue)]()

</div>

## Built with the tools and technologies:

<div align="center">

[![Java](https://img.shields.io/badge/-Java-black?style=flat-square&logo=oracle)]()
[![Swing](https://img.shields.io/badge/-Swing-black?style=flat-square&logo=java)]()
[![Design Patterns](https://img.shields.io/badge/-Design%20Patterns-black?style=flat-square&logo=java)]()
[![OOP](https://img.shields.io/badge/-OOP-black?style=flat-square&logo=java)]()

[![Factory Pattern](https://img.shields.io/badge/-Factory%20Pattern-red?style=flat-square&logo=java)]()
[![State Pattern](https://img.shields.io/badge/-State%20Pattern-red?style=flat-square&logo=java)]()
[![Observer Pattern](https://img.shields.io/badge/-Observer%20Pattern-yellow?style=flat-square&logo=java)]()
[![Singleton Pattern](https://img.shields.io/badge/-Singleton%20Pattern-green?style=flat-square&logo=java)]()

[![Reflection](https://img.shields.io/badge/-Reflection-blue?style=flat-square&logo=java)]()
[![Multithreading](https://img.shields.io/badge/-Multithreading-blue?style=flat-square&logo=java)]()
[![Exception Handling](https://img.shields.io/badge/-Exception%20Handling-purple?style=flat-square&logo=java)]()

</div>

## Overview

The Multithreaded Racing Simulation Framework transforms complex racing mechanics into an interactive, educational platform. Built with advanced Java concepts and design patterns, it provides computer science students, developers, and educators with a comprehensive example of object-oriented programming principles, design pattern implementation, and GUI development using Java Swing.


## Application Screenshots

*Screenshots will be added by the user*

## Core Features

### 🏁 Multi-Arena Racing System
- **Three Arena Types**: Aerial, Land, and Naval environments with unique characteristics
- **Dynamic Arena Creation**: Factory pattern implementation for flexible arena instantiation
- **Environment-Specific Rules**: Each arena type enforces racer compatibility and environmental effects
- **Scalable Architecture**: Easy addition of new arena types through inheritance

### 🏎️ Diverse Racer Types
- **Air Racers**: Airplanes with landing gear, Helicopters with hovering capabilities
- **Land Racers**: Cars with engine types, Bicycles with gear systems, Horses with breed characteristics
- **Naval Racers**: SpeedBoats with engine power, RowBoats with team configurations
- **Wheeled Interface**: Special handling for vehicles with wheel-based mechanics

### 🔄 Advanced State Management
- **State Pattern Implementation**: Clean state transitions for racer conditions
- **Four Racer States**: Active (racing), Broken (temporary failure), Failed (permanent), Finished (completed)
- **Dynamic State Changes**: Real-time state updates based on race events and mishaps
- **State Persistence**: Maintains racer condition throughout race duration

### 🏭 Dynamic Object Creation
- **Factory Pattern**: Singleton RaceBuilder for runtime object instantiation
- **Reflection-Based Creation**: Dynamic class loading and constructor invocation
- **Type Safety**: Comprehensive exception handling for invalid configurations
- **Extensibility**: Easy addition of new racer and arena types without code modification

### 🎮 Interactive GUI Interface
- **Swing-Based UI**: Professional desktop application with multiple panels
- **Real-Time Visualization**: Live race progress with racer positioning and status updates
- **Configuration Panels**: Interactive setup for arenas, racers, and race parameters
- **Observer Pattern**: Decoupled GUI updates through event notification system

### 🎲 Sophisticated Race Mechanics
- **Mishap System**: Random events affecting racer performance with probability calculations
- **Physics Simulation**: Speed, acceleration, and friction calculations for realistic movement
- **Turn-Based Progression**: Structured race advancement with comprehensive logging
- **Results Management**: Detailed race statistics and final rankings

## Quick Start

The easiest way to run the application:

### Console Version
```bash
javac utilities/Program.java
java utilities.Program
```

### GUI Version
```bash
javac GUI/MainScreen.java
java GUI.MainScreen
```

## Manual Setup

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **Java Runtime Environment (JRE)** for execution
- **IDE** (recommended: IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/GaiShukrun/Multithreaded-Simulation-Framework.git
   cd Multithreaded-Simulation-Framework
   ```

2. Compile all Java files:

   ```bash
   javac -cp . utilities/*.java factory/*.java States/*.java game/**/*.java GUI/*.java
   ```

3. Run the console application:

   ```bash
   java utilities.Program
   ```

4. Or run the GUI application:

   ```bash
   java GUI.MainScreen
   ```

### Build for Distribution

Create a JAR file for easy distribution:

```bash
jar cvfm RacingSimulation.jar META-INF/MANIFEST.MF *.class **/*.class
java -jar RacingSimulation.jar
```

## Tech Stack

- **Core Language**: Java 8+ with advanced OOP concepts
- **GUI Framework**: Java Swing for desktop application interface
- **Design Patterns**: Factory, State, Observer, and Singleton implementations
- **Concurrency**: Multithreading for race simulation and GUI responsiveness
- **Reflection**: Dynamic class loading and object instantiation
- **Exception Handling**: Custom exceptions for race-specific error management

## Key Components

### Factory Pattern Implementation
- **RaceBuilder**: Singleton factory managing dynamic object creation using reflection
- **Flexible Instantiation**: Support for both standard and wheeled racer construction
- **Type Safety**: Comprehensive exception handling for invalid class loading and instantiation

### State Pattern Architecture
- **RacerState Interface**: Contract defining state behavior for all racer conditions
- **Concrete States**: ActiveState, BrokenState, FailedState, and FinishedState implementations
- **State Transitions**: Clean, predictable state changes based on race events

### Observer Pattern Integration
- **GUI Updates**: Real-time interface updates through observer notifications
- **Decoupled Architecture**: Separation of game logic from presentation layer
- **Event-Driven Design**: Responsive user interface with automatic updates

### Arena System
- **Abstract Arena**: Base class defining common racing environment behavior
- **Specialized Arenas**: AerialArena, LandArena, and NavalArena with unique characteristics
- **Racer Validation**: Type checking to ensure compatible racer-arena combinations

### Racer Hierarchy
- **Abstract Racer**: Base class with common racing behavior and state management
- **Specialized Racers**: Type-specific implementations with unique characteristics and capabilities
- **Wheeled Interface**: Additional behavior for vehicles with wheel-based mechanics

## Development Features

### Object-Oriented Design
- **Inheritance Hierarchies**: Well-structured class relationships with proper abstraction
- **Polymorphism**: Runtime behavior determination through method overriding
- **Encapsulation**: Proper data hiding and controlled access through getters/setters

### Design Pattern Implementation
- **Educational Value**: Clear examples of Gang of Four design patterns in practical use
- **Best Practices**: Industry-standard implementation approaches and coding conventions
- **Extensibility**: Architecture designed for easy addition of new features and components

### Exception Handling
- **Custom Exceptions**: RacerLimitException and RacerTypeException for domain-specific errors
- **Graceful Degradation**: Fallback mechanisms when reflection operations fail
- **Comprehensive Logging**: Detailed error reporting and race event tracking

## Educational Value

This project serves as a comprehensive example of:
- **Advanced Java Programming**: Reflection, multithreading, and GUI development
- **Design Pattern Implementation**: Practical application of multiple design patterns
- **Object-Oriented Principles**: Inheritance, polymorphism, encapsulation, and abstraction
- **Software Architecture**: Clean code organization and separation of concerns
- **Exception Handling**: Robust error management and recovery strategies

## Contributing

Please read our contributing guidelines before submitting pull requests to the project.

## License

This project was developed as part of an Object-Oriented Programming course, demonstrating advanced Java concepts and design patterns.
