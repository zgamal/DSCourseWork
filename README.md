# zabdelrahman

### Grade for Laundry Sorter Lab
Criterion | Points
--- | ---
Generates a new color swatch only if previous one placed correctly | 2/2
Swatch displayed in/returns to the correct initial position | 2/2
Updates num correct and incorrect appropriately | 2/2
Drags swatch properly | 2/2
Appropriate behavior if user does unexpected things | 1/2
EC: Does not update counts if user misses all baskets | +1

Notes: Your program does not register drops as correct if the mouse is outside of the basket, 
even if the majority of a clothing item is in the correct basket. This is because you're
using the contains method instead of the overlaps one.  Stylistically, avoid using instance
variables when local ones will suffice.  You don't need instance variables for red, green, 
and blue.  That info already lives in the Color.  

#### Total: 10/10

### Grade for Magnets Lab
Criterion | Points
--- | ---
Drawing magnets correctly at startup | 1/1
Dragging a magnet | 1/1
Ability to move either magnet | 1/1
Moving a magnet to the right place when attracted | 1/1
On attraction, moving the magnet not pointed to | 1/1
Flipping a magnet | 1/1
Attracting and repelling at the right times | 1/1
No other problems | 1/1
Style | 0.5/1

Note:  The last 3 lines of your moveTo method are identical to your move one.  Better to
call your existing method.  Your contains method contains an unnecessary if statement,
just return the result of the the boolean directly.  In onMouseClick, you've written
two if statements as if they are an if/else, but you've forgotten the else.  

#### Total: 8.5/9

### Grade for Frogger Lab
Criterion | Points
--- | ---
Frog begins in correct initial position | 1/1
Frog jumps in response to clicks | 1/1
Frog jumps correct distance | 1/1
Frog dies when run over | 1/1
Frog says "Ouch" when killed | 1/1
Frog reincarnates on click | 1/1
Lane repeatedly generates vehicles | 1/1
Vehicles move smoothly | 1/1
Vehicles are appropriately spaced | 1/1
Vehicles get removed from canvas | 1/1
EC 1:  Lanes move in opposite directions | 1/1
EC 2:  Different cars in a single lane | 1/1
#### Total: 12/10