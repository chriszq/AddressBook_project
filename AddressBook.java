// Caution: care must be taken with the input text file as uppercase, lowercase, spaces, etc. will be taken into consideration for sorting
// the address txt file it reads from must use commas as delimiter.

// remaining things to do:
// program should be capable of handling multiple address books
// consider using ArrayList of ArrayLists to load multiple text files and then manipulate them

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

		System.out.println("Which field would you like to edit:");
		System.out.print("\t1) First Name 2) Last Name 3) Phone no. 4) Address 5) email\n>>");

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

	public String getAddess()
	{
		return address;
	}

	public String getEmail()
	{
		return email;
	}

	@Override
	public String toString()	// overrides Object.toString()
	{
		return ("First name:" + this.firstName + "\nLast name: " + this.lastName + "\nPhone number: " + this.phoneNumber + "\nAddress: " + this.address + "\nemail: " + this.email + "\n");
	}

	public static void saveFile (ArrayList <Record> arrlist) throws FileNotFoundException
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("specify file to save to (must have \".txt\" extension): ");
		File file = new File(kb.nextLine());
		PrintWriter pw = new PrintWriter(file);
		Record[] arr = arrlist.toArray(new Record[arrlist.size()]);

		for (int i = 0; i < arr.length; i ++)
		{
			pw.write(arr[i].firstName + "," + arr[i].lastName + "," + arr[i].phoneNumber + "," + arr[i].address + "," + arr[i].email + ",");
		}
		pw.close();
	}
}

class firstNameSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getFirstName().compareTo(o2.getFirstName());
	}
}

class lastNameSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getLastName().compareTo(o2.getLastName());
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
		return o1.getAddess().compareTo(o2.getAddess());
	}
}

class emailSorter implements Comparator <Record>
{
	public int compare(Record o1, Record o2)
	{
		return o1.getEmail().compareTo(o2.getEmail());
	}
}

class Menu
{
	public static ArrayList <Record> newBook = new ArrayList <Record> ();	// public static variable allows the same ArrayList to be the "canvas" until the user decides to load a new one or start from scratch

	public static int loadMenu() throws IOException	// loads the main menu
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("\t1) Load from file");
		System.out.println("\t2) Save to file");
		System.out.println("\t3) Add an entry");
		System.out.println("\t4) Remove an entry");
		System.out.println("\t5) Edit an existing entry");
		System.out.println("\t6) Sort the address book");
		System.out.println("\t7) Search entries");
		System.out.println("\t8) Display current addressbook");
		System.out.println("\t9) Quit");

		System.out.print("\nAction: ");

		int action = Integer.parseInt(kb.nextLine());

		if (action == 1)
		{
			System.out.println("WARNING: any unsaved changes will be erased");
			System.out.print("address book to load (must have \".txt\" extension): ");
			String choice = kb.nextLine();
			newBook = Record.loadFromFile(choice);
		}
		else if (action == 2)
		{
			Record.saveFile(newBook);
		}
		else if (action == 3)
		{
			Record.addEntry(newBook);
		}
		else if (action == 4)
		{
			Record.removeEntry(newBook);
		}
		else if (action == 5)
		{
			Record.editEntry(newBook);
		}
		else if (action == 6)
		{
			loadSortMenu();
		}
		else if (action == 7)
		{
			loadSearchMenu();
		}
		else if (action == 8)
		{
			Record.showRecord(newBook);
		}
		else if (action == 9)
		{
			System.exit(0);
		}
		else
		{
			System.out.println("Invalid menu option");
		}

		return action;
	}

	public static void loadSortMenu()	// loads the submenu for sorting address book records
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Which field to sort address book by: ");
		System.out.println("1) first name 2) last name 3) phone no. 4) address 5) email");
		System.out.print("sort by: ");

		int sortAction = kb.nextInt();

		if (sortAction == 1)
		{
			Collections.sort(newBook, new firstNameSorter());
		}
		else if (sortAction == 2)
		{
			Collections.sort(newBook, new lastNameSorter());
		}
		else if (sortAction == 3)
		{
			Collections.sort(newBook, new phoneNumberSorter());
		}
		else if (sortAction == 4)
		{
			Collections.sort(newBook, new addressSorter());
		}
		else if (sortAction == 5)
		{
			Collections.sort(newBook, new emailSorter());
		}
		else
		{
			System.out.println("invalid field");
		}
	}

	public static void loadSearchMenu()	// loads the sub menu for searching address book records
	{
		Scanner kb = new Scanner(System.in);

		System.out.println("Select field to search by: ");
		System.out.println("1) first name 2) last name 3) phone no. 4) address 5) email");
		System.out.print("search by: ");

		int searchField = Integer.parseInt(kb.nextLine());

		System.out.print("search records by this field beginning with: ");

		String criteria = kb.nextLine();

		searchRecord(searchField, criteria);
	}

	public static void searchRecord(int field, String str)
	{
		switch (field)	// not sure if using if-else statements and switch-case makes any difference here.
		{
			case 1:
			for (int i = 0; i < newBook.size(); i ++)
			{
				if (newBook.get(i).getFirstName().startsWith(str))
				{
					Record.showElt(newBook, i);
				}
			}
			break;

			case 2:
			for (int i = 0; i < newBook.size(); i ++)
			{
				if (newBook.get(i).getLastName().startsWith(str))
				{
					Record.showElt(newBook, i);
				}
			}
			break;

			case 3:
			for (int i = 0; i < newBook.size(); i ++)
			{
				if (newBook.get(i).getPhoneNumber().startsWith(str))
				{
					Record.showElt(newBook, i);
				}
			}
			break;

			case 4:
			for (int i = 0; i < newBook.size(); i ++)
			{
				if (newBook.get(i).getAddess().startsWith(str))
				{
					Record.showElt(newBook, i);
				}
			}
			break;

			case 5:
			for (int i = 0; i < newBook.size(); i ++)
			{
				if (newBook.get(i).getEmail().startsWith(str))
				{
					Record.showElt(newBook, i);
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
		while (Menu.loadMenu() != 9);
	}
}