# CustomItems

## plugin.yml
Simple plugin stuff not much to talk about.

## Main.java
Inits the plugin, nothing interesting.

## MyListener.java
Our spigot listener. 

Currently there are two types of events, single item events and all item events.

In mono item events, only the function lambda on the item that is being used is called.
For instance, if I right-clicked while holding a sword (`onPlayerInteractEvent`), only the `onPlayerInteractEvent` lambda for the sword would be called.

In poly item events, multiple item lambdas are called.
For instance, if I died (`onEntityDeathEvent`), an `onEntityDeathEvent` lambda would be called for each item in my inventory.

## CustomItems.java
The core of the plugin.

Important parts:
- CustomItems basic constructor
- CustomItems copy constructor
- Lambda variables
- setPersistentData(ItemStack i)

Everything seems pretty self explanatory, but I'll add a brief overview. Create a new CustomItems, set its lambdas, and the use `setPersistentData` to add this behavior to an ItemStack. 

What it does is add a integer key to the persistentData of the itemStack. When the listener for the itemStack is called, we get the key attached and then use it as the index to a static array in order to get the associated CustomItems. We then use that CustomItems to get the required lambda.

## Issues/Future plans
- Somehow deal with multiple items either cancelling or uncancelling events.
- Add more events
- Eventually remove distinction between mono and poly item events
