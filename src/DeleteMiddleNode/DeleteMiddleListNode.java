package DeleteMiddleNode;
import java.util.ArrayList;
import java.util.Collections;

public class DeleteMiddleListNode {

    public static void main(String[] args) {
        // Make the example
        int[] newList = {1,2,3,4};
        //int[] newList = {1,3,4,7,1,2,6};
        ListNode myListNode = fillList(newList);

        System.out.println("The contents of the updated list are: " + printListValues(deleteMiddle(myListNode)));
    }

    private static ListNode deleteMiddle(ListNode head) {
        int deletionIndex = 2;
        ListNode before = null;
        ListNode after = null;
        ListNode current = head;
        int currentIndex = 0;
        ArrayList<Integer> indexableCopy = new ArrayList<Integer>();

        // Since I have to iterate the list to get its length, copy it to an ArrayList at the same time.
        // (Note: The rest of the code was written prior to this change. There are almost certainly efficiencies
        // now that I have an ArrayList instead of just a singly-linked list.)
        while(current.next != null){
            indexableCopy.add(current.val);
            current = current.next;
        }

        // Calculate middle position.
        deletionIndex = (int) Math.floor(indexableCopy.size()/2);

        // Reusing a variable.
        current = head;
        while(after == null && current.next != null) {
            if(currentIndex == deletionIndex - 1) {
                // Get the list node before the middle position.
                before = current;
            }
            else if(currentIndex == deletionIndex + 1) {
                // Get the list node after the middle position.
                after = current;
            }

            current = current.next;
            currentIndex++;
        }
        String s = "stupid";
        // Point the list node before the middle position to the list node after it.
        before.next = after;

        // Return original head.
        return head;
    }


    // This is just to make testing easier.
    private static ListNode fillList(int[] values){
        ListNode firstNode = new ListNode(values[0]);
        ListNode currNode = firstNode;

        for(int i=1; i<values.length; i++){
            currNode.next = new ListNode(values[i]);
            currNode = currNode.next;
        }

        return firstNode;
    }

    private static String printListValues(ListNode head){
        String s = "";
        return recursivelyPrintListValues(s, head);
    }

    private static String recursivelyPrintListValues(String s, ListNode head){
        if(head.next != null){
            s += head.val + recursivelyPrintListValues(s, head.next);
        }
        else{
            s += head.val;
        }

        return s;
    }
}
