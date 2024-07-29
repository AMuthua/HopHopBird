# FlappyBird Game

## Overview
This project is a simple implementation of the Flappy Bird game using Java Swing for the GUI and basic game mechanics.

## Project Structure
- **Images**: The game includes background, bird, top pipe, and bottom pipe images.
- **Bird**: The bird has a starting position, dimensions, and an image.
- **Pipes**: Pipes are defined with a starting position, dimensions, and images for both the top and bottom pipes.

## Game Logic
- **Bird Movement**: The bird moves with an initial velocity and is affected by gravity.
- **Pipe Movement**: Pipes move to the left to simulate the bird moving forward.

## Timers
- **Game Loop**: A timer that runs the game loop, refreshing the game state and rendering it at 60 frames per second.
- **Pipe Placement**: A timer that places new pipes every 1.5 seconds.

## Key Features
- **Gravity and Velocity**: Gravity affects the bird's fall, and velocity changes when the spacebar is pressed.
- **Collision Detection (to be implemented)**: Currently, the pipes move left, and the bird falls due to gravity, but collision detection logic should be added.

## Code Breakdown

### Bird Class
Represents the bird with its initial position, dimensions, and image.

```java
class Bird {
    int x = birdX;
    int y = birdY;
    int width = birdWidth;
    int height = birdHeight;
    Image img;

    Bird(Image img) {
        this.img = img;
    }
}
 