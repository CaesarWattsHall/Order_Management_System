/*
*By: Caesar R. Watts-Hall
*Class: JAVA 2
*Instructor: Dr.Primo
*Assignment: ATM Machine
*Date: 02/07/2018
*Due: 02/07/2018 @11:55PM
 */
//START
package lab2;

import java.util.*;

public class Lab2 { // Start of main class

    public static void main(String[] args) { //start of main entry point 

        //Here are the variables I used.
        String[] transMenu = {"[T]\tTransactions", "[L]\tStock List", "[X]\tExit"};
        String[][] arrMenuItem = {{"[P]\tPurchase Order", "[S]\tSales Order", "[I]\tInventory", "[M]\tMain Menu"}, {"Customers", "Items", "Vendors"},
        {"Discounts", "About", "Exit"}};

        //menus variables
        char crwhSelectMenu;
        char crwhSelectTrans;
        char crwhCheckOutMenu;
        char crwhSoCheckOut;

        //Purchases (Order variables)
        int PONo = 0;
        int POItemNo = 1;
        String[][] POItemCode = new String[50][50];
        String[][] POItemName = new String[50][50];

        int[][] POQty = new int[50][50];

        float[][] POItemPrice = new float[50][50];
        float[][] POTotalAmt = new float[50][50];
        float totalAmount = 0;

        float[][] stockItemQty = new float[50][50];
        String POItemCodeHolder;
        //String POItemName;
        int POQtyHolder = 0;
        float POItemPriceHolder = 0;
        float POTotalAmtHolder = 0;
        float stockItemQtyHolder = 0;

        //(Inventory variables)
        int invNo = 1;
        char invCheckOut;
        String[] invItemCode = new String[50];
        String invItemCodeHolder;
        String[] invItemName = new String[50];
        String invItemNameHolder;
        int[] invItemPcs = new int[50];
        int invItemPcsHolder = 0;
        String[] invItemUnit = new String[50];
        String invItemUnitHolder;
        float[] invItemMarkUp = new float[50];
        float invItemMarkUpHolder;
        int m = 0;

        //(salesOrder variables)
        int SONo = 0;
        int SOItemNo = 1;
        String[][] SOItemCode = new String[50][50];
        String[][] SOItemName = new String[50][50];
        int[][] SOQty = new int[50][50];
        float[][] SOItemPrice = new float[50][50];
        float[][] SOTotalAmt = new float[50][50];
        int s = 0;
        int d = 0;
        float soAmtDue = 0;
        float soTotalAmtDue = 0;
        float SOTendAmt = 0;

        String except;
        Scanner scan = new Scanner(System.in);

        //Here is the desired output...
        System.out.println("\tWelcome to Order Management System");
        do {
            //start Main menu
            System.out.print("\n");
            System.out.println("\t--------Main Menu--------");
            System.out.print("\n");
            for (String transMenu1 : transMenu) {
                System.out.println("\t" + transMenu1);
            }
            System.out.print("\tEnter choice: ");
            crwhSelectMenu = scan.next().charAt(0);
            System.out.print("\n");
            // select transaction
            switch (crwhSelectMenu) {
                case 'T': //transaction
                case 't':
                    do {
                        System.out.print("\n");
                        System.out.println("\t-----Transaction Menu-----");
                        System.out.print("\n");
                        for (int i = 0; i < transMenu.length; i++) //iterate the transaction menus
                        {
                            System.out.println("\t" + arrMenuItem[0][i]);

                        }
                        System.out.print("\tEnter choice: ");
                        crwhSelectTrans = scan.next().charAt(0);

                        switch (crwhSelectTrans) //do selected transaction
                        {
                            case 'P': //purchase order
                            case 'p':
                                PONo = PONo + 1;
                                System.out.print("\n");
                                System.out.println("\t-----New Purchase Order-----");
                                System.out.print("\n");
                                System.out.println("\tPO No : " + PONo);
                                crwhCheckOutMenu = 'p';
                                String itemName = "";
                                Boolean itemFound = false;
                                do {
                                    System.out.print("\tEnter Item Code : ");
                                    POItemCodeHolder = scan.next();
                                    if (POItemCodeHolder.charAt(0) != 'C' && POItemCodeHolder.charAt(0) != 'c') {
                                        m = lookUpItem(invItemCode, POItemCodeHolder);
                                        if (m != 0) {
                                            //catch the error
                                            Boolean poNoError = true;

                                            //try to get the user input and catch the system error if there is any
                                            try {
                                                System.out.print("\tEnter Quantity : ");
                                                POQtyHolder = scan.nextInt(); //get input and put it in placeholder
                                                System.out.print("\tEnter Price : ");
                                                POItemPriceHolder = scan.nextFloat(); //get input and put it in placeholder
                                            } catch (Exception ex) {
                                                poNoError = false;
                                                System.out.println("\n");
                                                System.out.println("\t***Error! Enter number only in this field!***");
                                                break;
                                            }
                                            //put value in array if no error
                                            if (poNoError) {
                                                POItemCode[PONo][POItemNo] = POItemCodeHolder;
                                                POItemName[PONo][POItemNo] = invItemName[m];
                                                POQty[PONo][POItemNo] = POQtyHolder;
                                                POItemPrice[PONo][POItemNo] = POItemPriceHolder;
                                                POTotalAmt[PONo][POItemNo] = POQtyHolder * POItemPriceHolder;
                                            }

                                        } else {

                                            System.out.print("\tItem not found ");
                                        }
                                    } else {
                                        crwhCheckOutMenu = POItemCodeHolder.charAt(0); //convert string to char
                                    }

                                    POItemNo = POItemNo + 1;

                                    //display list
                                    System.out.print("\n");
                                    System.out.print("\tItem \t");
                                    System.out.print("Name\t");
                                    System.out.print("Qty\t");
                                    System.out.print("Price\t");
                                    System.out.print("Total Amount\t");
                                    System.out.print("\n");

                                    for (int h = 0; h < POItemCode.length; h++) {

                                        if (POItemCode[PONo][h] != null) {
                                            System.out.print("\n");
                                            System.out.print("\t" + POItemCode[PONo][h] + "\t");
                                            System.out.print(POItemName[PONo][h] + "\t");
                                            System.out.print(POQty[PONo][h] + "\t");
                                            System.out.print(POItemPrice[PONo][h] + "\t");
                                            System.out.print(POTotalAmt[PONo][h] + "\t");
                                            totalAmount = totalAmount + POTotalAmt[PONo][h];
                                        }

                                    }
                                    System.out.print("\n");
                                    System.out.print("\tGrand Total : " + totalAmount);
                                    totalAmount = 0;
                                    System.out.print("\n");
                                    System.out.print("\n");

                                } while (crwhCheckOutMenu != 'C' && crwhCheckOutMenu != 'c');

                                break;
                            case 'S':
                            case 's':

                                System.out.print("\n");
                                SONo = SONo + 1;
                                System.out.println("\t-----New Sales Order-----");
                                System.out.print("\n");
                                System.out.println("\tSO No : " + SONo);
                                crwhSoCheckOut = 'p';

                                do {
                                    System.out.print("\tEnter Item Code : ");
                                    SOItemCode[SONo][SOItemNo] = scan.next();
                                    //initialize if Item has stock
                                    int soldItems = totalPOQty(SOItemCode[SONo][SOItemNo], SOItemCode, SOQty);
                                    int purchasedItems = totalPOQty(SOItemCode[SONo][SOItemNo], POItemCode, POQty);
                                    int onHand = (purchasedItems - soldItems);

                                    if (SOItemCode[SONo][SOItemNo].charAt(0) != 'C' && SOItemCode[SONo][SOItemNo].charAt(0) != 'c') {
                                        s = lookUpItem(invItemCode, SOItemCode[SONo][SOItemNo]);
                                        d = lookUpItem(POItemCode[PONo], SOItemCode[SONo][SOItemNo]);

                                        if (s != 0) {
                                            SOItemName[SONo][SOItemNo] = invItemName[s];

                                            do {
                                                except = null;
                                                try {
                                                    System.out.print("\tEnter Quantity : ");
                                                    SOQty[SONo][SOItemNo] = scan.nextInt();
                                                } catch (Exception ex) {
                                                    except = "error";
                                                    System.out.println("Error: Enter number only");

                                                }

                                            } while (except != null);

                                            SOItemPrice[SONo][SOItemNo]
                                                    = ((invItemMarkUp[s] / 100) * (POItemPrice[PONo][d]))
                                                    + (POItemPrice[PONo][d]);
                                            SOTotalAmt[SONo][SOItemNo] = SOQty[SONo][SOItemNo] * SOItemPrice[SONo][SOItemNo];

                                        } else {

                                            System.out.print("\tItem not found ");
                                            SOItemCode[SONo][SOItemNo] = null;
                                        }
                                    } else {
                                        crwhSoCheckOut = SOItemCode[SONo][SOItemNo].charAt(0); //convert string to char
                                        SOItemCode[SONo][SOItemNo] = null;
                                    }

                                    SOItemNo = SOItemNo + 1;

                                    System.out.print("\n");
                                    System.out.print("\tItem \t");
                                    System.out.print("Name\t");
                                    System.out.print("Qty\t");
                                    System.out.print("Price\t");
                                    System.out.print("Total Amount\t");
                                    System.out.print("\n");
                                    for (int r = 1; r < SOItemCode[SONo].length; r++) {
                                        if (SOItemCode[SONo][r] != null) {
                                            System.out.print("\n");
                                            System.out.print("\t" + SOItemCode[SONo][r] + "\t");
                                            System.out.print(SOItemName[SONo][r] + "\t");
                                            System.out.print(SOQty[SONo][r] + "\t");
                                            System.out.print(SOItemPrice[SONo][r] + "\t");
                                            System.out.print(SOTotalAmt[SONo][r] + "\t");
                                            soAmtDue = soAmtDue + SOTotalAmt[SONo][r];

                                        }
                                    }
                                    System.out.print("\n");
                                    System.out.print("\n");
                                    System.out.print("\tGrand Total : " + soAmtDue);
                                    soTotalAmtDue = soAmtDue;
                                    soAmtDue = 0;
                                    System.out.print("\n");
                                    System.out.print("\n");
                                } while (crwhSoCheckOut != 'C' && crwhSoCheckOut != 'c');

                                //payment module
                                System.out.print("\tTendered Amount : ");

                                try {
                                    SOTendAmt = scan.nextFloat();

                                } catch (Exception ex) {
                                    System.out.println("\tError: " + ex.getMessage());
                                }
                                System.out.print("\t================================\n");

                                float vat = ((12 * soTotalAmtDue) / 100);
                                float amtlessvat = (soTotalAmtDue - vat);
                                float change = (SOTendAmt - soTotalAmtDue);
                                System.out.println("\t12% VAT : " + vat);
                                System.out.println("\tAmount Due : " + amtlessvat);
                                System.out.println("\tNet Amount : " + soTotalAmtDue);
                                System.out.println("\tChange: " + change);
                                System.out.print("\n");
                                System.out.print("\n");

                                break;

                            case 'I': // inventory
                            case 'i':
                                System.out.print("\n");
                                System.out.println("\t-----New Inventory Stock-----");
                                System.out.print("\n");
                                invCheckOut = 'i';

                                do { //repeat until payment

                                    System.out.print("\tEnter Item Code([c] Checkout): ");
                                    invItemCodeHolder = scan.next();
                                    if (invItemCodeHolder.equalsIgnoreCase("")) {
                                        break;
                                    }
                                    if (invItemCodeHolder.charAt(0) != 'C' && invItemCodeHolder.charAt(0) != 'c') {

                                        //catch the error
                                        Boolean noError = true;

                                        try {
                                            System.out.print("\tEnter Item Name: ");
                                            invItemNameHolder = scan.next(); //get input and put it in placeholder
                                            System.out.print("\tEnter PCS: ");
                                            invItemPcsHolder = scan.nextInt(); //get input and put it in placeholder
                                            System.out.print("\tEnter Unit: ");
                                            invItemUnitHolder = scan.next(); //get input and put it in placeholder
                                            System.out.print("\tEnter Markup: ");
                                            invItemMarkUpHolder = scan.nextFloat(); //get input and put it in placeholder
                                        } catch (Exception ex) {
                                            noError = false; // false if there is an error
                                            System.out.println("\n");
                                            System.out.println("\t***Error! Enter number only in this field!***");
                                            break;
                                        }

                                        //put value in array if no error
                                        if (noError) {
                                            invItemCode[invNo] = invItemCodeHolder;
                                            invItemName[invNo] = invItemNameHolder;
                                            invItemPcs[invNo] = invItemPcsHolder;
                                            invItemUnit[invNo] = invItemUnitHolder;
                                            invItemMarkUp[invNo] = invItemMarkUpHolder;
                                        }

                                    } else {

                                        invCheckOut = invItemCodeHolder.charAt(0); //convert string to char
                                        invItemCodeHolder = null;
                                    }

                                    invNo = invNo + 1; //counter

                                } while (invCheckOut != 'C' && invCheckOut != 'c');
                            default:

                                break;
                        }

                    } while (crwhSelectTrans != 'M' && crwhSelectTrans != 'm');

                    System.out.println("\tBack to Main Menu");

                    break;

                case 'L': //transaction stocks menu
                case 'l':

                    Boolean notFound = true;
                    System.out.print("\n");
                    System.out.println("\t\tStocks Inventory \t");
                    System.out.println("\t==========================\n");
                    System.out.print("\tItem \t");
                    System.out.print("Name\t");

                    System.out.print("TotalQty\t");
                    System.out.print("QtySold\t");
                    System.out.print("QtyOnHand\t");
                    System.out.print("\n");
                    for (int z = 0; z < invItemCode.length; z++) {
                        if (invItemCode[z] != null) {
                            notFound = false;
                            System.out.print("\t" + invItemCode[z]);
                            System.out.print("\t" + invItemName[z]);
                            System.out.print("\t" + totalPOQty(invItemCode[z], POItemCode, POQty));
                            System.out.print("\t" + totalPOQty(invItemCode[z], SOItemCode, SOQty));
                            System.out.print("\t" + (totalPOQty(invItemCode[z], POItemCode, POQty)
                                    - totalPOQty(invItemCode[z], SOItemCode, SOQty)));
                            System.out.print("\n");
                        }

                    }
                    if (notFound) {
                        System.out.println("\t Item not found");
                    }
                    break;

                case 'S': //Settings 
                case 's':

                    break;

                default: //default case

                    break;
            }

        } while (crwhSelectMenu != 'x' && crwhSelectMenu != 'X');

        System.out.println("System Closing...");
    }

    //search for itemcode and get the item quantity, then get the total quantity
    public static int lookUpItem(String[] arr, String targetValue) {

        int k = 0;

        for (int f = 0; f < arr.length; f++) {
            if (arr[f] != null) {
                if (arr[f].equalsIgnoreCase(targetValue)) {
                    k = f;
                }
            }

        }

        return k;
    }
    // get total quantity of purchase order and sales order to get the quantity on hand

    public static int totalPOQty(String itemCode, String[][] arrPOitem, int[][] arrqty) {

        int totalQty = 0;
        int qty = 0;

        for (int k = 1; k < arrPOitem.length; k++) {

            for (int l = 0; l < arrPOitem[k].length; l++) {
                if (arrPOitem[k][l] != null) {

                    if (itemCode.equalsIgnoreCase(arrPOitem[k][l])) {

                        qty = arrqty[k][l];
                        totalQty = totalQty + qty;
                    }
                }
            }
        }
        return totalQty;

    }
}
