import java.util.ArrayList;

public abstract class Piece {
	
	protected Team team;
	protected int value;	// used for evaluating the current state of a board
	
	public Piece(Team team, int value) {
		this.value = value;
		this.team = team;
	}
	
	public abstract Piece copy();	// returns a shallow copy of the piece, except for pawns (deep copy)
	
	public abstract ArrayList<int[]> getMoves(ChessBoard board, int i, int j);	// returns all possible moves that a piece at (i, j) can perform
	
	public Team getTeam() {
		return team;
	}
	
	// determines whether this piece puts the opponent's king in check
	public final boolean check(ChessBoard board, int i, int j) {
		ArrayList<int[]> possMoves = getMoves(board, i, j);
		
		for (int[] move : possMoves) {
			Piece p = board.getPiece(move[0], move[1]);
			if (p != null && p.team != this.team && p.getClass() == King.class) 
				return true;
		}
		return false;
	}
}
