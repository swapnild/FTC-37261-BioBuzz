# I Brake for Kevin — FTC Team 37261 🦚🤖

Robot control software for **FIRST Tech Challenge (FTC) Team 37261 ("I Brake for Kevin")** out of Oakland, CA, competing in the **2026–2027 BIOBUZZ** season.

## Overview
This repository houses our on-robot Java OpModes, hardware subsystems, vision pipelines, and autonomous routines running on the REV Control Hub.

## Hardware & Tech Stack
* **Control System:** REV Control Hub & REV Driver Hub
* **Drivetrain & Actuators:** goBILDA Mecanum chassis & Yellow Jacket motors
* **Vision:** USB Webcam with AprilTag detection
* **Language & IDE:** Java (FTC SDK / Android Studio)

## Repository Structure
```text
FTC-37261-BioBuzz/
├── FtcRobotController/      # Base FTC Robot Controller app
└── TeamCode/
    └── src/main/java/org/firstinspires/ftc/teamcode/
        ├── opmodes/         # [TBD] TeleOp and Autonomous OpModes
        ├── subsystems/      # [TBD]Hardware abstractions (drivetrain, mechanisms, etc.)
        └── util/            # [TBD]PIDF controllers, vision helpers, and constants
```

## Getting Started
1. **Clone the repository:**
   ```bash
   git clone https://github.com/swapnild/FTC-37261-BioBuzz.git
   ```
2. **Open in Android Studio:**
   Open the cloned `FTC-37261-BioBuzz` folder in **Android Studio** and allow Gradle to finish syncing all FTC SDK dependencies.
3. **Deploy to Robot:**
   Connect your computer to the REV Control Hub (via USB-C or Wi-Fi Direct) and click **Run** (`Shift + F10`) to install the Robot Controller app.

## Team Git Workflow
* Always pull the latest changes from `master` before starting work (`git pull origin master`).
* Create a dedicated branch for new features or tuning (`git checkout -b feature/your-feature`).
* Verify all changes on the physical robot before opening a Pull Request into `master`.

## Resources
* [FTC SDK Documentation](https://ftc-docs.firstinspires.org/)
* [2026–2027 BIOBUZZ Game Manual & Resources](https://www.firstinspires.org/robotics/ftc/game-and-season)
