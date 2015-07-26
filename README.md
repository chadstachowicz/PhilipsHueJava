# PhilipsHueJava

This is a small Java class to be used with the Philips Hue.

The app takes 6 params..

* param 1 = IP Address of HUE Bridge
* param 2 = API Key for HUE Bridge
* param 3 = true/false for on/off of the light
* param 4 = 0-255 for the saturation level (255 highest)
* param 5 = 0-255 for the brightness level (255 highest)
* param 6 = 0-65535 for the color


Example of compiling and running the app...
```c
Chads-iMac:Philips-Hue-Java cstachowicz$ javac ChangeLightColorHue.java
Chads-iMac:Philips-Hue-Java cstachowicz$ java ChangeLightColorHue 192.168.1.7 ad32e8a371850172530f6f64a8ab33 true 20 20 60000
{"on": true, "sat": 20, "bri": 20, "hue":60000}
Output from Server .... 

[{"success":{"/lights/1/state/on":true}},{"success":{"/lights/1/state/hue":60000}},{"success":{"/lights/1/state/sat":20}},{"success":{"/lights/1/state/bri":20}}]
```
