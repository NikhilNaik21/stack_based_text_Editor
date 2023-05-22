package com.dsaminiporject.texteditor;

import java.util.Scanner;

//Node creation
class Node{
	String data;
	Node next;

	Node(String e) {
		data = e;
		next = null;
	}
}

public class TextEditor {

	Node head;
	String temp;
	int flag = 0; //push:2 pop:1
	boolean undoflag = false; //flag to control undo operation
	String newreplacedvalue = "";

	public TextEditor() {
		head = null;
	}

	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	// push
	public void push(String e) {
		Node n = new Node(e);
		n.next = head;
		head = n;
		temp = e;
		flag = 2;
		undoflag=false;
	}

	//pop opr
	 void pop()
	  {
	      if(isEmpty())
	          return;
	      else
	      {
	          Node t=head;
	          head=head.next;
	          System.out.println("Element Poped:"+t.data);
	          temp = t.data;
	  		  flag = 1;
	  		  undoflag=false;
	      }
	  }

	// text editor display or print
	public void print() {
		if (isEmpty()) {
			System.err.println("Text Editor is Empty");
			return;
		}
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.data + " ");
			temp = temp.next;
		}
		//System.out.println();
	}

	// search code
	public void search(String data) {
		if (isEmpty()) {
			return;
		}
		Node temp = head;
		while (temp != null) {
			if (temp.data.equals(data)) {
				System.out.println("String found: " + data);
				return;
			}
			temp = temp.next;
		}
		System.err.println("String not found");
	}

	// Find and Replace
	public void findAndReplace(String find, String replace) {
		boolean found = false;
		if (isEmpty()) {
			return;
		}
		Node tempNode = head;
		while (tempNode != null) {
			if (tempNode.data.equals(find)) {
				temp = find;
				newreplacedvalue = replace;
				tempNode.data = replace;
				flag = 3;
				found=true;
			}
			tempNode = tempNode.next;
		}
		if (found) {
			System.out.println("Found occurrence of " + find + " and replaced with " + replace + ".");
		} else {
			System.err.println("String not found in Editor.");
		}
		undoflag=false;
	}

	// undo operation
	public void undo() {
		if (isEmpty()) {
			return;
		} else {
			if (flag == 2) {
				pop();
			} else if (flag == 1) {
				push(temp);
			} else if (flag == 3) {
				System.out.println(temp + " replaced by " + newreplacedvalue);
				findAndReplace(newreplacedvalue, temp);
			}
			undoflag=true;
		}
	}

	// redo operation  2:push 1: pop
	public void redo() {
		if (isEmpty()) {
			return;
		} else {
			if(undoflag)
			{
				if (flag == 2) {
					pop();
				} else if (flag == 1) {
					push(temp);
				}
				else if (flag == 3) {
					System.out.println(temp + " replaced by " + newreplacedvalue);
					findAndReplace(newreplacedvalue, temp);
				}
			}
			else
			{
				System.err.println("no undo Operations performed yet!!");
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TextEditor textEditor = new TextEditor();

		String choice="";

		// menu section
		do {
			System.out.println("\nMenu:");
			System.out.println("1. Insert the String");
			System.out.println("2. Pop");
			System.out.println("3. Display the String");
			System.out.println("4. Search the String");
			System.out.println("5. Find and replace the String");
			System.out.println("6. Undo the Operation");
			System.out.println("7. Redo the Operation");
			System.out.println("8. Exit");

				System.out.print("Enter your choice: ");
				choice = sc.nextLine();

				switch (choice) {
				case "1":
					System.out.print("Enter the Elements to be inserted : ");
					String str = sc.nextLine();
					textEditor.push(str);
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "2":
					textEditor.pop();
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "3":
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "4":
					// Search
					System.out.println("Enter the Element be searched : ");
					String data = sc.nextLine();
					textEditor.search(data);
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "5":
					// Find and replace
					System.out.println("Enter the Element to be searched :");
					String find = sc.nextLine();
					System.out.println("Enter the Element to be Replaced : ");
					String replace = sc.nextLine();
					textEditor.findAndReplace(find, replace);
					System.out.println("After replacing elements in Editor is/are:");
					textEditor.print();
					break;
				case "6":
					textEditor.undo();
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "7":
					textEditor.redo();
					System.out.println("Elements in Editor is/are: ");
					textEditor.print();
					break;
				case "8":
					System.out.println("Exited!!");
					choice="";
					break;
				default:
					System.err.println("Enter valid option");
					break;
				}
		} while (choice != "");
	}
}
