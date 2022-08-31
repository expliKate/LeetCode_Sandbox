package DeleteMiddleNode;

public class DeleteMiddleListNode {

    public static void main(String[] args) {
        // Make the example
        int[] newList = {1,2,3,4,5,6};
        ListNode myListNode = fillList(newList);

        System.out.println("The contents of the updated list are: " + printListValues(deleteMiddle(myListNode)));
    }

    private static ListNode deleteMiddle(ListNode head) {
        int deletionIndex = 2;
        ListNode before = null;
        ListNode after = null;
        ListNode current = head;
        int currentIndex = 0;

        // Calculate middle position.
        //TODO: Actually calculate this.

        while(after == null && head.next != null) {
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
