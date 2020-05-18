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