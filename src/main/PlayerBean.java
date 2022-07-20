package main;

public class PlayerBean {

	private String playerId;
	private String playerName;
	private String position;
	private String team;
	private int point;


	//コンストラクタ―
	public PlayerBean() {}
	public PlayerBean(DataBean dataBean, int point) {
		super();
		this.playerId = dataBean.getPlayerId();
		this.playerName = dataBean.getPlayer();
		this.position = dataBean.getPositions();
		this.team = dataBean.getTeam();
		this.point += point;
	}



	//getter, setter
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getPoint() {
		return point;
	}
	public void setPointReNew(int point) {
		this.point += point;
	}
}
