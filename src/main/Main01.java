package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main01 {

	public static void main(String[] args) {

		Path file = Paths.get("fifa.txtの絶対パス");
		String text = "";
		try {
			text = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//データを行ごとに配列で格納
		String[] dataArray = text.split("\n");
		boolean isFirstRoup = true;

		List<DataBean> dataList = new ArrayList<>();

		for (String data: dataArray) {

			//最初のループ(データがテーブルのカラム)のとき
			if (isFirstRoup) {
				isFirstRoup = false;
				continue;
			}

			//tabコードでで分割
			String[] dataSet = data.split("/");

			//格納するデータ
			 String number = "";
			 String unnamed = "";
			 String eventId = "";
			 String subEventName = "";
			 String tags = "";
			 String playerId = "";
			 String positions = "";
			 String matchId = "";
			 String eventName = "";
			 String teamId = "";
			 String matchPeriod = "";
			 String eventSec = "";
			 String subEventId = "";
			 String id = "";
			 String x1 = "";
			 String y1 = "";
			 String x2 = "";
			 String y2 = "";
			 String tag1 = "";
			 String tag2 = "";
			 String tag3 = "";
			 String tag4 = "";
			 String tag5 = "";
			 String tag6 = "";
			 String team = "";
			 String player = "";
			 String match = "";
			 String winnerId = "";
			 String winner = "";

			//ループ
			for (int i = 0; i < dataSet.length; i++) {

				String value = dataSet[i];

				if (i == 0) {
					number = value;
					continue;
				}
				if (i == 1) {
					unnamed = value;
					continue;
				}
				if (i == 2) {
					eventId = value;
					continue;
				}
				if (i == 3) {
					subEventName = value;
					continue;
				}
				if (i == 4) {
					tags = value;
					continue;
				}
				if (i == 5) {
					playerId = value;
					continue;
				}
				if (i == 6) {
					positions = value;
					continue;
				}
				if (i == 7) {
					matchId = value;
					continue;
				}
				if (i == 8) {
					eventName = value;
					continue;
				}
				if (i == 9) {
					teamId = value;
					continue;
				}
				if (i == 10) {
					matchPeriod = value;
					continue;
				}
				if (i == 11) {
					eventSec = value;
					continue;
				}
				if (i == 12) {
					subEventId = value;
					continue;
				}
				if (i == 13) {
					id = value;
					continue;
				}

				if (i == 14) {
					x1 = value;
					continue;
				}
				if (i == 15) {
					y1 = value;
					continue;
				}
				if (i == 16) {
					x2 = value;
					continue;
				}
				if (i == 17) {
					y2 = value;
					continue;
				}
				if (i == 18) {
					tag1 = value;
					continue;
				}
				if (i == 19) {
					tag2 = value;
					continue;
				}

				if (i == 20) {
					tag3 = value;
					continue;
				}
				if (i == 21) {
					tag4 = value;
					continue;
				}
				if (i == 22) {
					tag5 = value;
					continue;
				}
				if (i == 23) {
					tag6 = value;
					continue;
				}
				if (i == 24) {
					team = value;
					continue;
				}
				if (i == 25) {
					player = value;
					continue;
				}
				if (i == 26) {
					match = value;
					continue;
				}
				if (i == 27) {
					winnerId = value;
					continue;
				}
				if (i == 28) {
					winner = value;
					continue;
				}
			}

			DataBean dataBean = new DataBean(number, unnamed, eventId, subEventName, tags, playerId, positions, matchId, eventName, teamId, matchPeriod, eventSec, subEventId, id, x1, y1, x2, y2, tag1, tag2, tag3, tag4, tag5, tag6, team, player, match, winnerId, winner);
			dataList.add(dataBean);
		}

		//プレイヤー情報を格納
		List<PlayerBean> playerList = new ArrayList<>();

		//ポイント計算クラス
		CalcPointLogic calcPointLogic = new CalcPointLogic();

		//-----
		//計算
		//-----
		for (DataBean dataBean: dataList) {

			//playerListが何もないとき
			if (playerList.isEmpty()) {

				//ポイントイベントを発生させ、playerListに登録する
				int point = calcPointLogic.checkPointEvent(dataBean);
				PlayerBean playerBean = new PlayerBean(dataBean, point);
				playerList.add(playerBean);
				continue;
			}

			//playerListの回数分ループ
			for (int i = 0; i < playerList.size(); i++) {

				//現在の要素をplayerBeanに代入
				PlayerBean playerListBean = playerList.get(i);

				//playerBeanに登録されているとき
				if (dataBean.getPlayerId().equals(playerListBean.getPlayerId())) {

					//ポイントイベントを発生させ、playerListに既存のBeanを更新させセットする
					int point = calcPointLogic.checkPointEvent(dataBean);
					playerListBean.setPointReNew(point);
					playerList.set(i, playerListBean);
					break;
				}

				//playerListに登録されていないとき
				if (i == playerList.size() - 1) {

					//ポイントイベント発生させ、playerListに新しいBeanを登録する
					int point = calcPointLogic.checkPointEvent(dataBean);
					PlayerBean playerBean = new PlayerBean(dataBean, point);
					playerList.add(playerBean);
					break;
				}
			}
		}

		//playerListの値をファイルに書き出し
		try {
			FileWriter playerFile = new FileWriter("player.txtの絶対パス");
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(playerFile));

			//カラムを書き込む
			printWriter.println("playerId	playerName	position	team	point");

			//playerListのデータ全てを書き込む
			for (PlayerBean playerBean: playerList) {

				//抽出
				String playerId = playerBean.getPlayerId();
				String playerName = playerBean.getPlayerName();
				String position = playerBean.getPosition();
				String team = playerBean.getTeam();
				int point = playerBean.getPoint();

				printWriter.println(playerId + "	" + playerName + "	" + position + "	" + team + "	" + point);
			}

			//ファイルを閉じる
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}