# PhilipsHueJava

This is a small Java class to be used with the Philips Hue.

The app takes 7 params..

* param 1 = IP Address of HUE Bridge
* param 2 = API Key for HUE Bridge
* param 3 = id of the light (1,2,3,4)... you can also use "All" to do all lights on the bridge
* param 4 = true/false for on/off of the light
* param 5 = 0-255 for the saturation level (255 highest)
* param 6 = 0-255 for the brightness level (255 highest)
* param 7 = 0-65535 for the color
* param 8 = "none, select (1 sec flash) or lselect (30 sec flash)" for the alert setting


Example of compiling and running the app...
```c
Chads-iMac:Philips-Hue-Java cstachowicz$ javac PhilipsHue.java
Chads-iMac:Philips-Hue-Java cstachowicz$ java PhilipsHue 192.168.1.7 ad32e8a371850172530f6f64a8ab33 1 true 20 20 60000 select
{"on": true, "sat": 20, "bri": 20, "hue":60000, "alert":"select"}
Output from Server .... 

[{"success":{"/lights/1/state/on":true}},{"success":{"/lights/1/state/hue":60000}},{"success":{"/lights/1/state/sat":20}},{"success":{"/lights/1/state/bri":20}}]
```
