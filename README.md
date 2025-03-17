# Smart Doorbell IoT System

## Overview
A comprehensive IoT-based smart doorbell and door security system that integrates motion detection, real-time photo capture, remote door control, and mobile notifications. This project uses ESP32-CAM and Firebase to create a complete home security solution.

## Features
- **Motion Detection**: PIR sensor automatically detects presence and triggers alerts
- **Real-time Photography**: ESP32-CAM captures images when motion is detected
- **Remote Door Control**: Lock/unlock your door from anywhere using the app
- **Push Notifications**: Instant mobile alerts when someone is at your door
- **Cloud Integration**: Firebase storage and real-time database for reliable data management
- **Multiple User Support**: Support for simultaneous users monitoring the same system

## System Architecture
The system operates at the 5th level of IoT implementation with:
- **Sensor Layer**: PIR sensor and OV2640 camera module
- **Controller Layer**: ESP32-CAM as the central node
- **Actuator Layer**: Solenoid door lock mechanism
- **Cloud Layer**: Firebase RTDB and Storage
- **Application Layer**: Android mobile application

## Hardware Components
- ESP32-CAM (with OV2640 camera module)
- PIR motion sensor
- Solenoid door lock
- Logic level converter (3.3V to 5V)
- Relay module
- Buck converter
- LED indicators (Red & Green)
- Buzzer for audio alerts
- Push button (manual trigger)
- Power supply components

## Software Components
- **ESP32 Firmware**: Arduino-based firmware for ESP32-CAM
- **Android Application**: Java-based mobile app for monitoring and control
- **Firebase Backend**: Real-time database and storage configuration

## Circuit Design
The system uses dual power supplies:
- One for the solenoid lock (high current)
- One for the ESP32 and sensors (stable 3.3V/5V)

Key connections include:
- Logic level converter between ESP32 (3.3V) and relay (5V)
- Status LEDs for visual system feedback
- Buck converter for stable ESP32 power

## Status Indicators
| BUZZER | GREEN LED | RED LED | CONDITION |
|--------|-----------|---------|-----------|
| - | - | Blink 200ms | System startup |
| - | Blink 1s | - | System ready |
| 2s | - | Blink 2s | Motion detected |
| - | Blink 200ms | - | Uploading photo |
| - | Steady on | - | Door unlocked |

## Cloud Backend
The Firebase backend handles:
- Real-time database for system status
- Storage bucket for captured images
- Authentication and security rules

### Firebase Variables
| VARIABLE | IMPLEMENTATION |
|----------|----------------|
| LOCK | 11: Locked (Solenoid on)<br>10: Unlocked (Solenoid off) |
| PIR | 1: Motion detected<br>0: No motion |
| requestphoto | 1: App requested photo<br>0: No request |

## Mobile Application
The Android app provides:
- Real-time status monitoring
- Door lock/unlock toggle
- Image viewing capability
- Manual photo capture requests
- Push notifications on motion detection

## Installation & Setup
1. **Hardware Assembly**:
   - Follow the circuit diagram to connect all components
   - Ensure proper power supply connections

2. **ESP32 Firmware**:
   - Upload the provided Arduino code to the ESP32-CAM
   - Configure WiFi and Firebase credentials

3. **Firebase Configuration**:
   - Create a new Firebase project
   - Set up Realtime Database with appropriate rules
   - Configure Storage bucket with proper permissions

4. **Android Application**:
   - Import the project into Android Studio
   - Configure Firebase connection (google-services.json)
   - Build and install on your device

## Future Enhancements
- Face recognition for authorized access
- Voice interaction capabilities
- Integration with smart home systems
- Multiple camera support
- Enhanced analytics and logging

## Contributors
- Aniket Kalbhor
- Karan Harshey
- Jannu
- Aastha Jain
- Sujit Kanawade

## Acknowledgments
This project was developed as part of IoT course.
