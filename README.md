# Multithreaded Racing Simulation Framework

**Authors:** Gai Shukrun (315809863) & Vlad Forman (206818239)

## Project Overview

This is a comprehensive Java-based racing simulation game that demonstrates multiple design patterns and object-oriented programming principles. The project features a GUI interface for race management and supports various types of racers competing in different arena environments.

## 🏗️ Architecture & Design Patterns

### Design Patterns Implemented:
- **Factory Pattern** - Dynamic creation of racers and arenas using reflection
- **State Pattern** - Managing racer states (Active, Broken, Failed, Finished)
- **Observer Pattern** - GUI updates and race event notifications
- **Singleton Pattern** - RaceBuilder factory instance management

## 📁 Project Structure

```
├── GUI/              # GUI Components & Main Interface
├── States/           # State Pattern Implementation
├── factory/          # Factory Pattern for Dynamic Object Creation
├── game/             # Core Game Logic
│   ├── arenas/       # Racing Arena Types
│   └── racers/       # Racer Types & Implementations
└── utilities/        # Utility Classes & Enums
```

## 🎮 Features

### Supported Arena Types:
- **Aerial Arena** - For flying vehicles (Airplanes, Helicopters)
- **Land Arena** - For ground vehicles (Cars, Bicycles, Horses)
- **Naval Arena** - For water vehicles (SpeedBoats, RowBoats)

### Supported Racer Types:
- **Air Racers:** Airplane, Helicopter
- **Land Racers:** Car, Bicycle, Horse
- **Naval Racers:** SpeedBoat, RowBoat

### Key Features:
- Dynamic racer and arena creation using reflection
- Real-time race simulation with mishap system
- GUI interface for race management
- State management for racer conditions
- Comprehensive race statistics and results

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Swing GUI support

### Running the Application
1. Compile all Java files
2. Run the main class: `utilities.Program`
3. For GUI interface: Run `GUI.MainScreen`

## 🎯 Core Components

### Factory Pattern (`factory/`)
- **RaceBuilder.java** - Singleton factory for creating racers and arenas dynamically

### State Pattern (`States/`)
- **RacerState.java** - Interface defining state behavior
- **ActiveState.java** - Racer actively racing
- **BrokenState.java** - Racer temporarily disabled
- **FailedState.java** - Racer permanently out of race
- **FinishedState.java** - Racer completed the race

### Game Logic (`game/`)
- **Arena.java** - Abstract base class for all racing environments
- **Racer.java** - Abstract base class for all racing participants

### Utilities (`utilities/`)
- **Program.java** - Main entry point with race demonstrations
- **EnumContainer.java** - Centralized enum definitions
- **Point.java** - 2D coordinate system
- **Fate.java** - Random event generation
- **Mishap.java** - Race incident management

## 🎨 GUI Features (`GUI/`)
- Interactive race setup and management
- Real-time race visualization
- Racer statistics and information display
- Arena configuration options

## 🏁 Race Mechanics

### Race Flow:
1. Arena initialization with specified parameters
2. Racer creation and validation
3. Race start with racer introductions
4. Turn-based race progression
5. Mishap handling and state transitions
6. Race completion and results display

### Mishap System:
- Random events affecting racer performance
- State transitions based on mishap severity
- Recovery mechanisms for temporary failures

## 📊 Example Output

The program demonstrates three different race scenarios:
- Air race with airplanes and helicopters
- Land race with cars, horses, and bicycles  
- Naval race with speedboats and rowboats

Each race shows racer introductions, mishap events, and final rankings.

## 🔧 Technical Implementation

### Reflection Usage:
- Dynamic class loading for extensibility
- Runtime object creation based on string parameters
- Flexible factory pattern implementation

### Exception Handling:
- Custom exceptions for race-specific errors
- Graceful degradation when reflection fails
- Comprehensive error reporting

## 📝 Notes

This project serves as an educational example of:
- Object-oriented design principles
- Design pattern implementation
- Java Swing GUI development
- Reflection and dynamic programming
- Event-driven programming

---

*This project was developed as part of an Object-Oriented Programming course, demonstrating advanced Java concepts and design patterns.*
