package roboToro.model;

public class ClickModel implements ActionInterface {

	public ClickModel() {
		// TODO Auto-generated constructor stub
	}

	private MoveModel cmClickUpStart;
	private MoveModel cmClickDown;
	private MoveModel cmClickUpEnd;
	
	public MoveModel getCmClickUpStart() {
		return cmClickUpStart;
	}
	public void setCmClickUpStart(MoveModel cmClickUpStart) {
		this.cmClickUpStart = cmClickUpStart;
	}
	public MoveModel getCmClickDown() {
		return cmClickDown;
	}
	public void setCmClickDown(MoveModel cmClickDown) {
		this.cmClickDown = cmClickDown;
	}
	public MoveModel getCmClickUpEnd() {
		return cmClickUpEnd;
	}
	public void setCmClickUpEnd(MoveModel cmClickUpEnd) {
		this.cmClickUpEnd = cmClickUpEnd;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return ActionModel.TYPE_CLICK;
	}
}
