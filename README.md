# StudyCase-PercentageStore


On a retail website, the following discounts apply:
1.	If the user has gold card of the store, he gets a 30% discount
2.	If the user has silver card of the store, he gets a 20% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount. 
4. For every $200 on the bill, there would be a $ 5 discount (e.g. for $ 950, you get $ 20 as a discount).
5. The percentage based discounts do not apply on phones.
6. A user can get only one of the percentage based discounts on a bill.
Write a program in java such that given a bill, it finds the net payable amount. 
User interface is not required. 

## **Solutions**
The discount rate defined for the user card was taken, a total value was found by making transactions for each product in the cart. Then, as a result of the calculation at the end of the invoice, a $5 discount was applied for each $200 on the invoice.

If there is a phone in the cart, first the percentage of the other products in the cart is calculated according to the credit card the user has. The result is added to the amount of the phone. 5$ is deducted for every 200$ amount. And the amount without discount, the final amount, and the discount amount are written on the invoice.
In addition, there is only a single percentage discount on the invoice.

!! The user's card type does not give a discount for the phone. 
------------------------------------------------------------------

## **UML DIAGRAMS**
------------------------------------------------------------------
![nn](https://user-images.githubusercontent.com/79936070/144602198-2af4482a-f98d-4b62-b4b9-2844d8b8480f.PNG)

## **Users**
------------------------------------------------------------------
![Ekran Alıntısı](https://user-images.githubusercontent.com/79936070/144601929-0431faf8-3a4c-460e-af3e-179b98484887.PNG)

## **Products**
------------------------------------------------------------------
![1](https://user-images.githubusercontent.com/79936070/144601414-fff71682-17a9-4d88-a442-3d6c25bd1acd.PNG)

## **Invoices**
------------------------------------------------------------------
![2](https://user-images.githubusercontent.com/79936070/144602105-bb4299f9-39d1-402a-ab88-3150eb362a77.PNG)


