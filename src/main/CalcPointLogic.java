package main;

public class CalcPointLogic {


	/* *****************
	 * ポイントイベント
	 * *****************/


	//-------------
	//Good event
	//-------------

	//オフェンス
	private String offTag1Good = "goal";
	private String offTag2Good = "assist";
	private String offTag3Good = "key_pass";
	private String offTag4Good = "key_innovation";
	private String offTag5Good = "anticipated";

	//ディフェンス
	private String defTag1Good = "interception";
	private String defTag2Good = "counter_attack";
	private String defTag3Good = "clearence";
	private String defTag4Good = "anticipation";

	//共通
	private String comTag1Good = "good";
	private String comTag2Good = "won";        //wonとlostはduelのみ適用される


	//-------------
	//Bad event
	//-------------

	//オフェンス
	private String offTag1Bad = "oppotunity";
	private String offTag2Bad = "lost";          //wonとlostはduelのみ適用される
	private String offTag3Bad = "ball_lost";

	//共通
	private String comTag1Bad = "yellow";
	private String comTag2Bad = "red";
	private String comTag3Bad = "hand";
	private String comTag4Bad = "bad";


	//--------
	//Other
	//--------

	//Good
	private String subEve1Good = "Smart_pass";

	//Bad
	private String subEve1Bad = "Foul";


	/*
	 * ポイント獲得イベント判定処理
	 */
	public int checkPointEvent(DataBean dataBean) {

		String unitTagAll = this.unitAllTag(dataBean);
		int pointSum = this.calcPointOffenceEvent(unitTagAll) + this.calcPointDiffenceEvent(unitTagAll) + this.calcPointCommonEvent(unitTagAll) + this.calcPointOtherEvent(dataBean.getSubEventName());
		return pointSum;
	}


	/*
	 * オフェンスのポイント獲得イベント判定処理
	 */
	private int calcPointOffenceEvent(String unitTagAll) {

		int point = 0;

		//Goodイベント
		if (unitTagAll.indexOf(offTag1Good) != -1) {
			point += 2;
		}
		if (unitTagAll.indexOf(offTag2Good) != -1) {
			point += 1;
		}
		if (unitTagAll.indexOf(offTag3Good) != -1) {
			point += 1;
		}
		if (unitTagAll.indexOf(offTag4Good) != -1) {
			point += 1;
		}
		if (unitTagAll.indexOf(offTag5Good) != -1) {
			point += 1;
		}

		//Badイベント
		if (unitTagAll.indexOf(offTag1Bad) != -1) {
			point -= 1;
		}
		if (unitTagAll.indexOf(offTag2Bad) != -1) {
			point -= 1;
		}
		if (unitTagAll.indexOf(offTag3Bad) != -1) {
			point -= 1;
		}

		return point;
	}


	/*
	 * ディフェンスのポイント獲得イベント判定処理
	 */
	private int calcPointDiffenceEvent(String unitTagAll) {

		int point = 0;

		//Goodイベント
		if (unitTagAll.indexOf(defTag1Good) != -1) {
			point += 2;
		}
		if (unitTagAll.indexOf(defTag2Good) != -1) {
			point += 2;
		}
		if (unitTagAll.indexOf(defTag3Good) != -1) {
			point += 2;
		}
		if (unitTagAll.indexOf(defTag4Good) != -1) {
			point += 2;
		}

		return point;
	}


	/*
	 * 共通のポイント獲得イベント判定処理
	 */
	private int calcPointCommonEvent(String unitTagAll) {

		int point = 0;

		//Goodイベント
		if (unitTagAll.indexOf(comTag1Good) != -1) {
			point += 1;
		}
		if (unitTagAll.indexOf(comTag2Good) != -1) {
			point += 1;
		}

		//Badイベント
		if (unitTagAll.indexOf(comTag1Bad) != -1) {
			point -= 1;
		}
		if (unitTagAll.indexOf(comTag2Bad) != -1) {
			point -= 2;
		}
		if (unitTagAll.indexOf(comTag3Bad) != -1) {
			point -= 1;
		}
		if (unitTagAll.indexOf(comTag4Bad) != -1) {
			point -= 1;
		}

		return point;
	}


	/*
	 * その他(subEvent)のポイント獲得イベント判定処理
	 */
	private int calcPointOtherEvent(String subEvent) {

		int point = 0;

		//Goodイベント
		if (subEvent.indexOf(subEve1Good) != -1) {
			point += 1;
		}

		//Badイベント
		if (subEvent.indexOf(subEve1Bad) != -1) {
			point -= 1;
		}

		return point;
	}


	/*
	 * タグ結合処理
	 */
	private String unitAllTag(DataBean dataBean) {

		String tagAll = dataBean.getTag1() + dataBean.getTag2() + dataBean.getTag3() + dataBean.getTag4() + dataBean.getTag5() + dataBean.getTag6();
		return tagAll;
	}
}
