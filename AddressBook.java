// Caution: the address txt file it reads from must use commas delimiter

// remaining things to do:
// deal with invalid user inputs
// no matches after search

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
// import java.util.*;
import java.io.*;

class Record
{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String email;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public String getAddress()
	{
		return address;
	}

	public String getEmail()
	{
		return email;
	}

	public static ArrayList <Record> loadFromFile (String str) throws IOException
	{
		Scanner dataFile = new Scanner(new File(str));
		ArrayList <Record> arr = new ArrayList <Record> ();
		dataFile.useDelimiter(",");

		while (dataFile.hasNext())
		{
			Record record = new Record();
			record.firstName = dataFile.next();
			record.lastName = dataFile.next();
			record.phoneNumber = dataFile.next();
			record.address = dataFile.next();
			record.email = dataFile.next();

			arr.add(record);
		}

		return arr;
	}

	public static  void addEntry (ArrayList <Record> record)
	{
		Scanner kb = new Scanner(System.in);
		Record r = new Record();

		System.out.print("Enter first name: ");
		r.firstName = kb.nextLine();
		System.out.print("\nEnter last name: ");
		r.lastName = kb.nextLine();
		System.out.print("\nEnter phone number: ");
		r.phoneNumber = kb.nextLine();
		System.out.print("\nEnter address: ");
		r.address = kb.nextLine();
		System.out.print("\nEnter email: ");
		r.email = kb.nextLine();

		record.add(r);
	}

	public static void removeEntry (ArrayList <Record> record)
	{
		Scanner kb = new Scanner(System.in);

		System.out.print("Enter the record number to remove: ");
		int index = kb.nextInt();

		record.remove(index);
	}

	public static void editEntry (ArrayList <Record> record)
	{
		Scanner kb = new Scanner(System.in);
		Record r = new Record();

		System.out.print("Enter the record number to edit: ");
		int index = Integer.parseInt(kb.nextLine());
		r = record.get(index);

		System.out.println("Select field to edit");
		System.out.println("1) First Name 2) Last Name 3) Phone no. 4) Address 5) email");
		System.out.print("field number: ");

		int fieldNum = Integer.parseInt(kb.nextLine());

		if (fieldNum == 1)
		{
			System.out.print("Enter new first name: ");
			r.firstName = kb.nextLine();
		}
		else if (fieldNum == 2)
		{
			System.out.print("Enter new last name: ");
			r.lastName = kb.nextLine();
		}
		else if (fieldNum == 3)
		{
			System.out.print("Enter new phone number: ");
			r.phoneNumber = kb.nextLine();
		}
		else if (fieldNum == 4)
		{
			System.out.print("Enter new address: ");
			r.address = kb.nextLine();
		}
		else if (fieldNum == 5)
		{
			System.out.print("Enter new email: ");
			r.email = kb.nextLine();
		}
		else
		{
			System.out.println("invalid field");
		}

		record.set(index, r);
	}

	public static void saveFile (String str, ArrayList <Record> record) throws FileNotFoundException
	{
		File file = new File(str);
		PrintWriter pw = new PrintWriter(file);
		Record[] arr = record.toArray(new Record[record.size()]);

		for (int i = 0; i < arr.length; i ++)
		{
			pw.write(arr[i].firstName + "," + arr[i].lastName + "," + arr[i].phoneNumber + "," + arr[i].address + "," + arr[i].email + ",");
		}
		pw.close();
	}

	public static void copyRecord(ArrayList <Record> arrList1, ArrayList <Record> arrList2, int x)
	{
		arrList2.add(arrList1.get(x));	// adds element index x from arrList1 to arrList2
	}

	public static void showElt (ArrayList <Record> record, int x)	// prints the entry number (index) of the element along with the rest of the Record object
	{
		System.out.println("Entry number: " + x + "\n" + record.get(x));
	}

	public static void showRecord (ArrayList <Record> record)	// prints the entire ArrayList
	{
		for (int i = 0; i < record.size(); i ++)
		{
			System.out.println("Entry number: " + i + "\n" + record.get(i));
		}
	}

	@Override
	public String toString()	// overrides Object.toString()
	{
		return ("First name:" + this.firstName + "\nLast name: " + this.lastName + "\nPhone number: " + this.phoneNumber + "\nAddress: " + this.address + "\nemail: " + this.email + "\n");
	}
}

class firstNameSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase());
	}
}

class lastNameSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase());
	}
}

class phoneNumberSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
	}
}

class addressSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getAddress().toLowerCase().compareTo(o2.getAddress().toLowerCase());
	}
}

class emailSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getEmail().toLowerCase().compareTo(o2.getEmail().toLowerCase());
	}
}

class BookShelf
{
	String bookName;
	ArrayList <Record> book;

	@Override
	public String toString()
	{
		return (this.bookName);
	}

	public static void addToShelf(String str, ArrayList <Record> arrList)
	{
		BookShelf newAddressBook = new BookShelf();
		newAddressBook.bookName = str;
		newAddressBook.book = arrList;

		Menu.bookShelf.add(newAddressBook);
	}

	public static void showBookShelf(ArrayList <BookShelf> arrList)
	{
		for (int i = 0; i < arrList.size(); i ++)
		{
			System.out.print(i + ") " + arrList.get(i) + " ");
		}
	}
}

class Menu
{
	public static ArrayList <BookShelf> bookShelf = new ArrayList <BookShelf> ();	// new book shelf instantiated before everything begins

	public static int loadMenu() throws IOException
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("\n\t0) Create empty address book");
		System.out.println("\t1) Load from file");
		System.out.println("\t2) Save to file");
		System.out.println("\t3) Add entry");
		System.out.println("\t4) Remove entry");
		System.out.println("\t5) Edit entry");
		System.out.println("\t6) Copy entry");
		System.out.println("\t7) Sort entries");
		System.out.println("\t8) Search entries");
		System.out.println("\t9) Display an address book");
		System.out.println("\t10) Display loaded address books");
		System.out.println("\t11) Quit");
		System.out.print("\nAction: ");

		int action = Integer.parseInt(kb.nextLine());
		int bookChoice;

		if (action == 0)
		{
			System.out.print("name of new address book: ");
			String newBookName = kb.nextLine();
			BookShelf.addToShelf(newBookName, new ArrayList <Record>());	// new empty address books are immediately added to shelf for ease of use
		}
		else if (action == 1)
		{
			System.out.print("address book to load: ");
			String choice = kb.nextLine();
			BookShelf.addToShelf(choice, Record.loadFromFile(choice));
		}
		else if (action == 2)
		{
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect addressbook number to save: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.saveFile(bookShelf.get(bookChoice).bookName, bookShelf.get(bookChoice).book);
		}
		else if (action == 3)
		{
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect address book number to add entry to: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.addEntry(bookShelf.get(bookChoice).book);
		}
		else if (action == 4)
		{
			System.out.println("select address book to remove entry from: ");
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\naddress book number: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.showRecord(bookShelf.get(bookChoice).book);
			Record.removeEntry(bookShelf.get(bookChoice).book);
		}
		else if (action == 5)
		{
			System.out.println("select address book to edit entry from: ");
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\naddress book number: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.showRecord(bookShelf.get(bookChoice).book);
			Record.editEntry(bookShelf.get(bookChoice).book);
		}
		else if (action == 6)
		{
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect address book number to copy from:");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.showRecord(bookShelf.get(bookChoice).book);
			System.out.print("select entry number to copy: ");
			int entryNo = Integer.parseInt(kb.nextLine());
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect address book number to copy to:");
			int bookChoice1 = Integer.parseInt(kb.nextLine());
			Record.copyRecord(bookShelf.get(bookChoice).book, bookShelf.get(bookChoice1).book, entryNo);
		}
		else if (action == 7)
		{
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect address book number to sort: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			loadSortMenu(bookShelf.get(bookChoice).book);
		}
		else if (action == 8)
		{
			System.out.println("select address book to search: ");
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\naddress book number: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			loadSearchMenu(bookShelf.get(bookChoice).book);
		}
		else if (action == 9)
		{
			BookShelf.showBookShelf(bookShelf);
			System.out.print("\nselect address book number to display: ");
			bookChoice = Integer.parseInt(kb.nextLine());
			Record.showRecord(bookShelf.get(bookChoice).book);
		}
		else if (action == 10)
		{
			System.out.println("currently loaded address books:");
			BookShelf.showBookShelf(bookShelf);
		}
		else if (action == 11)
		{
			System.exit(0);
		}
		else
		{
			System.out.println("Invalid menu option");
		}

		return action;
	}

	public static void loadSortMenu(ArrayList <Record> arrList)	// loads the submenu for sorting address book records
	{
		Scanner kb = new Scanner(System.in);

		System.out.println("1) first name 2) last name 3) phone no. 4) address 5) email");
		System.out.print("\nselect field number to sort address book by: ");

		int sortAction = kb.nextInt();

		if (sortAction == 1)
		{
			Collections.sort(arrList, new firstNameSorter());
		}
		else if (sortAction == 2)
		{
			Collections.sort(arrList, new lastNameSorter());
		}
		else if (sortAction == 3)
		{
			Collections.sort(arrList, new phoneNumberSorter());
		}
		else if (sortAction == 4)
		{
			Collections.sort(arrList, new addressSorter());
		}
		else if (sortAction == 5)
		{
			Collections.sort(arrList, new emailSorter());
		}
		else
		{
			System.out.println("invalid field");
		}
	}

	public static void loadSearchMenu(ArrayList <Record> arrList)	// loads the sub menu for searching address book records
	{
		Scanner kb = new Scanner(System.in);

		System.out.println("1) first name 2) last name 3) phone no. 4) address 5) email");
		System.out.print("Select field number to search by: ");

		int searchField = Integer.parseInt(kb.nextLine());

		System.out.print("search records by this field beginning with: ");

		String criteria = kb.nextLine();

		searchRecord(searchField, criteria, arrList);
	}

	public static void searchRecord(int field, String str, ArrayList <Record> arrList)
	{
		str = str.toLowerCase();	// the search funtionality is non case sensitive (both ways)

		switch (field)	// not sure if using if-else statements and switch-case makes any difference here.
		{
			case 1:
			for (int i = 0; i < arrList.size(); i ++)
			{
				if (arrList.get(i).getFirstName().toLowerCase().startsWith(str))
				{
					Record.showElt(arrList, i);
				}
			}
			break;

			case 2:
			for (int i = 0; i < arrList.size(); i ++)
			{
				if (arrList.get(i).getLastName().toLowerCase().startsWith(str))
				{
					Record.showElt(arrList, i);
				}
			}
			break;

			case 3:
			for (int i = 0; i < arrList.size(); i ++)
			{
				if (arrList.get(i).getPhoneNumber().toLowerCase().startsWith(str))
				{
					Record.showElt(arrList, i);
				}
			}
			break;

			case 4:
			for (int i = 0; i < arrList.size(); i ++)
			{
				if (arrList.get(i).getAddress().toLowerCase().startsWith(str))
				{
					Record.showElt(arrList, i);
				}
			}
			break;

			case 5:
			for (int i = 0; i < arrList.size(); i ++)
			{
				if (arrList.get(i).getEmail().toLowerCase().startsWith(str))
				{
					Record.showElt(arrList, i);
				}
			}
			break;

			default: System.out.println("invalid criteria");
		}
	}
}

public class AddressBook
{
	public static void main (String[] args) throws IOException
	{
		do
		{
			Menu.loadMenu();
		}
		while (Menu.loadMenu() != 11);
	}
}