package kaako.test2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @see https://programmers.co.kr/learn/courses/30/lessons/42888
 * @author jun
 *
 */
public class Openchat {
	public String[] solution(String[] record) {
		HashMap<String, String> name = new HashMap<>();
		List<String> log = new ArrayList<>();
		for(int i = 0; i < record.length; i++) {
			String[] info = record[i].split(" ");
			switch(info[0]) {
				case "Enter":
					log.add(info[0]+" "+info[1]);
					name.put(info[1], info[2]);
					break;
				case "Leave":
					log.add(info[0]+" "+info[1]);
					break;
				case "Change":
					name.put(info[1], info[2]);
					break;
			}
		}
		
		String[] answer = new String[log.size()];
		for(int i = 0; i < log.size(); i++) {
			String[] tmp = log.get(i).split(" ");
			if(tmp[0].equals("Enter")) {
				answer[i] = name.get(tmp[1])+"님이 들어왔습니다.";
			} else {
				answer[i] = name.get(tmp[1])+"님이 나갔습니다.";
			}
		}
		return answer;
    }
}
