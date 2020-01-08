# FRC 2020

Team 7721's FRC Code for infinite recharge.

![Infinite Recharge](https://i.imgur.com/sd8N6p1.jpg)

# Reade this
When in doubt, **Actually** read the guides and this readme.

* [Raspberry Pi stuffs](#setting-up-the-raspberry-pi)
* [chameleon Vision stuffs](#setting-up-chameleon-vision)
* [Pixy Cam stuffs](#setting-up-pixy-cam)

⠀

* [I have nothing to do](https://trello.com/b/l4WCfzfA/uhs-robotics-2019-2020)

⠀

* [I havn't done anything at all, How do I get started?](#the-basics)
* [I want to use shuffle board/smart dashboard, where do I get it?](#tools)

---

## Getting Started

### The Basics

1. Setting up your environment
   * Install WPILib: [Installation Guide](https://docs.wpilib.org/en/latest/docs/getting-started/getting-started-frc-control-system/wpilib-setup.html#wpilib-installation-guide)
     * Needed for programming, building prorgams and settings up projects
     * Includes JDK, VSCode and a bunch of stuffs

Or

1. Setting up your environment
   1. Install JDK: [Download Link](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
      * Needed for java programming
      * Remember to "Accept License Agreement"
   2. Install VSCode: [Download Link](https://code.visualstudio.com/)
      * Needed for programming
   3. Install the WPILib VSCode extension: [Download Link](https://marketplace.visualstudio.com/items?itemName=wpilibsuite.vscode-wpilib)
      * Needed for FRC Robot Programming
2. Install FRC Game Tools: [Download Link](https://www.ni.com/en-ca/support/downloads/drivers/download.frc-game-tools.html#333285).
   * Needed for connecting to the robot
   * Watch out that the installer will disable windows fast start by default.
     * I havn't noticed any issue having windows fast start kept on.
   * When rompted to enter a NI account or serial code, simple cancel/close/skip
3. Install CTRE Phoenix Framework: [Installation Guide](https://phoenix-documentation.readthedocs.io/en/latest/ch05_PrepWorkstation.html#option-1-windows-installer-strongly-recommended)
   * Needed for using motor controllers in your program
   * The name is something like "CTRE Phoenix Framework Installer 5.17.3.1 (.zip)"
4. Adding the CTRE Phoenix Library to your **new** project: [Guide](https://phoenix-documentation.readthedocs.io/en/latest/ch05a_CppJava.html#frc-c-java-add-phoenix)
   * If you are not creating a new project, and is cloning this one instead, there's no need to do this as it's already done.

### Tools

Getting tools like pathweaver, smartdashboard, shuffleboard, etc.

1. Press f1 in vscode
   * If you are on a laptop and F1 does some other random stuff, try holding down the "FN" key while pressing F1
2. Search for `WPILIB: Start Tools` by typing this in the bar that just appeared.
3. You'll probably get prompted that you don't have any, proceed with the option that downloads you them.
4. Wait......
5. After installation, try to run `WPILIB: Start Tools` again and start any random tool, if prompted "A Java Exception has Occured"
   1. install JDK 13[\(The Basics, 1,i)](#the-basics)
   2. Search for and open "advanced system settings" on your laptop
   3. Open "Enviromental Variables" in "advanced system settings"
   4. Find/Create a variable called `JAVA_PATH` in "System Variables", setting the value to the path to your jdk 13
      * Looks something like this: `C:\Program Files\Java\jdk-13.0.1`

---

## Vision

### Setting up the raspberry pi

**[Guide for Installing Raspbian Buster Lite on your raspberry pi](https://www.raspberrypi.org/documentation/installation/installing-images/README.md)**

After installation, put an empty file with no file extension named `ssh` into the root of the `boot` to enable ssh for headless setup.

Power on the raspberry pi, then connect it to your computer through an ethernet cable.

ssh into the raspberrypi by running the command `ssh pi@raspberrypi`, then `yes`, then enter the default password `raspberry`

**[Guide to Settings WiFi up via the command line](https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md)**

For school Wifi, open the `wpa-supplicant` configuration file in nano (a code editor) by running:

`sudo nano /etc/wpa_supplicant/wpa_supplicant.conf`

and put this in there, replacing the [student#] and [student password] with actual student number and password:

``` js
network={
        ssid="YRDSB-S"
        scan_ssid=1
        key_mgmt=WPA-EAP
        eap=PEAP
        identity="[student#]"
        password="[student password]"
        phase1="peaplabel=1"
}
```

### Setting up chameleon vision

**[Official guide for setting up chameleon vision](https://chameleon-vision.readthedocs.io/en/latest/installation/coprocessor-setup.html)**

**The raspberry pi must be connected to the internet through ethernet during install/update**

Run these commands on the raspberry pi, not your own computer, to do stuffs:

To install:

```
wget https://git.io/JeDUk -O install.sh
chmod +x install.sh
sudo ./install.sh
```

To run:

```
sudo java -jar chameleon-vision.jar
```

To update:

```
wget https://git.io/JeDUL -O update.sh
chmod +x update.sh
sudo ./update.sh
```

**[Guide to make stuffs (in this case, chameleon vision) run on boot](https://www.dexterindustries.com/howto/run-a-program-on-your-raspberry-pi-at-startup/)**

To connect to the web dashboard of chameleon vision, put `http://raspberrypi:5800/` in your browser.
Make sure your computer and the raspberrypi are under the same network.

**School wifi blocks a lot of ports and won't work, connect the raspberry pi directly to your laptop through ethernet or the roborio wifi to test chameleon vision**

### Setting up pixy cam

#### [Guide for setting up pixy cam](https://docs.pixycam.com/wiki/doku.php?id=wiki:v2:hooking_up_pixy_to_a_raspberry_pi)
