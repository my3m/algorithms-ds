package io.linkedlist;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}
	
	public ListNode(int[] x) {
		val = x[0];
		ListNode ref = this;
		for(int i = 1; i < x.length; i++) {
			ref.next = new ListNode(x[i]);
			ref = ref.next;
		}
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
	//7,7,1
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		ListNode ref = this;
		
		if(ref != null) {
			sb.append(ref.val);
			ref = ref.next;
			while(ref != null) {
				sb.append("->"+ref.val);
				ref = ref.next;
			}
		}
		return sb.toString();
	}
}
