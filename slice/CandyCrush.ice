#include <Ice/Identity.ice>

module candycrush {

	struct Match {
		int row;
		int column;
		int dir;
		int length;
	};
	
	struct Square {
		int row;
		int column;
		int value;
	};
	
	struct Movement {
		int fromRow;
		int fromColumn;
		int toRow;
		int toColumn;
		int nSupporters;
	};
	
	sequence<int> BoardArray;
	sequence<BoardArray> BoardMatrix;
	
	sequence<Square> SquareList;

	interface Server {
		bool joinTeam(Ice::Identity id, string nick, int team);
		idempotent void teamComplete(Ice::Identity id);
		void movementProposal(Ice::Identity id, Movement mov);
		void message(Ice::Identity id, string msg);
		void leaveGame(Ice::Identity id);
	};
	
	interface Client {
		void newParticipant(string nick, int team);
		void participantLeft(string nick, int team);
		void startGame();
		void endGame(int team);
		idempotent void setBoard(BoardMatrix boardValues);
		idempotent void turn(int team);
		void log(string message);
		void customLog(string message, int code);
		void logStatus(string statusMessage);
		void makeMovement(Movement mov);
		idempotent void fillNewSquares(SquareList newSquares);
		void score(int team, int scored);
		void receiveMessage(string from, string message);
	};
};
