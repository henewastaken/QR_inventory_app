# QR_inventory_app

Android inventory application.
Idea id to create database where user can store data items by using qr codes
and getting information about the item and how much of item is left.

Example use case:
User prints QR code for bag of flower, adds it to the app and a warning limit, 
when need to buy more.
When user uses flower, qr is scanned and amount taken is imputted.
When user goes to store user can with single button press get all items that are
under the warning limit so user knows to buy more of those items.
Also comments can be inserted to each item, i.e. coockig time for rice..

TODO:
- Database item delete
- For manually imputted (not by scanning code) items barcode can be given later.
- functionality for getting items that has amount < minAmount. DONE
- fucntionality for searching from database via qr reader. DONE
- make it look pretty
- Toast if amount goes under minimum (alarm) amount.


INSTRUCTIONS FOR USE:

Idea is that user can use QR- or barcodes for inventory. For example barcodes in packaging or 
by creating codes with generator (can be found online). App does not provide generating functionality.
App keeps track when items are running out and will give list of these items. 
Back arrow just pops the stack, so not most usefull for navigation. Upeer right corner navigation is recommended.

In scan view user can scan QR- or barcodes by pressing the camera button on right lower corner.
It opens scanning view. If code has been scanned and is in database user can update it's values
if code is new user can insert new item to database.
Fields name, amount and minimun amounts are required, optional data is optional.

Navigation happends via menu on right upper corner. List view gives user all items which amount
is lower than minimum amount. Usefull for example when in supermarket. By clicking item 
user can update it's values. Also items can be added manually with the + button, but this 
will remove barcode functionality for this item (for now. Will be patched later).

