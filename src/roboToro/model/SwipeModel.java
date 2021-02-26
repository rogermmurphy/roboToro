package roboToro.model;

public class SwipeModel extends CommandModel implements ActionInterface {

	public String getType() {
		return ActionModel.TYPE_DRAG;
		
	}
	
	public SwipeModel() {
		// TODO Auto-generated constructor stub
	}
	
	private MoveModel cmClickUpStart;
	private MoveModel cmClickDownStart;
	private MoveModel cmClickDownEnd;
	private MoveModel cmClickUpEnd;
	

}
