# Algorithmic puzzles

Puzzles from the book *Algorithmic Puzzles* by Anani and Maria Levitin (2011).

## 1. A Wolf, a Goat, and a Cabbage
Solution can be found by generic breadth first search, where the initial state 
is that the boat, wolf, goat and cabbage are on the west bank. The stop-state is
all items are at the east bank. Possible next state is a list of states where the 
boat to the other side, optionally taking one of the items from the side where 
the boat currently. You need to filter out states that leave an invalid states on 
the opposite side (where the boat is not).

## 2. Glove selection
20 gloves (10 pairs black, 3 pairs brown, 2 pairs gray).
1) how many gloves to pick to guarantee 1 pair with matching colour?
2) How many gloves to pick to guarantee 3 matching pairs of all colours?

To get one matching pair you need to make 11 picks, cause worst case the first 10 
you pick are all left or right gloves, so no pair. The eleventh one will make a pair 
for sure.

To get three matching pairs, you need to pick 19 gloves. Again the first 10
could be all left-hand gloves. Then worst case you pick five black right-hand 
gloves, then three brown ones and finally a gray one at which point you for sure
have three matching pairs of different colour.

## 3. Rectangle Dissection
Of course, you can dissect a rectangle into an infinite number of rectangles.

Starting with a square:
Step 1. You can create two right triangles from any rectangle by cutting it into 2 equal 
parts diagonally.
Step 2. You can split any right triangle into two right triangles by
splitting it at the highest point (with the hypotenuse facing down)

In addition, you can first cut the rectangle into multiple rectangles along any side, 
after which each rectangle can be cut into two right triangles (step 1).

## 4. Ferrying soldiers
The boat can carry two boys, or one soldier, or ... one boy (of course). To get
one soldier across, first one of the boys is moved to the opposite side, which takes
2 crossings (2 boys on the first crossing, onw boy back). Now, one soldier can take 
the boat and cross. Finally, the boy across brings the boat back to the first boy, and
the sequence can start over again. So, it takes 4 crossing to transfer one soldier,
which makes 100 crossings to transfer 25 soldiers, leaving the  boat at one  side 
with the two boys.

## 5. Row and Column exchanges
Whenever you switch a row or a column, the number in the row, or the numbers in the 
column stay the same (so the groups stay together). Only the order will change. When table
two is a transformation of table 1, then the combination of numbers in a row of table two, 
match  the combination of numbers of one row in table 1. This also applies to the columns.

## 6. Predicting a Finger Count
At which finger will you end if you count Thumb, first finger, middle finger, ring finger, 
little finger, ring finger, etc?

One tour from thumb until (but not including) thumb is 1 through 8. 
* Thumb is 1
* First finger is 2 and 8
* Middle finger is 3 and 7
* Ring finger is 4 and 6
* Little finger is 5

Knowing this, you will end at finger (count - 1 mod 8), and when counting to 1000, you will end at the 
first finger. 

## 7. Bridge crossing at night
Four people need to cross a rickety footbridge... Can they cross the bridge in 17 minutes?

This one can be solved with a BSF approach when using a ```PriorityQueue<State>``` instead of a normal
```Queue<State>```. The State contains a set with persons on the left, a set with Persons on the right, the total time
used so far, and the position of the flashlight. The priority queue needs to be ordered by the total time used to cross,
which will ensure the shortest solution will be found first.


