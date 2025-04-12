package Day5.PracticeProblems;
class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryManagement {
    private Item head;

    public void addItemAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addItemAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    public void addItemAtPosition(int position, String itemName, int itemId, int quantity, double price) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (position == 1 || head == null) {
            newItem.next = head;
            head = newItem;
            return;
        }
        Item temp = head;
        int count = 1;
        while (count < position - 1 && temp.next != null) {
            temp = temp.next;
            count++;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeItemById(int itemId) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        if (head.itemId == itemId) {
            head = head.next;
            System.out.println("Item ID " + itemId + " removed.");
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Item ID " + itemId + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Item ID " + itemId + " removed.");
        }
    }

    public void updateQuantityById(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for Item ID " + itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID " + itemId + " not found.");
    }

    public void searchItemById(int itemId) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("[Name: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price + "]");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item ID " + itemId + " not found.");
    }

    public void searchItemByName(String itemName) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("[Name: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price + "]");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item Name '" + itemName + "' not found.");
        }
    }

    public void calculateTotalInventoryValue() {
        Item temp = head;
        double totalValue = 0;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + totalValue);
    }

    public void sortByName(boolean ascending) {
        head = mergeSortByName(head, ascending);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSortByPrice(head, ascending);
    }

    private Item mergeSortByName(Item head, boolean ascending) {
        if (head == null || head.next == null) {
            return head;
        }
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;
        Item left = mergeSortByName(head, ascending);
        Item right = mergeSortByName(nextOfMiddle, ascending);
        return sortedMergeByName(left, right, ascending);
    }

    private Item sortedMergeByName(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        Item result;
        if ((ascending && a.itemName.compareToIgnoreCase(b.itemName) <= 0) ||
                (!ascending && a.itemName.compareToIgnoreCase(b.itemName) > 0)) {
            result = a;
            result.next = sortedMergeByName(a.next, b, ascending);
        } else {
            result = b;
            result.next = sortedMergeByName(a, b.next, ascending);
        }
        return result;
    }

    private Item mergeSortByPrice(Item head, boolean ascending) {
        if (head == null || head.next == null) {
            return head;
        }
        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;
        Item left = mergeSortByPrice(head, ascending);
        Item right = mergeSortByPrice(nextOfMiddle, ascending);
        return sortedMergeByPrice(left, right, ascending);
    }

    private Item sortedMergeByPrice(Item a, Item b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        Item result;
        if ((ascending && a.price <= b.price) ||
                (!ascending && a.price > b.price)) {
            result = a;
            result.next = sortedMergeByPrice(a.next, b, ascending);
        } else {
            result = b;
            result.next = sortedMergeByPrice(a, b.next, ascending);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void displayInventory() {
        Item temp = head;
        System.out.println("Inventory List:");
        while (temp != null) {
            System.out.println("[Name: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price + "]");
            temp = temp.next;
        }
    }
}
public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        inventory.addItemAtEnd("Laptop", 101, 10, 75000);
        inventory.addItemAtEnd("Phone", 102, 20, 25000);
        inventory.addItemAtBeginning("Tablet", 103, 15, 30000);
        inventory.addItemAtPosition(2, "Monitor", 104, 5, 15000);

        inventory.displayInventory();
        System.out.println();

        inventory.updateQuantityById(102, 25);
        inventory.searchItemById(102);
        System.out.println();

        inventory.searchItemByName("Laptop");
        System.out.println();

        inventory.calculateTotalInventoryValue();
        System.out.println();

        System.out.println("Sorting by Name Ascending:");
        inventory.sortByName(true);
        inventory.displayInventory();
        System.out.println();

        System.out.println("Sorting by Price Descending:");
        inventory.sortByPrice(false);
        inventory.displayInventory();
    }
}
