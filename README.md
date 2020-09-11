# Point of sales terminal

## Description 

The Terminal is used by cashiers to enter sales orders and check order total. 
The application consists of 2 widgets:
 - Sales orders entry widget.
    Within - Cashier puts “product name”, “price”, “qty”. It shows all entered lines and calculates totals.
 - Credit check widget.
    The widget is used to check the maximum order amount. Cashier puts the maximum amount (credit limit). If the currently entered order total less than the credit limit, the “indicator box” is green, otherwise - the “indicator box” will be red.
## Final product

#### List of products (over the limit) Sales order #1.
!["Screenshot of list of products (over the limit) Sales order #1 page."](https://github.com/VasylTykhomyrov/cashiers-terminal/blob/master/src/main/docs/List%20of%20products%20(over%20the%20limit)%20Sales%20order%201.PNG?raw=true)

#### List of products (below the limit) Sales order #2.
!["Screenshot of list of products (below the limit) Sales order #2 page."](https://github.com/VasylTykhomyrov/cashiers-terminal/blob/master/src/main/docs/List%20of%20products%20(below%20the%20limit)%20Sales%20order%202.PNG?raw=true)

## Tech Stack
- Java SE
- Java FX
- MySQL