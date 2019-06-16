package main;

import java.util.Scanner;

public class TicTacToe {
	//	status 1 player 1 wins
	//	status 2 player 2 wins
	//	status 3 tie
	//	status 4 incomplete
	//	status 5 invalid move
	public Scanner s = new Scanner (System.in);
	private Player player1, player2;
	private Board board;
	public void startGame() {
		//		player input
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		while (player1.getSymbol() == player2.getSymbol())// if symbol is same
		{
			System.out.println("Symbol already taken please pick another symbol");
			char symbol = s.next().charAt(0);
			player2.setSymbol(symbol);
		}
		//		create board
		board = new Board(player1.getSymbol(), player2.getSymbol());
		//		conduct game
		boolean player1Turn = true;
		int status = Board.INCOMPLETE;
		while(status == Board.INCOMPLETE || status == Board.INVALID) 
		{
			if(player1Turn) 
			{
				System.out.println("Player1 -" + player1.getName() + "'s turn");
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				status = board.move(player1.getSymbol(), x, y);
				if(status != Board.INVALID) 
				{
					player1Turn = false;
					board.print();
				}
				else if(status == Board.INVALID)
					System.out.println("Please enter valid coordinates");
			}
			else 
			{
				System.out.println("Player2 -" + player2.getName() + "'s turn");
				System.out.println("Enter x: ");
				int x = s.nextInt();
				System.out.println("Enter y: ");
				int y = s.nextInt();
				status = board.move(player2.getSymbol(), x, y);
				if(status != Board.INVALID) 
				{
					player1Turn = true;
					board.print();
				}
				else if(status == Board.INVALID)
					System.out.println("Please enter valid coordinates");
			}
		}
		if(status == Board.PLAYER1_WINS) 
		{
			System.out.println("Player1 -" + player1.getName() + "WINS");	
		}
		else if(status == Board.PLAYER2_WINS) 
		{
			System.out.println("Player2 - " + player2.getName() + "WINS");	

		}
		else
			System.out.println("TIE");
	}
	private Player takePlayerInput(int num)// num indicating player 
	{
		System.out.println("Enter player " + num + " name: ");
		String name = s.next(); 
		System.out.println("Enter player " + num + " symbol: ");
		char symbol = s.next().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}

}
