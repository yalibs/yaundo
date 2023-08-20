# yaundo
Yet another undo system implementation for java applications.
This can be very useful when creating e.g. an editor using JavaFX.

This library provides one default implementation of the `IUndoSystem` interface, the `StackUndoSystem` which provides the "normal" behavior that most undo systems have.

## Usage
First, you need to add the library to your project:

```gradle
// Add mavenCentral to your repositories
repositories {
    mavenCentral();
}

// Add the library (NOTE: Check the latest version on github)
depdendencies {
    implementation group: 'dk.yalibs', name: 'yaundo', version: '1.0.0';
}
```

Then use it in your application:

```java
var element = new ArrayList<Integer>();
IUndoSystem undosystem = new StackUndoSystem();
// Do the action
element.add(1);
// Add the undoable action
undosystem.push(new Undoable("add 1", () -> element.remove(0), () -> element.add(1)));
// Undo the action
undosystem.undo();
// Redo the action
undosystem.redo();
```

