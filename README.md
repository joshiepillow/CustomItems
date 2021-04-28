# CustomItems

## plugin.yml
Simple plugin stuff not much to talk about.

## Main.java
Inits the plugin, nothing interesting.

## MyListener.java
Spigot listener which does all the heavy lifting. 

Currently there are two types of events, single item events and all item events.

In single item events, only the function lambda on the item that is being used is called.
For instance, if I right-clicked while holding a sword (`onPlayerInteractEvent`), only the `onPlayerInteractEvent` lambda for the sword would be called.

In poly item events, multiple item lambdas are called.
For instance, if I died (`onEntityDeathEvent`), an `onEntityDeathEvent` lambda would be called for each item in my inventory.

## CustomItems.java
The core of the plugin.

Important parts:
- CustomItems basic constructor
- CustomItems copy constructor
- Assigned Functions

Everything seems pretty self explanatory.
