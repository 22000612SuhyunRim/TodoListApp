package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<메뉴 사용법>");
        System.out.println("1. 아이템 추가 [add]");
        System.out.println("2. 아이템 삭제 [del]");
        System.out.println("3. 아이템 수정 [edit]");
        System.out.println("4. 아이템 목록 [ls]");
        System.out.println("5. 아이템 이름순으로 정렬 [ls_name_asc]");
        System.out.println("6. 아이템 이름역순으로 정렬 [ls_name_desc]");
        System.out.println("7. 아이템 시간순으로 정렬 [ls_date]");
        System.out.println("8. 나가기 [exit]");
        System.out.println("\nEnter a command > ");
    }
    
    public static void prompt() {
    	System.out.println("\nEnter a command > ");
    }
}
