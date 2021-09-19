package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n리스트에 추가시킬 아이템의 제목을 입력하세요. > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 추가된 아이템과 동일한 제목을 사용할 수 없습니다.");
			return;
		}
		
		System.out.println("설명을 입력하세요. > ");
		sc.nextLine();
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, null);
		list.addItem(t);
		System.out.println("아이템이 추가되었습니다!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n리스트에서 삭제할 아이템의 제목을 입력하세요. > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("아이템이 삭제되었습니다!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n리스트에서 수정하고 싶은 아이템의 제목을 입력하세요. > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("방금 입력하신 제목의 아이템은 존재하지 않습니다.");
			return;
		}

		System.out.println("새로운 제목을 입력하세요. > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("같은 제목으로 바꿀 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 설명을 입력하세요. > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, null);
				l.addItem(t);
				System.out.println("아이템이 수정되었습니다!");
			}
		}

	}

	public static void listAll(TodoList l) {
		
		System.out.println("<전체 목록>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		int count = 0;
		
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				count++;
			}
			w.close();
			System.out.println(count + "개의 아이템이 \"todolist.txt\" 파일에 저장되었습니다.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		int count = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String title = st.nextToken();
				String description = st.nextToken();
				String date = st.nextToken();
				count++;
				
				TodoItem t = new TodoItem(title, description, date);
				l.addItem(t);
				
			}
			if(count==0) System.out.println("\"todolist.txt\" 파일에 아이템이 존재하지 않습니다.");
			else System.out.println("\"todolist.txt\" 파일에서 " + count + "개의 아이템을 읽어왔습니다.\n");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("\"todolist.txt\" 파일이 없습니다.");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
